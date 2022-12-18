package controllers;

import io.javalin.http.Context;

import models.Items;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class ItemsController {
    private static Items items;

    //Mock Store data
    final static List<String> store = List.of(
            "Sasko brown bread R20",
            "Vanilla cake R100",
            "Golden Apples R50"
    );

    public static void processItem(Context ctx){

        String itemToSearchFor = ctx.body();

        //TODO: @Kwanele Do your magic bellow

        String found = store.stream().filter( i -> i.contains(itemToSearchFor))
                .findFirst().orElse(null);
        if (found != null){
            String description = found.split("R")[0];
            String price = found.split("R")[1];

            ctx.json( items = new Items(description,price));
        }
    }

    public static void viewItem(Context ctx){
        ctx.json(Objects.requireNonNullElseGet(items, () -> new Items("Empty", "None")));
    }
}
