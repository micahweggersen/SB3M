package inf112.skeleton.app;

import inf112.skeleton.app.Cards.Deck;
import inf112.skeleton.app.Cards.Cards;

import java.util.ArrayList;

public class GameRunner {

    private static Deck deck = new Deck();
    private static ArrayList<Cards> programmedCards = new ArrayList<>();

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

    public GameRunner() { }

    public static void dealCards(){ programmedCards = deck.getCards(5); }
}
