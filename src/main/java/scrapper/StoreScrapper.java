package scrapper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

abstract class StoreScrapper {

    protected static double convertToDouble(String price){
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

    protected static WebDriver initializeDriver(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("start-maximized");
        options.addArguments("--no-sandbox");

        return new ChromeDriver(options);
    }

}
