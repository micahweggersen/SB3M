package RoboRallySB3M;

/**
 * Direction Keywords
 */
public enum Direction {
    NORTH(0), WEST(1), SOUTH(2), EAST(3);

    public final int value;

    Direction(int value) {
        this.value = value;
    }

    public static Direction toDirection(int dir) {
        switch (dir) {
            case 0:
                return NORTH;
            case 1:
                return WEST;
            case 2:
                return SOUTH;
            case 3:
                return EAST;
        }
        return null;
    }


    public static Direction oppositeDirection(Direction dir) {
        switch (dir) {
            case SOUTH:
                return NORTH;
            case EAST:
                return WEST;
            case NORTH:
                return SOUTH;
            case WEST:
                return EAST;
        }
        return null;
    }

}