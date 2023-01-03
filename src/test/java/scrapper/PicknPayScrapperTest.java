package scrapper;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class PicknPayScrapperTest {

    @Test
    void searchForSnowballs(){
        HashMap<String,Double> snowballs = new HashMap<>();
        try {
            snowballs = PicknPayScrapper.searchItems("snowball");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(!snowballs.isEmpty());

        //I did things
        Assertions.assertEquals(45.00, snowballs.get("PnP Mini Snowballs 10s"));
    }
}
