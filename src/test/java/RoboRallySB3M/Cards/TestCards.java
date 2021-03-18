package RoboRallySB3M.Cards;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Unit tests for the Cards class
 */
public class TestCards {

    Cards moveTwoCard = new MoveTwo(270, "Move Two", Cards.ROTATE_0, 2);
    Cards rotateRightCard = new RotateRight(80, "Rotate Right", Cards.ROTATE_90, 0);
    Cards moveOneCard = new MoveOne(270, "Move One", Cards.ROTATE_0, 1);
    Cards moveThreeCard = new MoveThree(270, "Move Three", Cards.ROTATE_0, 3);
    Cards backUpCard = new BackUp(180, "Back Up", Cards.ROTATE_0, -1);
    Cards rotateLeftCard = new RotateLeft(180, "Rotate Left", Cards.ROTATE_270, 0);
    Cards uTurnCard = new UTurn(270, "U-Turn", Cards.ROTATE_180, 0);

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

    @Test
    public void getValueOfMoveOneCard(){
        int value = moveOneCard.getPriorityValue();
        assertEquals(270, value);
    }

    @Test
    public void getIdOfMoveOneCard(){
        String id = moveOneCard.getId();
        assertEquals("Move One", id);
    }

    @Test
    public void getValueOfMoveThreeCard(){
        int value = moveThreeCard.getPriorityValue();
        assertEquals(270, value);
    }

    @Test
    public void getValueOfBackUpCard(){
        int value = backUpCard.getPriorityValue();
        assertEquals(180, value);
    }

    @Test
    public void getValueOfRotateLeftCard(){
        int value = rotateLeftCard.getPriorityValue();
        assertEquals(180, value);
    }

    @Test
    public void getValueOfUTurnCard(){
        int value = uTurnCard.getPriorityValue();
        assertEquals(270, value);
    }




}
