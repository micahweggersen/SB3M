package inf112.skeleton.app.Cards;

import java.util.ArrayList;
import java.util.Collections;


public class Deck {

    private ArrayList<UpdateCards> deck;

    public Deck() {
        createDeck();
        shuffleDeck();
    }

    public void createDeck() {
        deck = new ArrayList<>();

        // U_TURN Cards
        int priorityValue = 10;
        for (int i = 1; i <= 6; i++) {
            deck.add(new UTurn(priorityValue, "U-Turn"));
            priorityValue = priorityValue + 10;
        }
        // ROTATE_LEFT Cards
        priorityValue = 70;
        for (int i = 1; i <= 18; i++) {
            deck.add(new RotateLeft(priorityValue, "Rotate Left"));
            priorityValue = priorityValue + 20;
        }

        // ROTATE_RIGHT Cards
        priorityValue = 80;
        for (int i = 1; i <= 18; i++) {
            deck.add(new RotateRight(priorityValue, "Rotate Right"));
            priorityValue = priorityValue + 20;
        }

        // BACKUP Cards
        priorityValue = 430;
        for (int i = 1; i <= 6; i++) {
            deck.add(new BackUp(priorityValue, "Back Up"));
            priorityValue = priorityValue + 10;
        }

        // MOVE_ONE Cards
        priorityValue = 490;
        for (int i = 1; i <= 18; i++) {
            deck.add(new MoveOne(priorityValue,"Move One"));
            priorityValue = priorityValue + 10;
        }
        // MOVE_TWO Cards
        priorityValue = 670;
        for (int i = 1; i <= 12; i++) {
            deck.add(new MoveTwo(priorityValue, "Move Two"));
            priorityValue = priorityValue + 10;
        }
        // MOVE_THREE Cards
        priorityValue = 790;
        for (int i = 1; i <= 6; i++) {
            deck.add(new MoveThree(priorityValue, "Move Three"));
            priorityValue = priorityValue + 10;
        }

    }

    public ArrayList<UpdateCards> getCards(int desiredAmount) {
        ArrayList<UpdateCards> temporaryDeck = new ArrayList<>();
        for (int i = 0; i < desiredAmount; i++) {
            temporaryDeck.add(deck.get(i));
            deck.remove(i);
        }
        return temporaryDeck;
    }

    public void shuffleDeck(){ Collections.shuffle(deck); }

    @Override
    public String toString() { return "Deck{" + "deck=" + deck + '}'; }

    public void getId(){
        for (UpdateCards card : deck) { System.out.println(card.getId() + " " + card.getPriorityValue()); }
    }
}

