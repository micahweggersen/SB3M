package com.RoboRallySB3M.Cards;

import com.RoboRallySB3M.Direction;

public class UTurn extends Cards {

    /** See Card class
     * @param priorityValue int
     * @param id String
     * @param direction direction
     * @param momentum int
     */
    
    public UTurn(int priorityValue, String id, Direction direction, int momentum) {
        super(priorityValue, "U-Turn", Direction.SOUTH, 0);
    }
}
