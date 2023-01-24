package scrapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

public class WooliesScrapperTest {

    @Test
    void searchForCake(){
        HashMap<String,Double> product = new HashMap<>();
        try {
            product = WooliesScrapper.searchItems("cake");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(!product.isEmpty());

        //I did things
        Assertions.assertEquals(209.99, product.get("Carrot Gateau Cake 1 kg"));

    }

}
