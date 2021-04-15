package com.RoboRallySB3M.Network.Data;

import com.RoboRallySB3M.Cards.Cards;
import com.RoboRallySB3M.Direction;

public class MoveCardData implements PayloadData {
    public int cardPV;
    public int cardMom;
    public Direction cardDir;
    public String cardID;
    public String playerName;

    /**
     * @param playerName player ID - name
     * @param card Card type with values
     * @return MoveCardData type
     */
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
