package inf112.skeleton.app.Cards;

import com.badlogic.gdx.Input;

import java.util.*;


public class Deck {


    public Queue<Cards> cardQueue;
    ArrayList<Cards> dealtCards = new ArrayList<>();
    Queue<Cards> chosenCards = new LinkedList<>();

    public Deck() {
        createDeck();
        shuffleDeck();
    }

    public ArrayList<Cards> dealCards(int amount){
        for (int i = 0; i <= amount; i++){
            dealtCards.add(cardQueue.poll());
        }
        System.out.println("choose cards now! you can choose from these cards" + dealtCards.toString());
        return dealtCards;
    }

    public boolean keyUp(int keycode){

        if(keycode==Input.Keys.NUM_1){
            chosenCards.add(dealtCards.get(0));
            dealtCards.set(0,null); //sånn at man ikke kan velge det samme to ganger
        }
        if(keycode==Input.Keys.NUM_2){
            chosenCards.add(dealtCards.get(1));
            dealtCards.set(1,null);
        }
        if(keycode==Input.Keys.NUM_3){
            chosenCards.add(dealtCards.get(2));
            dealtCards.set(2,null);
        }
        if(keycode==Input.Keys.NUM_4){
            chosenCards.add(dealtCards.get(3));
            dealtCards.set(3,null);
        }
        if(keycode==Input.Keys.NUM_5){
            chosenCards.add(dealtCards.get(4));
            dealtCards.set(4,null);
        }
        if(keycode==Input.Keys.NUM_6){
            chosenCards.add(dealtCards.get(5));
            dealtCards.set(5,null);
        }
        if(keycode==Input.Keys.NUM_7){
            chosenCards.add(dealtCards.get(6));
            dealtCards.set(6,null);
        }
        if(keycode==Input.Keys.NUM_8){
            chosenCards.add(dealtCards.get(7));
            dealtCards.set(7,null);
        }
        if(keycode==Input.Keys.NUM_9){
            chosenCards.add(dealtCards.get(8));
            dealtCards.set(8,null);
        }
        return keycode != 0;
    }
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

