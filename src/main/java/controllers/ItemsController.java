package controllers;

import io.javalin.http.Context;

import io.javalin.http.HttpCode;
import io.netty.handler.codec.http.HttpResponseStatus;
import kong.unirest.HttpStatus;
import models.Items;
import scrapper.PicknPayScrapper;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ItemsController {
    private static final ArrayList<Items> items = new ArrayList<>();

    public static void processItem(Context ctx) throws InterruptedException {

        String itemToSearchFor = ctx.body();

        HashMap<String, Double> foundItems = PicknPayScrapper.searchItems(itemToSearchFor);

        //TODO: implement a better method to handle errors
        if (foundItems.size() >=1){
            foundItems.forEach((description, price) -> {
                items.add(new Items(description,price));
            });
            ctx.json(HttpStatus.OK);
        }
        else {
            ctx.json(HttpStatus.NOT_FOUND);
        }
    }

    public static void viewItem(Context ctx){
        ctx.json(Objects.requireNonNullElseGet(items, () -> new Items("Empty", 0.00)));
    }
}
