package scrapper;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

public class PicknPayScrapperTest {

    @Test
    void searchForSnowballs(){
        HashMap<String,String> snowballs = new HashMap<>();
        try {
            snowballs = PicknPayScrapper.searchItems("snowball");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(!snowballs.isEmpty());

        //Luyanda said I must push even though the price is not right
        Assertions.assertEquals(snowballs.toString(), "4500");
    }
}
