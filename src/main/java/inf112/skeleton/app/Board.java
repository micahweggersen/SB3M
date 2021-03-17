package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Board {

    public static TiledMap map;

    //Define board-layers for board, player, flag and hole
    public static TiledMapTileLayer boardLayer;
    public static TiledMapTileLayer playerLayer;
    public static TiledMapTileLayer flagLayer;
    public static TiledMapTileLayer holeLayer;
    public static TiledMapTileLayer walls;

    public static boolean isCellFlag(Player player) {
        return Board.flagLayer.getCell((int) player.playerPosition.x, (int) player.playerPosition.y) != null;
    }

    public static boolean isCellHole(Player player) {
        return Board.holeLayer.getCell((int) player.playerPosition.x, (int) player.playerPosition.y) != null;
    }

    public static boolean isCellWall(Player player) {
        return Board.walls.getCell((int) player.playerPosition.x, (int) player.playerPosition.y) != null;
    }
}