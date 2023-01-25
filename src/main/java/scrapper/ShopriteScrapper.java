package scrapper;

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

            TimeUnit.SECONDS.sleep(5);
            
           //Retrieve the descriptions and prices of searched items
            List<WebElement> description = driver.findElements(
                    By.className("item-product__details")
            );

            List<WebElement> prices = driver.findElements(By.className("special-price__price"));

            // description and prices should have the same size
            for (int i = 0; i< description.size(); i++){
                String price = prices.get(i).getText();
                Items.put(description.get(i).getText(),convertToDouble(price));
            }

        } finally {
            driver.quit();
        }
        return Items;
    }
    
}
