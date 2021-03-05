package inf112.skeleton.app.Cards;


import inf112.skeleton.app.Player;

import java.util.*;


public class Deck {

    public Queue<Cards> cardQueue;
    ArrayList<Cards> dealtCards = new ArrayList<>();

    //creates and shuffles deck
    public Deck() {
        createDeck();
        shuffleDeck();
    }
    //checks if the stack of cards the player has chosen is full, if it is, it makes the player move
    public static Boolean checkCardStatus(Queue<Cards> chosenCards, Player player, Boolean chooseCardsNow) {
        if (chosenCards.size() >= 5) {
            chooseCardsNow = false;
            System.out.println("enough choosing!");
            for (Cards card : chosenCards) {
                player.move(card);
            }
        }
        return chooseCardsNow;
    }
    //deals the given amount of cards and removes them from the cardQueue,
    //and tells the player that they can start choosing cards
    public ArrayList<Cards> dealCards(int amount){
        for (int i = 0; i <= amount; i++){
            dealtCards.add(cardQueue.poll());
        }
        System.out.println("press the keys in the priority of the cards you want! you can choose from these cards: \n" + dealtCardsList());
        return dealtCards;
    }
    //print-method for a neat display of the cards to choose from, prompted by dealCards
    public String dealtCardsList() {
        int count = 1;
        String cardList = "";
        for (Cards card : dealtCards) {
            cardList += (count + ": " + card + "\n");
            count++;
        }
        return cardList;
    }

    //creates the deck and adds all the different cards with the right amount of priorityValue.
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

    //used mainly for tests, gives the desired amount of cards and removes them from cardQueue
    public ArrayList<Cards> getCards(int desiredAmount) {
        ArrayList<Cards> temporaryDeck = new ArrayList<>();
        for (int i = 0; i < desiredAmount; i++) {
            temporaryDeck.add(cardQueue.poll());
        }
        return temporaryDeck;
    }

    //shuffles the deck
    public void shuffleDeck(){
        Collections.shuffle((List<?>) cardQueue);
    }

}

