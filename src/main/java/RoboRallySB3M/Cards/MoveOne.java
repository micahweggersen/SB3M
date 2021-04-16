package RoboRallySB3M.Cards;

import RoboRallySB3M.Direction;

public class MoveOne extends Cards {

    /** See Card class
     * @param priorityValue int
     * @param id String
     * @param direction direction
     * @param momentum int
     */

    public MoveOne(int priorityValue, String id, Direction direction, int momentum) {
        super(priorityValue, "Move One", Direction.NORTH, 1);
    }
}
