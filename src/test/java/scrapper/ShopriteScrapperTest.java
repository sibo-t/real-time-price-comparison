
package scrapper;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class ShopriteScrapperTest {
    @Test
    void searchForOmo(){
        HashMap<String,Double> item = new HashMap<>();
        try {
            item = PicknPayScrapper.searchItems("omo");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(!item.isEmpty());

        //I did things
        Assertions.assertEquals(42.99, item.get("Omo Multiactive Washing Powder 1kg"));
    }
    
}
