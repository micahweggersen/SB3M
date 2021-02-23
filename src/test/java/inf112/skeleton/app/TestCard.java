package inf112.skeleton.app;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

/**
 * Unit test for simple App.
 */
public class TestCard {

    Cards card = new Cards("test", 1,2);


    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void test_get_value_of_card(){
//        Cards card = new Cards("test", 1,1);
        int value = card.get_value();
        assertEquals(1, value);
    }

    @Test
    public void test_get_id_of_card(){
//        Cards card = new Cards("test", 1,1);
        String id = card.get_id();
        assertEquals("test", id);
    }
    @Test
    public void test_get_magnitude_of_card(){
//        Cards card = new Cards("test", 1,1);
        int magnitude = card.get_magnitude_of_direction();
        assertEquals(2, magnitude);
    }

}
