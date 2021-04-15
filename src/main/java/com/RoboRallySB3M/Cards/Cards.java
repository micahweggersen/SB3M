package com.RoboRallySB3M.Cards;

import com.RoboRallySB3M.Direction;

public class Cards {
    private int priorityValue;
    private String id;
    private int momentum;
    private Direction direction;

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
