package controllers;

import io.javalin.http.Context;

import io.javalin.http.HttpCode;
import io.netty.handler.codec.http.HttpResponseStatus;
import kong.unirest.HttpStatus;
import models.Items;
import scrapper.PicknPayScrapper;
import scrapper.ShopriteScrapper;
import scrapper.WooliesScrapper;

import java.time.LocalTime;
import java.util.*;

public class ItemsController{
    private static final ArrayList<Items> itemsPnp = new ArrayList<>();
    private static final ArrayList<Items> itemsShop = new ArrayList<>();
    private static final ArrayList<Items> itemsWool = new ArrayList<>();

    private static final HashMap<String,Object> results = new HashMap<>();

    public static void findtems(Context ctx) throws InterruptedException {
        String itemToSearchFor = ctx.body();

        processItem(itemToSearchFor);

        if (!itemsWool.isEmpty() || !itemsPnp.isEmpty() ){
            ctx.json(Map.of("result","Done", "items",Map.of("woolworths",itemsWool.size()
                    ,"pnp",itemsPnp.size(),"shoprite",itemsShop.size())));
        }else {
            ctx.json(Map.of("result","ERROR"));
        }
    }

    public static void findtem(Context ctx) throws InterruptedException {
        String itemToSearchFor = ctx.body();
        String store = ctx.pathParam("store");

        processItem(itemToSearchFor,store);
        ctx.json(Map.of("result","Done"));
    }


    public static void processItem(String itemToSearchFor, String... store) throws InterruptedException {

//        TODO: simplify somehow
        Thread pnpProcess = new Thread(() -> {
            try {
                HashMap<String, Double> foundItemsPnP = PicknPayScrapper.searchItems(itemToSearchFor);
                itemsPnp.clear();
                createItemObject(foundItemsPnP,itemsPnp);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread woolProcess = new Thread(() -> {
            try {
                HashMap<String, Double> foundItemsWool = WooliesScrapper.searchItems(itemToSearchFor);
                itemsWool.clear();
                createItemObject(foundItemsWool,itemsWool);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread shopProcess = new Thread(() -> {
            try {
                HashMap<String, Double> foundItemsShop = ShopriteScrapper.searchItems(itemToSearchFor);
                itemsShop.clear();
                createItemObject(foundItemsShop,itemsShop);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        if (store.length > 0){
            switch (store[0]){
                case "pnp":
                    pnpProcess.start();
                    pnpProcess.join();
                    break;
                case "wool":
                    woolProcess.start();
                    woolProcess.join();
                    break;
                case "shop":
                    shopProcess.start();
                    shopProcess.join();
                    break;
            }
        }
        else {
            woolProcess.start();
            pnpProcess.start();
            shopProcess.start();
            woolProcess.join();
            pnpProcess.join();
            shopProcess.join();
        }
    }

    private static void createItemObject(HashMap<String, Double> storeMap, ArrayList<Items> itemsList){
        //TODO: could be moved to scappers?
        if (storeMap.size() >=1){
            storeMap.forEach((description, price) -> {
                itemsList.add(new Items(description,price));
            });
        }
    }

    public static void viewItem(Context ctx){
        results.put("woolworths",itemsWool);
        results.put("pnp", itemsPnp);
        results.put("shoprite", itemsShop);

        ctx.json(results);
    }
}
