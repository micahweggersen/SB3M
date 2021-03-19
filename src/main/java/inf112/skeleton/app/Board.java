package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Board {

    public static TiledMap map;

    //Define board-layers for board, player, flag and hole
    public static TiledMapTileLayer boardLayer;
    public static TiledMapTileLayer playerLayer;
    public static  TiledMapTileLayer flagLayer;
    public static TiledMapTileLayer holeLayer;
    public static TiledMapTileLayer walls;

    public static void clear(TiledMapTileLayer layer) {
        for (int x = 0; x < layer.getWidth(); x++) {
            for (int y = 0; y < layer.getHeight(); y++) {
                layer.setCell(x, y, null);
            }
        }
    }

    public static boolean isCellFlag() { return flagLayer.getCell((int) Player.playerPosition.x, (int) Player.playerPosition.y) != null; }
    public static boolean isCellHole() { return holeLayer.getCell((int) Player.playerPosition.x, (int) Player.playerPosition.y) != null; }
    public static boolean isCellWall(int x, int y) { return walls.getCell( x, y) != null; }

}