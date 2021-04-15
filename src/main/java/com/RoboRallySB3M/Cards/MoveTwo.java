package com.RoboRallySB3M.Cards;

import com.RoboRallySB3M.Direction;

public class MoveTwo extends Cards {

    /** See Card class
     * @param priorityValue int
     * @param id String
     * @param direction direction
     * @param momentum int
     */

    public MoveTwo(int priorityValue, String id, Direction direction, int momentum) {
        super(priorityValue, "Move Two", Direction.NORTH, 2);
    }
}
