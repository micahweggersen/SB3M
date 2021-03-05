package inf112.skeleton.app;


import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
public class Board {


    public static TiledMap map;

    //Define board-layers for board, player, flag and hole
    public static TiledMapTileLayer boardLayer;
    public static TiledMapTileLayer playerLayer;
    public static  TiledMapTileLayer flagLayer;
    public static TiledMapTileLayer holeLayer;
    public static TiledMapTileLayer walls;


//    public boolean legalValue(int keycode) { return Arrays.stream(numberKeyValues).anyMatch(i -> i == keycode); }
//    public boolean isNumberKey(int keycode){
//        for (int i : numberKeyValues){
//            return i == keycode;
//        }
//        return false;
//    }

    public static boolean isCellFlag() { return Board.flagLayer.getCell((int) Player.playerPosition.x, (int) Player.playerPosition.y) != null; }
    public static boolean isCellHole() { return Board.holeLayer.getCell((int) Player.playerPosition.x, (int) Player.playerPosition.y) != null; }
    public static boolean isCellWall() { return Board.walls.getCell((int) Player.playerPosition.x, (int) Player.playerPosition.y) != null; }

}