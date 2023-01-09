package scrapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

public class WooliesScrapperTest {

    @Test
    void searchForCake(){
        HashMap<String,Double> product = new HashMap<>();
        try {
            product = WooliesScrapper.searchItems("Madeira Butter Cake");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(!product.isEmpty());

        //I did things
        Assertions.assertEquals(52.99, product.get("Madeira Butter Cake Loaf 260 g"));

    }

}
