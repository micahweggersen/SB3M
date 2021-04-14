package RoboRallySB3M.GameObjects;

import RoboRallySB3M.Network.Data.LaserData;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import java.util.*;

public class Laser {

    public TiledMapTileLayer.Cell laserH;
    public TiledMapTileLayer.Cell laserV;

    /**
     * Creating laser from tileset and initializing list for storing laser locations.
     * Finds laser walls on the board and stores there location.
     */
    public void initializeLaser() {

        laserH = new TiledMapTileLayer.Cell();
        laserV = new TiledMapTileLayer.Cell();

        Texture texture = new Texture("src/assets/tiles.png"); //henter ut segment av et bilde og ikke tile verider derfor kommer ikke verdiene til tile med
        TextureRegion[][] laserFig = TextureRegion.split(texture, 300, 300);
        laserH.setTile(new StaticTiledMapTile(laserFig[4][6]));
        laserV.setTile(new StaticTiledMapTile(laserFig[5][6]));
    }

    public void drawLaser(HashMap<String, LaserData> laserData) {

        for (int i = 0; i < Board.boardLayer.getHeight(); i++) {
            for (int j = 0; j < Board.boardLayer.getWidth(); j++) {
                String key = String.valueOf(i) + String.valueOf(j);

                if(laserData == null) {
                    break;
                }
                if(laserData.containsKey(key)) {
                    int x = laserData.get(key).x;
                    int y = laserData.get(key).y;
                    String laserType = laserData.get(key).laserType;
                    if (laserType.equals("laserH")) {
                        Board.laserHorizontal.setCell(x, y, laserH);
                    }
                    if (laserType.equals("laserV")) {
                        Board.laserVertical.setCell(x, y, laserV);
                    }
                }
            }
        }
    }
}