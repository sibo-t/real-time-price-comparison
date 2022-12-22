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

    public static HashMap<String, String> searchItems(String theItem) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        HashMap<String, String> Items = new HashMap<>();
        try {
            // Navigate to Url
            driver.get("https://www.pnp.co.za/");

            TimeUnit.SECONDS.sleep(5);

            // Enter text "q" and perform keyboard action "Enter"
            driver.findElement(By.name("policiesCloseButton")).click();

            TimeUnit.SECONDS.sleep(5);

            // Find the search bar, enter search item and press ENTER
            driver.findElement(By.id("js-site-search-input")).sendKeys(theItem + Keys.ENTER);

            TimeUnit.SECONDS.sleep(5);

            List<WebElement> description = driver.findElements(By.className("item-name"));

            List<WebElement> prices = driver.findElements(By.className("item-price"));

            // description and prices are assumed to have the same size
            for (int i = 0; i< description.size(); i++){
                Items.put(description.get(i).getText(),prices.get(i).getText());
            }

        } finally {
            driver.quit();
        }
        return Items;
    }
}
