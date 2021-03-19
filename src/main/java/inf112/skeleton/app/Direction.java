package inf112.skeleton.app;

/**
 * Direction Keywords
 */
public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public static Direction intToDirection(int i){
        switch (i){
            case 0: return NORTH;
            case 1: return WEST;
            case 2: return SOUTH;
            case 3: return EAST;
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



