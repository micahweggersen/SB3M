package roboRallySB3M.Cards;

import roboRallySB3M.Direction;

public class RotateRight extends Cards {

    /** See Card class
     * @param priorityValue int
     * @param id String
     * @param direction direction
     * @param momentum int
     */

    public RotateRight(int priorityValue, String id, Direction direction, int momentum) {
        super(priorityValue, "Rotate Right", Direction.WEST, 0);
    }
}
