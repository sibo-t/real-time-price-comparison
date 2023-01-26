package scrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PicknPayScrapper extends StoreScrapper{

    public static HashMap<String, Double> searchItems(String theItem) throws InterruptedException {

        WebDriver driver = initializeDriver();
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
                //Handles new price and old price error when casting to double
                String[] price = prices.get(i).getText().split(" ");

                Items.put(description.get(i).getText(),convertToDouble(price[0]));
            }

        } finally {
            driver.quit();
        }
        return Items;
    }

}
