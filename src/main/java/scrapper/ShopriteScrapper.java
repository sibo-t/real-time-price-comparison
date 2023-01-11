package scrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShopriteScrapper {

    public static HashMap<String, Double> searchItems(String theItem) throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("start-maximized");

        WebDriver driver = new ChromeDriver(options);
        HashMap<String, Double> Items = new HashMap<>();
        try {
            // Navigate to Url
            driver.get("https://www.shoprite.co.za/search/all?q="+theItem);

            TimeUnit.SECONDS.sleep(5);

            // Enter text "q" and perform keyboard action "Enter"
            driver.findElement(By.className("pdp")).click();

            TimeUnit.SECONDS.sleep(5);

            List<WebElement> description = driver.findElements(By.className("pdp__name"));

            List<WebElement> prices = driver.findElements(By.className("special-price"));

            // description and prices are assumed to have the same size
            for (int i = 0; i< description.size(); i++){
                Items.put(description.get(i).getText(),convertToDouble(prices.get(i).getText()));
            }

        } finally {
            driver.quit();
        }
        return Items;
    }

    private static double convertToDouble(String price){
        StringBuilder rand = new StringBuilder();
        StringBuilder cent = new StringBuilder("00.");

        for (int i = 1; i<price.length();i++){
            if(i<price.length()-2){
                rand.append(price.charAt(i));
            }
            else{
                cent.append(price.charAt(i));
            }
        }
        return Double.parseDouble(rand.toString())+Double.parseDouble(cent.toString());
    }
    
}
