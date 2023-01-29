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

            //We retrieve both the price and description all at once
            List<WebElement> descriptionPrice = driver.findElements(By.className("product-list__item"));

            String[] firstPair = descriptionPrice.remove(0).getText().split("\n");
            Items.put(firstPair[0], convertToDouble(firstPair[firstPair.length-1]));
            for (WebElement x:descriptionPrice
                 ) {
                String[] descriptionPlusPrice = x.getText().split("\n");

                if(descriptionPlusPrice.length==5){
                    Items.put(descriptionPlusPrice[2], convertToDouble(descriptionPlusPrice[descriptionPlusPrice.length-1]));
                }
                if(descriptionPlusPrice.length==4){
                    Items.put(descriptionPlusPrice[1], convertToDouble(descriptionPlusPrice[descriptionPlusPrice.length-1]));
                }
                if(descriptionPlusPrice.length==3){
                    //Sometimes there is an issue of "" in first index
                    int indicator = descriptionPlusPrice[0].length();
                    if(indicator==1){
                        Items.put(descriptionPlusPrice[1], convertToDouble(descriptionPlusPrice[descriptionPlusPrice.length-1]));
                    }
                    else{
                        Items.put(descriptionPlusPrice[0], convertToDouble(descriptionPlusPrice[descriptionPlusPrice.length-1]));
                    }
                }

            }
        } finally {
            driver.quit();
        }
        return Items;
    }

}