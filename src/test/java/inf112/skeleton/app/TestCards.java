package inf112.skeleton.app;

import static org.junit.Assert.*;

import inf112.skeleton.app.Cards.Cards;
import inf112.skeleton.app.Cards.MoveTwo;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TestCards {

    Cards card = new MoveTwo(270, "Rotate Left", Cards.ROTATE_0, -1);

    @Test
    public void testGetValueOfCard(){
        int value = card.getPriorityValue();
        assertEquals(270, card.getPriorityValue());
    }


}
