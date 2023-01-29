package scrapper;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShopriteScrapper extends StoreScrapper{

    public static HashMap<String, Double> searchItems(String theItem) throws InterruptedException {

        WebDriver driver = initializeDriver();
        HashMap<String, Double> Items = new HashMap<>();
        try {
            // Navigate to Url
            driver.get("https://www.shoprite.co.za/search/all?q="+theItem);

           //Retrieve the descriptions and prices of searched items
            List<WebElement> content = driver.findElements(
                    By.className("row")
            );

            // description and prices should have the same size
            for (int i = 0; i< content.size(); i++){
                String attributes = content.get(content.size()-1).getAttribute("data-product-ga");
                JSONArray attributeArray = new JSONArray(attributes);
                JSONObject productJson = attributeArray.getJSONObject(i);

                String price = productJson.get("price").toString();
                String description = productJson.get("name").toString();
                Items.put(description,convertToDouble(price));
            }

        } finally {
            driver.quit();
        }
        return Items;
    }
    
}
