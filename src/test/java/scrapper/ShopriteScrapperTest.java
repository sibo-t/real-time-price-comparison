
package scrapper;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class ShopriteScrapperTest {
    @Test
    void searchForOmo(){
        HashMap<String,Double> item = new HashMap<>();
        try {
            item = ShopriteScrapper.searchItems("omo");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(!item.isEmpty());
//
//        Assertions.assertTrue(!item.containsKey(""));

//        Assertions.assertEquals("hgh", item.keySet());
        //I did things
        Assertions.assertEquals(13.99, item.get("Omo Hand Washing Powder Detergent 300g"));
    }
    
}
