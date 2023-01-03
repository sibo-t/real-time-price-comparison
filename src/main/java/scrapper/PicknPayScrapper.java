package scrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PicknPayScrapper {

    public static HashMap<String, Double> searchItems(String theItem) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        HashMap<String, Double> Items = new HashMap<>();
        try {
            // Navigate to Url
            driver.get("https://www.pnp.co.za/pnpstorefront/pnp/en/search/?text="+theItem);

            TimeUnit.SECONDS.sleep(5);

            // Enter text "q" and perform keyboard action "Enter"
            driver.findElement(By.name("policiesCloseButton")).click();

            TimeUnit.SECONDS.sleep(5);

            List<WebElement> description = driver.findElements(By.className("item-name"));

            List<WebElement> prices = driver.findElements(By.className("item-price"));

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
