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

    public static Direction toDirection(int direction) {
        switch (direction) {
            case 0:
                return NORTH;
            case 1:
                return WEST;
            case 2:
                return SOUTH;
            case 3:
                return EAST;
            default:
                return null;
        }
    }

    public static Direction oppositeDirection(Direction direction) {
        switch (direction) {
            case SOUTH:
                return NORTH;
            case EAST:
                return WEST;
            case NORTH:
                return SOUTH;
            case WEST:
                return EAST;
            default:
                return null;
        }
    }

    public static Direction stringToDirection(String direction) {
        switch (direction) {
            case "SOUTH":
                return SOUTH;
            case "EAST":
                return EAST;
            case "NORTH":
                return NORTH;
            case "WEST":
                return WEST;
            default:
                return null;
        }
    }

    public static int changeInDirectionX(Direction direction) {
        switch (direction) {
            case SOUTH:
            case NORTH:
                return 0;
            case EAST:
                return 1;
            case WEST:
                return -1;
            default:
        }
        return 0;
    }

    public static int changeInDirectionY(Direction direction) {
        switch (direction) {
            case SOUTH:
                return -1;
            case NORTH:
                return 1;
            case EAST:
            case WEST:
                return 0;
            default:
        }
        return 0;
    }

}