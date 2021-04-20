package RoboRallySB3M.GameObjects;

import RoboRallySB3M.Direction;
import RoboRallySB3M.Network.Data.LaserData;
import RoboRallySB3M.Network.Data.PlayerData;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import java.util.*;

public class Laser {

    private TiledMapTileLayer.Cell laserH;
    private TiledMapTileLayer.Cell laserV;


    /**
     * Creating laser from tileset and initializing list for storing laser locations.
     * Finds laser walls on the board and stores there location.
     */
    public void initializeLaser() {

        laserH = new TiledMapTileLayer.Cell();
        laserV = new TiledMapTileLayer.Cell();


        Texture texture = new Texture("src/assets/tiles.png");
        TextureRegion[][] laserFig = TextureRegion.split(texture, 300, 300);
        laserH.setTile(new StaticTiledMapTile(laserFig[4][6]));
        laserV.setTile(new StaticTiledMapTile(laserFig[5][6]));

    }

    public void drawLaser(HashMap<String, LaserData> laserData, List<PlayerData> playerData) {

        for (int t = 0; t <= 1; t++) {
            int k = 0;
            if(laserData == null || playerData == null) {
                break;
            }
            String pointing = t == 0 ? "V" : "H";
            String laserType = "";
            for (int i = 0; i < Board.boardLayer.getHeight()-1; i++) {
                for (int j = 0; j < Board.boardLayer.getWidth()-1; j++) {
                    String key = i + String.valueOf(j) + pointing;
                    if (laserData.containsKey(key)) {
                        if(playerWall(playerData,laserData.get(key).x, laserData.get(key).y)) {
                            draw(laserData.get(key).x, laserData.get(key).y, "null");
                        }
                        draw(laserData.get(key).x, laserData.get(key).y, laserData.get(key).laserType);
                    }

                }
            }
        }
    }
        private void draw(int x, int y, String laserType) {
            TiledMapTileLayer.Cell placeholder;
            if (laserType.equals("laserV")) {
                placeholder = laserV;
                Board.laserVertical.setCell(x, y, placeholder);
            }
            else if (laserType.equals("laserH")) {
                placeholder = laserH;
                Board.laserHorizontal.setCell(x, y, placeholder);
            }
            else {
                Board.laserVertical.setCell(x, y, null);
                Board.laserHorizontal.setCell(x, y, null);
            }
    }

    private boolean playerWall(List<PlayerData> players, int x, int y) {

        if(players == null) return false;

        for (PlayerData player : players) {
            if ((int) player.position.x == x && (int) player.position.y == y) {
                return true;
            }
        }
        return false;
    }
/*    *//**
     * @param x coordinate
     * @param y coordinate
     * @param playerList list of current players
     * @return if player location is the same as drawn laser location then return true - this stops the laser form being drawn
     *//*
    private static boolean isCellLaser(int x, int y, List<PlayerData> playerList) {

        if(playerList == null || laserLocationDraw.size() <= 0) {
            return false;
        }

        for (PlayerData player : playerList) {
            String key = String.valueOf(x) + String.valueOf(y);
            if ((int) player.position.x == laserLocationDraw.get(key).x && (int) player.position.y == laserLocationDraw.get(key).y) {

                return true;
            }
        }
        return false;
    }

    public static boolean getLaserPosition(int x, int y) {
        String key = String.valueOf(x) + String.valueOf(y);
        System.out.println("Laser");
        if (laserLocationDraw.get(key) != null) {
            System.out.println("IFLaser");
            return true;
        }
        return false;
    }*/

}