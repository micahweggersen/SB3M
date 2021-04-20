package RoboRallySB3M.Cards;

import RoboRallySB3M.Direction;

public class Cards {
    private int priorityValue;
    private String id;
    private int momentum;
    private Direction direction;
    public int cardType;

    /**
     * Constructor. Used by sub-classes.
     *
     * @param priorityValue The value that a card has in order to decide priority of the card at start of each round.
     * @param id            The id/type of the card
     */
    public Cards(int priorityValue, String id, Direction direction, int momentum) {
        this.momentum = momentum;
        this.direction = direction;
        this.priorityValue = priorityValue;
        this.id = id;
    }

    /**
     * Gets the priority value a card has.
     *
     * @return the priority value
     */
    public int getPriorityValue() {
        return priorityValue;
    }

    /**
     * Gets the id of a card
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    public int getIdInt(Cards card){
        switch(card.getId()) {
            case "Move One":
                cardType = 0;
                return cardType;
            case "Move Two":
                cardType = 1;
                return cardType;
            case "Move Three":
                cardType = 2;
                return cardType;
            case "Back Up":
                cardType = 3;
                return cardType;
            case "Rotate Left":
                cardType = 4;
                return cardType;
            case "Rotate Right":
                cardType = 5;
                return cardType;
            case "U-Turn":
                cardType = 6;
                return cardType;
            default:
                return Integer.parseInt("Unexpected value: " + cardType);
        }
    }

    @Override
    public String toString() {
    return id + ", priorityValue: " + priorityValue + '}';
    }

    /**
     * Returns the momentum of the given card
     *
     * @return momentum of gard
     */
    public int getMomentum() {
        return momentum;
    }

    /**
     * Returns the direction of the given card
     *
     * @return the direction of a card
     */
    public Direction getDirection() {
        return direction;
    }
}
