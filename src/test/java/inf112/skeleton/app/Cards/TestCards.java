package inf112.skeleton.app.Cards;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Unit tests for the Cards class
 */
public class TestCards {

    Cards moveTwoCard = new MoveTwo(270, "Move Two", Cards.ROTATE_0, 2);
    Cards rotateRightCard = new RotateRight(80, "Rotate Right", Cards.ROTATE_90, 0);


    @Test
    public void testGetValueOfMoveTwoCard(){
        int value = moveTwoCard.getPriorityValue();
        assertEquals(270, value);
    }

    @Test
    public void testGetIdOfMoveTwoCard(){
        String id = moveTwoCard.getId();
        assertEquals("Move Two", id);
    }

    @Test
    public void testGetDirectionOfMoveTwoCard(){
        int direction = moveTwoCard.getDirection();
        assertEquals(Cards.ROTATE_0, direction);
    }

    @Test
    public void testGetMomentumOfMoveTwoCard(){
        int momentum = moveTwoCard.getMomentum();
        assertEquals(2, momentum);
    }

    @Test
    public void testGetValueOfRotateRightCard(){
        int value = rotateRightCard.getPriorityValue();
        assertEquals(80, value);
    }

    @Test
    public void testGetIdOfRotateRightCard(){
        String id = rotateRightCard.getId();
        assertEquals("Rotate Right", id);
    }

    @Test
    public void testGetDirectionOfRotateRightCard(){
        int direction = rotateRightCard.getDirection();
        assertEquals(Cards.ROTATE_90, direction);
    }

    @Test
    public void testGetMomentumOfRotateRightCard(){
        int momentum = rotateRightCard.getMomentum();
        assertEquals(0, momentum);
    }




}
