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

    public static Direction stringToDirection(String s) {
        switch (s) {
            case "SOUTH":
                return SOUTH;
            case "EAST":
                return EAST;
            case "NORTH":
                return NORTH;
            case "WEST":
                return WEST;
        }
        return null;
    }

    public static int changeInXdir(Direction dir){
        switch (dir) {
            case SOUTH:
            case NORTH:
                return 0;
            case EAST:
                return 1;
            case WEST:
                return -1;
        }
        return 0;
    }

    public static int changeInYdir(Direction dir){
        switch (dir) {
            case WEST:
            case EAST:
                return 0;
            case NORTH:
                return 1;
            case SOUTH:
                return -1;
        }
        return 0;
    }

}



