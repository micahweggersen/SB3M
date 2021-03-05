package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Cards.Cards;

import java.util.Arrays;

public class Player {

//    private static int[][] a = {{16,8,23,32,24},{32,30,24,8,16,23},{32,29,8,31,16,24},{31,16,24,8,29,32}};

    private static int direction;
    public Player(int direction){
        this.direction = direction; //bytt til enum
    }

    public static int getDirection(){ return direction; }

    //Define player-coordinates
    public static Vector2 playerPosition;

    //Define player states
    public static TiledMapTileLayer.Cell playerFigure;
    public static TiledMapTileLayer.Cell playerFigureHasWon;
    public static TiledMapTileLayer.Cell playerFigureHasDied;

    public void create(){

        // Set grid coordinate for playerFigure
        playerPosition = new Vector2(0, 0);

        //Load player figure and set size
        Texture texture = new Texture("src/assets/player.png");
        TextureRegion[][] playerFig = TextureRegion.split(texture, 300, 300);

        //Initialize playerFigure States
        playerFigure = new TiledMapTileLayer.Cell();
        playerFigureHasDied = new TiledMapTileLayer.Cell();
        playerFigureHasWon = new TiledMapTileLayer.Cell();

        //playerFigure States
        playerFigure.setTile(new StaticTiledMapTile(playerFig[0][0]));
        playerFigureHasDied.setTile(new StaticTiledMapTile(playerFig[0][1]));
        playerFigureHasWon.setTile(new StaticTiledMapTile(playerFig[0][2]));
    }

    /**
     * Decides player image according to legal placement on board
     * @param playerLayer Image of player character
     */
    public static void setPlayerFigure(TiledMapTileLayer playerLayer) {
        int x = (int)playerPosition.x;
        int y = (int)playerPosition.y;

        if(Board.isCellFlag()) playerLayer.setCell(x, y, Player.playerFigureHasWon.setRotation(Player.getDirection()));
        else if(Board.isCellHole()) playerLayer.setCell(x, y, Player.playerFigureHasDied.setRotation(Player.getDirection()));
        else playerLayer.setCell(x, y, Player.playerFigure.setRotation(Player.getDirection()));

    }

    /**
     * @param card Card with directional value
     *             Moves the player to new location
     */
    public void move(Cards card) {

        int x = 0;
        int y = 0;

        Direction dir = null;

        direction = (direction + card.getDirection())%4;

        if(direction == 0) {y =  card.getMomentum();  dir = Direction.NORTH;}
        if(direction == 1) {x =  card.getMomentum(); dir = Direction.WEST;}
        if(direction == 2) {y = -card.getMomentum(); dir = Direction.SOUTH;}
        if(direction == 3) {x = -card.getMomentum(); dir = Direction.EAST;}

        if (dir == null) throw new IllegalArgumentException("The direction can't be null");

        int magnitude = (dir == Direction.WEST || dir == Direction.SOUTH) ? -1 : 1;
        System.out.println(magnitude);

        if(card.getMomentum() == -1){
//
            magnitude = (dir == Direction.WEST || dir == Direction.SOUTH) ? 1 : -1;
            System.out.println(magnitude);
            if(direction == 0) {y =  -card.getMomentum();  dir = Direction.SOUTH;}
            if(direction == 1) {x =  -card.getMomentum(); dir = Direction.EAST;}
            if(direction == 2) {y = card.getMomentum(); dir = Direction.NORTH;}
            if(direction == 3) {x = card.getMomentum(); dir = Direction.WEST;}
        }


        for(int i = 0; i < Math.abs(card.getMomentum()); i++){
            if(x!=0 && canMove(dir)) {playerPosition = new Vector2(playerPosition.x + magnitude, playerPosition.y);}
            if(y!=0 && canMove(dir)) {playerPosition = new Vector2( playerPosition.x, playerPosition.y + magnitude);}
        }
    }

    /**
     * Checks if player can move to location
     * @param direction player pointing direction
     * @return true if can move false if cannot move
     */
    public static boolean canMove(Direction direction){

        int x = (int)playerPosition.x;
        int y = (int)playerPosition.y;

        int cellCurrentlyOn = Board.isCellWall() ? Board.walls.getCell(x, y).getTile().getId() : -1;

        int x_change = 0;
        int y_change = 0;

        if(direction == Direction.WEST) {x_change =  -1;}
        if(direction == Direction.EAST) {x_change =   1;}
        if(direction == Direction.NORTH) {y_change = 1;}
        if(direction == Direction.SOUTH) {y_change = -1;}


        int cellMovingTo = (Board.walls.getCell(x+x_change, y+y_change) == null) ? -1 : Board.walls.getCell(x+x_change, y+y_change).getTile().getId();

        if(direction == Direction.WEST){ return cellMovingTo  != 16 && cellMovingTo != 8 && cellMovingTo != 23 && cellCurrentlyOn != 32 && cellCurrentlyOn != 30 && cellCurrentlyOn != 24; }
        if(direction == Direction.EAST){ return cellMovingTo  != 32 && cellMovingTo != 30 && cellMovingTo != 24 && cellCurrentlyOn != 8 && cellCurrentlyOn != 16 && cellCurrentlyOn != 23; }
        if(direction == Direction.NORTH){ return cellMovingTo != 32 && cellMovingTo != 29 && cellMovingTo != 8 && cellCurrentlyOn != 31 && cellCurrentlyOn != 16 && cellCurrentlyOn != 24; }
        if(direction == Direction.SOUTH){ return cellMovingTo != 31 && cellMovingTo != 16 && cellMovingTo != 24 && cellCurrentlyOn != 8 && cellCurrentlyOn != 29 && cellCurrentlyOn != 32; }

        return true;
    }
}
