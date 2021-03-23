package RoboRallySB3M.Cards;

import RoboRallySB3M.Direction;

public class RotateLeft extends Cards {

    /** See Card class
     * @param priorityValue int
     * @param id String
     * @param direction direction
     * @param momentum int
     */

    public RotateLeft(int priorityValue, String id, Direction direction, int momentum) {
        super(priorityValue, "Rotate Left", Direction.EAST, 0);
    }
}
