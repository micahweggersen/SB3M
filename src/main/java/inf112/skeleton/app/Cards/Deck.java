package inf112.skeleton.app.Cards;

import java.util.*;


public class Deck {


    public Queue<Cards> cardQueue;

    public Deck() {
        createDeck();
        shuffleDeck();
    }


    //TODO: momentum og direction er i feil rekkefølge

    public void createDeck() {
        cardQueue = new LinkedList<>();

        // U_TURN Cards
        int priorityValue = 10;
        for (int i = 1; i <= 6; i++) {
            cardQueue.add(new UTurn(priorityValue, "U-Turn", 2, 0));
            priorityValue = priorityValue + 10;
        }
        // ROTATE_LEFT Cards
        priorityValue = 70;
        for (int i = 1; i <= 18; i++) {
            cardQueue.add(new RotateLeft(priorityValue, "Rotate Left", 3, 0));
            priorityValue = priorityValue + 20;
        }

        // ROTATE_RIGHT Cards
        priorityValue = 80;
        for (int i = 1; i <= 18; i++) {
            cardQueue.add(new RotateRight(priorityValue, "Rotate Right", 1, 0));
            priorityValue = priorityValue + 20;
        }

        // BACKUP Cards
        priorityValue = 430;
        for (int i = 1; i <= 6; i++) {
            cardQueue.add(new BackUp(priorityValue, "Back Up", 0, -1));
            priorityValue = priorityValue + 10;
        }

        // MOVE_ONE Cards
        priorityValue = 490;
        for (int i = 1; i <= 18; i++) {
            cardQueue.add(new MoveOne(priorityValue,"Move One", 0, 1));
            priorityValue = priorityValue + 10;
        }
        // MOVE_TWO Cards
        priorityValue = 670;
        for (int i = 1; i <= 12; i++) {
            cardQueue.add(new MoveTwo(priorityValue, "Move Two", 0, 2));
            priorityValue = priorityValue + 10;
        }
        // MOVE_THREE Cards
        priorityValue = 790;
        for (int i = 1; i <= 6; i++) {
            cardQueue.add(new MoveThree(priorityValue, "Move Three", 0,3));
            priorityValue = priorityValue + 10;
        }

    }

    public ArrayList<Cards> getCards(int desiredAmount) {
        ArrayList<Cards> temporaryDeck = new ArrayList<>();
        for (int i = 0; i < desiredAmount; i++) {
            temporaryDeck.add(cardQueue.poll());
        }
        return temporaryDeck;
    }

    public void shuffleDeck(){
        Collections.shuffle((List<?>) cardQueue);
    }


    public void getId(){
        for (Cards card : cardQueue) { System.out.println(card.getId() + " " + card.getPriorityValue()); }
    }

    public Cards giveNextCard() {
        return cardQueue.poll();
    }
}

