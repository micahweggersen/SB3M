package com.RoboRallySB3M.Cards;

import com.RoboRallySB3M.Direction;

public class BackUp extends Cards {

    /**
     * See Card class
     *
     * @param priorityValue int
     * @param id            String
     * @param direction     direction
     * @param momentum      int
     */
    public BackUp(int priorityValue, String id, Direction direction, int momentum) {
        super(priorityValue, "Back Up", Direction.NORTH, -1);
    }
}
