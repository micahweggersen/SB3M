package inf112.skeleton.app.Network.Data;

import inf112.skeleton.app.Cards.Cards;

public class MoveCardData implements PayloadData {
    public int cardPV;
    public int cardMom;
    public int cardDir;
    public String cardID;
    public String playerName;

    public static MoveCardData create(String playerName, Cards card) {
        return new MoveCardData(playerName, card);
    }

    private MoveCardData(String playerName, Cards card) {
        this.playerName = playerName;
        this.cardPV = card.getPriorityValue();
        this.cardID = card.getId();
        this.cardMom = card.getMomentum();
        this.cardDir = card.getDirection();
    }
}
