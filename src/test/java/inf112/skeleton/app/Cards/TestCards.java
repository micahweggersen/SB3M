package inf112.skeleton.app.Cards;

import static org.junit.Assert.*;

import inf112.skeleton.app.Cards.Cards;
import inf112.skeleton.app.Cards.MoveTwo;
import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class TestCards {

    Cards card = new MoveTwo(270, "Move Two", Cards.ROTATE_0, 2);

    @Test
    public void testGetValueOfCard(){
        int value = card.getPriorityValue();
        assertEquals(270, value);
    }

    @Test
    public void testGetIdOfCard(){
        String id = card.getId();
        assertEquals("Move Two", id);
    }

    @Test
    public void testGetDirectionOfCard(){
        int direction = card.getDirection();
        assertEquals(Cards.ROTATE_0, direction);
    }

    @Test
    public void testGetMomentumOfCard(){
        int momentum = card.getMomentum();
        assertEquals(2, momentum);
    }

}
