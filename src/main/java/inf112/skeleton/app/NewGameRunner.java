package inf112.skeleton.app;

import com.badlogic.gdx.Input;import inf112.skeleton.app.Cards.Deck;
import inf112.skeleton.app.Cards.Cards;

import java.util.ArrayList;

public class NewGameRunner {

    private static Deck deck = new Deck();
    private static ArrayList<Cards> programmedCards = new ArrayList<>();
    private static ArrayList<Cards> chosenCards = new ArrayList<>();

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.createDeck();


        dealCards();
        System.out.println(programmedCards.get(0).toString());
        System.out.println(programmedCards.get(1).toString());
        System.out.println(programmedCards.get(2).toString());
        System.out.println(programmedCards.get(3).toString());
        System.out.println(programmedCards.get(4).toString());
    }

    public NewGameRunner() { }

    public static void dealCards(){
        programmedCards = deck.getCards(9);
    }


    public void keyUp(int keycode){

        if (keycode == Input.Keys.NUM_1) {
            chosenCards.add(programmedCards.get(0));
        }
        if (keycode == Input.Keys.NUM_2) {
            chosenCards.add(programmedCards.get(1));
        }
        if (keycode == Input.Keys.NUM_3) {
            chosenCards.add(programmedCards.get(2));
        }
        if (keycode == Input.Keys.NUM_4) {
            chosenCards.add(programmedCards.get(3));
        }
        if (keycode == Input.Keys.NUM_5) {
            chosenCards.add(programmedCards.get(4));
        }
        if (keycode == Input.Keys.NUM_6) {
            chosenCards.add(programmedCards.get(5));
        }
        if (keycode == Input.Keys.NUM_7) {
            chosenCards.add(programmedCards.get(6));
        }
        if (keycode == Input.Keys.NUM_8) {
            chosenCards.add(programmedCards.get(7));
        }
        if (keycode == Input.Keys.NUM_9) {
            chosenCards.add(programmedCards.get(8));
        }


    }
}
