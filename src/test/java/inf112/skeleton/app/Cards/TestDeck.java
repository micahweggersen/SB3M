package inf112.skeleton.app.Cards;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Unit tests for the Deck class
 */
public class TestDeck {

    Deck deck = new Deck();


    @Test
    public void testPriorityValueOfUTurnWithinRange(){
        for (Cards card : deck.cardQueue) {
            if(card.getId().equals("U-Turn")) {
                assertTrue(card.getPriorityValue() >= 10 && card.getPriorityValue() <= 60 );
            }
        }
    }
    @Test
    public void testPriorityValueOfRotateLeftWithinRange(){
        for (Cards card : deck.cardQueue) {
            if(card.getId().equals("Rotate Left")) {
                assertTrue(card.getPriorityValue() >= 70 && card.getPriorityValue() <= 430 );
            }
        }
    }

    @Test
    public void testPriorityValueOfRotateRightWithinRange(){
        for (Cards card : deck.cardQueue) {
            if(card.getId().equals("Rotate Right")) {
                assertTrue(card.getPriorityValue() >= 80 && card.getPriorityValue() <= 440 );
            }
        }
    }

    @Test
    public void testPriorityValueOfBackUpWithinRange(){
        for (Cards card : deck.cardQueue) {
            if(card.getId().equals("Back Up")) {
                assertTrue(card.getPriorityValue() >= 430 && card.getPriorityValue() <= 490 );
            }
        }
    }

    @Test
    public void testPriorityValueOfMoveOneWithinRange(){
        for (Cards card : deck.cardQueue) {
            if(card.getId().equals("Move One")) {
                assertTrue(card.getPriorityValue() >= 490 && card.getPriorityValue() <= 670 );
            }
        }
    }

    @Test
    public void testShuffledDeckIsNotTheSame() {
        Deck oldDeck = deck;
        deck.shuffleDeck();
        assertNotEquals(deck.getCards(10), oldDeck.getCards(10));
    }

    @Test
    public void testAmountMoveTwoCards() {
        int count = 0;
        for (Cards card : deck.cardQueue) {
            if (card.getId().equals("Move Two"))
                count++;
        }
        assertEquals(count, 12);
    }

    @Test
    public void testAmountRotateLeftCards() {
        int count = 0;
        for (Cards card : deck.cardQueue){
            if (card.getId().equals("Rotate Left"))
                count++;
        }
        assertEquals(count, 18);
    }

    @Test
    public void testMomentumMoveThreeCards(){
        for (Cards card : deck.cardQueue) {
            if (card.getId().equals("Move Three"))
                assertEquals(3, card.getMomentum());
        }
    }

    @Test
    public void testMomentumBackUpCards(){
        for (Cards card : deck.cardQueue) {
            if (card.getId().equals("Back Up"))
                assertEquals(-1, card.getMomentum());
        }
    }

    @Test
    public void testDirectionMoveOneCards(){
        for (Cards card : deck.cardQueue) {
            if (card.getId().equals("Move One"))
                assertEquals(0, card.getDirection());
        }
    }

    @Test
    public void testDirectionUTurnCards(){
        for (Cards card : deck.cardQueue) {
            if (card.getId().equals("U-Turn"))
                assertEquals(2, card.getDirection());
        }
    }

    //deal cards = amount

    @Test
    public void testDealtCardsIsRightAmount(){
        ArrayList<Cards> dealtCards = deck.dealCards(8);
        assertEquals(9, dealtCards.size());
    }


}
