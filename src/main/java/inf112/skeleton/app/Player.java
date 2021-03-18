package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Cards.Cards;

public class Player {

//    private static int[][] a = {{16,8,23,32,24},{32,30,24,8,16,23},{32,29,8,31,16,24},{31,16,24,8,29,32}};

    private int direction;
    private String name;

    public Player(int direction, String name) {
        this.direction = direction; //TODO bytt til enum
        this.name = name;
    }

    public String setName(String name) {
        return this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getDirection() {
        return direction;
    }

    //Define player-coordinates
    public Vector2 playerPosition;

    //Define player states
    public TiledMapTileLayer.Cell playerFigure;

    public void create(int px, int py, TiledMapTileLayer playerLayer) {

        // Set grid coordinate for playerFigure
        playerPosition = new Vector2(px, py);

        //Initialize playerFigure States
        playerFigure = new TiledMapTileLayer.Cell();
    }


    /**
     * @param card Card with directional value
     *             Moves the player to new location
     */
    public void move(Cards card) {
        int x = 0;
        int y = 0;

        Direction dir = null;

        direction = (direction + card.getDirection()) % 4;

        if (direction == 0) {
            y = card.getMomentum();
            dir = Direction.NORTH;
        }
        if (direction == 1) {
            x = card.getMomentum();
            dir = Direction.WEST;
        }
        if (direction == 2) {
            y = -card.getMomentum();
            dir = Direction.SOUTH;
        }
        if (direction == 3) {
            x = -card.getMomentum();
            dir = Direction.EAST;
        }

        if (dir == null) throw new IllegalArgumentException("The direction can't be null");
        //Handling of backward movement
        int magnitude = (dir == Direction.WEST || dir == Direction.SOUTH) ? -1 : 1;
        if (card.getMomentum() == -1) {

            magnitude = (dir == Direction.WEST || dir == Direction.SOUTH) ? 1 : -1;
            if (direction == 0) {
                y = -card.getMomentum();
                dir = Direction.SOUTH;
            }
            if (direction == 1) {
                x = -card.getMomentum();
                dir = Direction.EAST;
            }
            if (direction == 2) {
                y = card.getMomentum();
                dir = Direction.NORTH;
            }
            if (direction == 3) {
                x = card.getMomentum();
                dir = Direction.WEST;
            }
        }

        //Movement handling
        for (int i = 0; i < Math.abs(card.getMomentum()); i++) {
            if (x != 0 && canMove(dir)) {
                playerPosition.x += magnitude;
            }
            if (y != 0 && canMove(dir)) {
                playerPosition.y += magnitude;
            }
        }
    }

    /**
     * Checks if player can move to location
     *
     * @param direction player pointing direction
     * @return true if can move false if cannot move
     */
    public boolean canMove(Direction direction) {

        int x = (int) playerPosition.x;
        int y = (int) playerPosition.y;

        int cellCurrentlyOn = Board.isCellWall(x, y) ? Board.walls.getCell(x, y).getTile().getId() : -1;

        int x_change = 0;
        int y_change = 0;

        if (direction == Direction.WEST) {
            x_change = -1;
        }
        if (direction == Direction.EAST) {
            x_change = 1;
        }
        if (direction == Direction.NORTH) {
            y_change = 1;
        }
        if (direction == Direction.SOUTH) {
            y_change = -1;
        }
        //TODO Skriv om til en funksjon som tar for seg de forskjellige veridene

        int cellMovingTo = (Board.walls.getCell(x + x_change, y + y_change) == null) ? -1 : Board.walls.getCell(x + x_change, y + y_change).getTile().getId();

        if (direction == Direction.WEST) {
            return cellMovingTo != 16 && cellMovingTo != 8 && cellMovingTo != 23 && cellCurrentlyOn != 32 && cellCurrentlyOn != 30 && cellCurrentlyOn != 24;
        }
        if (direction == Direction.EAST) {
            return cellMovingTo != 32 && cellMovingTo != 30 && cellMovingTo != 24 && cellCurrentlyOn != 8 && cellCurrentlyOn != 16 && cellCurrentlyOn != 23;
        }
        if (direction == Direction.NORTH) {
            return cellMovingTo != 32 && cellMovingTo != 29 && cellMovingTo != 8 && cellCurrentlyOn != 31 && cellCurrentlyOn != 16 && cellCurrentlyOn != 24;
        }
        if (direction == Direction.SOUTH) {
            return cellMovingTo != 31 && cellMovingTo != 16 && cellMovingTo != 24 && cellCurrentlyOn != 8 && cellCurrentlyOn != 29 && cellCurrentlyOn != 32;
        }

        return true;
    }
}
