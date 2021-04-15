package roboRallySB3M.Cards;

import static org.junit.Assert.*;

import roboRallySB3M.Direction;
import org.junit.Test;


/**
 * Unit tests for the Cards class
 */
public class TestCards {

    Cards moveTwoCard = new MoveTwo(270, "Move Two", Direction.NORTH, 2);
    Cards rotateRightCard = new RotateRight(80, "Rotate Right", Direction.WEST, 0);
    Cards moveOneCard = new MoveOne(270, "Move One", Direction.NORTH, 1);
    Cards moveThreeCard = new MoveThree(270, "Move Three", Direction.NORTH, 3);
    Cards backUpCard = new BackUp(180, "Back Up", Direction.NORTH, -1);
    Cards rotateLeftCard = new RotateLeft(180, "Rotate Left", Direction.EAST, 0);
    Cards uTurnCard = new UTurn(270, "U-Turn", Direction.SOUTH, 0);

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
        Direction direction = moveTwoCard.getDirection();
        assertEquals(Direction.NORTH, direction);
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
        Direction direction = rotateRightCard.getDirection();
        assertEquals(Direction.WEST, direction);
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
