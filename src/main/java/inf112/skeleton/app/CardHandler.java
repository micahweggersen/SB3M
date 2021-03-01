package inf112.skeleton.app;

import inf112.skeleton.app.Cards.Deck;
import inf112.skeleton.app.Cards.UpdateCards;

import java.util.LinkedList;
import java.util.Queue;

public class CardHandler {

    public Queue<UpdateCards> cardQueue;

    public CardHandler(){
        UpdateCards card1 = new UpdateCards(0,"Move One");
        UpdateCards card2 = new UpdateCards(0, "Move Two");
        UpdateCards card3 = new UpdateCards(0, "Move Three");
        UpdateCards card4 = new UpdateCards(0, "Rotate Right");
        UpdateCards card5 = new UpdateCards(0, "Rotate Left");
        UpdateCards card6 = new UpdateCards(0, "U-Turn");

        cardQueue = new LinkedList<>();

        cardQueue.add(card1);
        cardQueue.add(card2);
        cardQueue.add(card3);
        cardQueue.add(card4);
        cardQueue.add(card5);
        cardQueue.add(card6);
    }
    public UpdateCards nextCard(){ return cardQueue.poll(); }
    //dem fikse en queue/giveNextcard metode
    //lage metodene getMomentum og getDirection

    //lager en queue med et kort av hver type
    //sender den til spiller via board
}
