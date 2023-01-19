package controllers;

import io.javalin.http.Context;

import models.Items;

import java.util.*;

public class ItemsController{

    public static void findtems(Context ctx) throws InterruptedException {
        String itemToSearchFor = ctx.body();

        ScrapperProcess.processItem(itemToSearchFor);

        if (!ScrapperProcess.itemsWool.isEmpty() || !ScrapperProcess.itemsPnp.isEmpty() ){
            ctx.json(Map.of("result","Done", "items",Map.of("woolworths", ScrapperProcess.itemsWool.size()
                    ,"pnp", ScrapperProcess.itemsPnp.size(),"shoprite", ScrapperProcess.itemsShop.size())));
        }else {
            ctx.json(Map.of("result","ERROR"));
        }
    }

    public static void findtem(Context ctx) throws InterruptedException {
        String itemToSearchFor = ctx.body();
        String store = ctx.pathParam("store");

        ScrapperProcess.processItem(itemToSearchFor,store);
        ctx.json(Map.of("result","Done"));
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
        ScrapperProcess.results.put("woolworths", ScrapperProcess.itemsWool);
        ScrapperProcess.results.put("pnp", ScrapperProcess.itemsPnp);
        ScrapperProcess.results.put("shoprite", ScrapperProcess.itemsShop);

        ctx.json(ScrapperProcess.results);
    }
}
