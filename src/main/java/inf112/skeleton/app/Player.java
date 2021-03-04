package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Cards.Cards;

import static java.lang.Thread.sleep;

public class Player {

    private static int direction;
//    private int direction;
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

    public static void setPlayerFigure(TiledMapTileLayer playerLayer) {
        if(Board.isCellFlag(Player.playerPosition)) playerLayer.setCell((int) Player.playerPosition.x, (int) Player.playerPosition.y, Player.playerFigureHasWon.setRotation(Player.getDirection()));
        else if(Board.isCellHole(Player.playerPosition)) playerLayer.setCell((int) Player.playerPosition.x, (int) Player.playerPosition.y, Player.playerFigureHasDied.setRotation(Player.getDirection()));
        else playerLayer.setCell((int) Player.playerPosition.x, (int) Player.playerPosition.y, Player.playerFigure.setRotation(Player.getDirection()));

    }

    public void move(Cards card, TiledMapTileLayer playerLayer) throws InterruptedException {

        int x = 0;
        int y = 0;

        Direction dir = null;

        direction = (direction + card.getDirection())%4;

        //skrive til en switch case
        if(direction == 0) {y = card.getMomentum();  dir = Direction.NORTH;}
        if(direction == 1) {x = card.getMomentum(); dir = Direction.WEST;}
        if(direction == 2) {y = -card.getMomentum(); dir = Direction.SOUTH;}
        if(direction == 3) {x = -card.getMomentum(); dir = Direction.EAST;}

        if (dir == null) throw new IllegalArgumentException("The direction can't be null");

        int magnitude;

        if(dir == Direction.WEST || dir == Direction.SOUTH) {magnitude = -1;} else {magnitude = 1;} //skriv denne om til kort versjon

        for(int i = 0; i < card.getMomentum(); i++){
                if(x!=0 && canMove((int) playerPosition.x, (int) playerPosition.y, dir)) {playerPosition = new Vector2(playerPosition.x + magnitude, playerPosition.y);}
                if(y!=0 && canMove((int) playerPosition.x, (int) playerPosition.y, dir)) {playerPosition = new Vector2( playerPosition.x, playerPosition.y + magnitude);}
                playerLayer.setCell((int)Player.playerPosition.x, (int)Player.playerPosition.y, Player.playerFigure);
                sleep(200);
        }
    }

    public static boolean canMove(int x, int y, Direction direction){
        //se om man kan skrive denne uten x og y
        //se om man kan forenkle koden

        int cellCurrentlyOn;
        int cellMovingTo;

        if(Board.isCellWall(Player.playerPosition)) { cellCurrentlyOn = Board.walls.getCell(x, y).getTile().getId(); } else{ cellCurrentlyOn = -1; }

        if(direction == Direction.WEST){
            if(Board.walls.getCell((int) playerPosition.x-1, (int) playerPosition.y) == null) {cellMovingTo = -1;}
            else {cellMovingTo = Board.walls.getCell(x-1, y).getTile().getId();}
            return cellMovingTo != 16 && cellMovingTo != 8 && cellMovingTo != 23 && cellCurrentlyOn != 32 && cellCurrentlyOn != 30 && cellCurrentlyOn != 24;
        }
        if(direction == Direction.EAST){
            if(Board.walls.getCell((int) playerPosition.x+1, (int) playerPosition.y) == null) {cellMovingTo = -1;}
            else {cellMovingTo = Board.walls.getCell(x+1, y).getTile().getId();}
            return cellMovingTo != 32 && cellMovingTo != 30 && cellMovingTo != 24 && cellCurrentlyOn != 8 && cellCurrentlyOn != 16 && cellCurrentlyOn != 23;
        }
        if(direction == Direction.NORTH){
            if(Board.walls.getCell((int) playerPosition.x, (int) playerPosition.y+1) == null) {cellMovingTo = -1;}
            else {cellMovingTo = Board.walls.getCell(x, y+1).getTile().getId();}
            return cellMovingTo != 32 && cellMovingTo != 29 && cellMovingTo != 8 && cellCurrentlyOn != 31 && cellCurrentlyOn != 16 && cellCurrentlyOn != 24;
        }
        if(direction == Direction.SOUTH){
            if(Board.walls.getCell((int) playerPosition.x, (int) playerPosition.y-1) == null) {cellMovingTo = -1;}
            else {cellMovingTo = Board.walls.getCell(x, y-1).getTile().getId();}
            return cellMovingTo != 31 && cellMovingTo != 16 && cellMovingTo != 24 && cellCurrentlyOn != 8 && cellCurrentlyOn != 29 && cellCurrentlyOn != 32;
        }
        return true;
    }
}
