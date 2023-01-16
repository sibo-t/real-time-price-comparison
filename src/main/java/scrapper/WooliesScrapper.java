package scrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WooliesScrapper extends StoreScrapper{

    public static HashMap<String, Double> searchItems(String theItem) throws InterruptedException {

        WebDriver driver = initializeDriver();

        HashMap<String, Double> Items = new HashMap<>();

        try {
            // Navigate to Url
            driver.get("https://www.woolworths.co.za/cat?Ntt=" + theItem);

            TimeUnit.SECONDS.sleep(5);

//            TODO: Sometimes cookies are not on screen so it cant find them
//            // Enter text "q" and perform keyboard action "Enter"
//            driver.findElement(By.className("cookie-banner-accept")).click();
//
//            TimeUnit.SECONDS.sleep(5);

            List<WebElement> description = driver.findElements(By.className("range--title"));

            List<WebElement> prices = driver.findElements(By.className("product-card__actions"));

//            TODO: Sometimes prices and description are not the same size which crashes, e.g seaching for bread

//             description and prices are assumed to have the same size
            for (int i = 0; i< description.size(); i++){

                if (!Items.containsKey((description.get(i).getText()))) {
                    Items.put(description.get(i).getText(), convertToDouble(prices.get(i).getText()));
                }
            }

        } finally {
            driver.quit();
        }
        return Items;
    }

}