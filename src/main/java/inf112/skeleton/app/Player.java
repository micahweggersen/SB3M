package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Cards.Cards;

public class Player {

    private int direction;
    public Player(int direction){
        this.direction = direction; //bytt til enum
    }

    public int getDirection(){
        return this.direction;
    }

    public static Vector2 playerPosition;
    //Define player-coordinates
//    private Vector2 playerPosition;

    public static TiledMapTileLayer.Cell playerFigure;
    public static TiledMapTileLayer.Cell playerFigureHasWon;
    public static TiledMapTileLayer.Cell playerFigureHasDied;
    //Define player states
//    private TiledMapTileLayer.Cell playerFigure;
//    private TiledMapTileLayer.Cell playerFigureHasWon;
//    private TiledMapTileLayer.Cell playerFigureHasDied;


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


    public void move(Cards card){
        // TODO: 02/03/2021 finnes en error her når man flytter seg kan man gå igjennom vegger (se posisjon 0,0 dir = east, card = momentum 3) 

        int x = 0;
        int y = 0;

        Direction dir = null;
        this.direction = (this.direction + card.getDirection())%4;

        if(this.direction == 0) {y = card.getMomentum();  dir = Direction.NORTH;}
        if(this.direction == 1) {x = -card.getMomentum(); dir = Direction.EAST;}
        if(this.direction == 2) {y = -card.getMomentum(); dir = Direction.SOUTH;}
        if(this.direction == 3) {x = card.getMomentum(); dir = Direction.WEST;}

        int magnitude;
        if(dir == Direction.EAST || dir == Direction.SOUTH) {magnitude = -1;} else {magnitude = 1;}

        for(int i = 0; i < card.getMomentum(); i++){
            
        }




        for (int i = 0; i < card.getMomentum(); i++){
            if (x>0) {
                if(this.canMove((int)this.playerPosition.x, (int)this.playerPosition.y, dir)) {
                    this.playerPosition = new Vector2(this.playerPosition.x + 1*magnitude, this.playerPosition.y);

                }
            }
            if (y>0) {
                if(this.canMove((int)this.playerPosition.x, (int)this.playerPosition.y, dir)) {
                    this.playerPosition = new Vector2(this.playerPosition.x, this.playerPosition.y + 1*magnitude);
                }
            }

        }

//        if(this.canMove((int)this.playerPosition.x + x, (int)this.playerPosition.y + y, dir)) {this.playerPosition = new Vector2(this.playerPosition.x + x, this.playerPosition.y + y);}
//        this.playerPosition = new Vector2(this.playerPosition.x + x, this.playerPosition.y + y);
    }

    public static boolean canMove(int x, int y, Direction direction){

        int cellCurrentlyOn;
        int cellMovingTo;

        if(Board.isCellWall(Player.playerPosition)) { cellCurrentlyOn = Board.walls.getCell(x, y).getTile().getId(); }
        else{ cellCurrentlyOn = -1; }

        if(direction == Direction.WEST){
            if(Board.walls.getCell((int) Player.playerPosition.x-1, (int) Player.playerPosition.y) == null) {cellMovingTo = -1;}
            else {cellMovingTo = Board.walls.getCell(x-1, y).getTile().getId();}
            return cellMovingTo != 16 && cellMovingTo != 8 && cellMovingTo != 23 && cellCurrentlyOn != 32 && cellCurrentlyOn != 30 && cellCurrentlyOn != 24;
        }
        if(direction == Direction.EAST){
            if(Board.walls.getCell((int) Player.playerPosition.x+1, (int) Player.playerPosition.y) == null) {cellMovingTo = -1;}
            else {cellMovingTo = Board.walls.getCell(x+1, y).getTile().getId();}
            return cellMovingTo != 32 && cellMovingTo != 30 && cellMovingTo != 24 && cellCurrentlyOn != 8 && cellCurrentlyOn != 16 && cellCurrentlyOn != 23;
        }
        if(direction == Direction.NORTH){
            if(Board.walls.getCell((int) Player.playerPosition.x, (int) Player.playerPosition.y+1) == null) {cellMovingTo = -1;}
            else {cellMovingTo = Board.walls.getCell(x, y+1).getTile().getId();}
            return cellMovingTo != 32 && cellMovingTo != 29 && cellMovingTo != 8 && cellCurrentlyOn != 31 && cellCurrentlyOn != 16 && cellCurrentlyOn != 24;
        }
        if(direction == Direction.SOUTH){
            if(Board.walls.getCell((int) Player.playerPosition.x, (int) Player.playerPosition.y-1) == null) {cellMovingTo = -1;}
            else {cellMovingTo = Board.walls.getCell(x, y-1).getTile().getId();}
            return cellMovingTo != 31 && cellMovingTo != 16 && cellMovingTo != 24 && cellCurrentlyOn != 8 && cellCurrentlyOn != 29 && cellCurrentlyOn != 32;
        }
        return true;
    }
}
