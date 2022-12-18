package controllers;

import io.javalin.http.Context;

import models.Items;

public class ItemsController {
    private static Items items;

    public static void processItem(Context ctx){
        Items item = ctx.bodyAsClass(Items.class);
        String price = item.getPrice();
        String description = item.getDescription();

        ctx.json( items = new Items(description,price));
    }

    public static void viewItem(Context ctx){
        ctx.result(items.toString());
    }
}
