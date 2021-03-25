package RoboRallySB3M;

import RoboRallySB3M.Network.Data.PlayerData;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

import java.util.*;

public class Laser {

    public static TiledMapTileLayer.Cell laserH;
    public static TiledMapTileLayer.Cell laserV;
    public static TiledMapTileLayer.Cell laserVH;
    private static List<LaserData> laserLocation;
    private static HashMap<String, LaserData> laserLocationDraw;
    private static ArrayList<Vector2> coor_xy;
    private static Direction dir;

    public static void createLaser() {

        laserH = new TiledMapTileLayer.Cell();
        laserV = new TiledMapTileLayer.Cell();
        laserVH = new TiledMapTileLayer.Cell();

        Texture texture = new Texture("src/assets/tiles.png"); //henter ut segment av et bilde og ikke tile verider derfor kommer ikke verdiene til tile med
        TextureRegion[][] laserFig = TextureRegion.split(texture, 300, 300);
        laserH.setTile(new StaticTiledMapTile(laserFig[4][6]));
        laserV.setTile(new StaticTiledMapTile(laserFig[5][6]));
        laserVH.setTile(new StaticTiledMapTile(laserFig[4][7]));
        laserLocation = new ArrayList<>();
        laserLocationDraw = new HashMap<>();


        for (int x = 0; x < Board.boardLayer.getHeight(); x++) {
            for (int y = 0; y < Board.boardLayer.getWidth(); y++) {
                if (Board.walls.getCell(x, y) != null && Board.walls.getCell(x, y).getTile().getProperties().containsKey("Laser")) {
                    laserLocation.add(LaserData.newLaser("laser", x, y));
                }
            }
        }
    }

    public static void drawLaser(List<PlayerData> playerData){

        for (LaserData v : laserLocation) {

            Direction dir = Direction.oppositeDirection(
                    Direction.stringToDirection(Board.walls.getCell(v.x, v.y).getTile().getProperties().get("direction").toString()));

            int x_change = Direction.changeInXdir(dir);
            int y_change = Direction.changeInYdir(dir);

            int x = v.x;
            int y = v.y;

            TiledMapTileLayer.Cell placeholder = laserH;
            if (Board.walls.getCell(v.x, v.y).getTile().getProperties().get("Laser").equals("H")) {
                Board.laserHorizontal.setCell(x, y, laserH);
                while (PlayerServer.canMove(dir, x, y)) {
                    if(x > Board.boardLayer.getWidth() || x < 0) break;
                    Board.laserHorizontal.setCell(x+x_change, y, laserH);
                    x += x_change;
                }
            }
            placeholder = laserV;
            if (Board.walls.getCell(v.x, v.y).getTile().getProperties().get("Laser").equals("V")) {
                Board.laserVertical.setCell(x, y, laserV);
                while (PlayerServer.canMove(dir, x, y)) {
                    if (playerWall(x , y, playerData)) {
                        placeholder = null;
                    }
                    Board.laserVertical.setCell(x, y + y_change, placeholder);

                    String key = String.valueOf(x) + String.valueOf(y);
                    laserLocationDraw.put(key, LaserData.newLaser("laserV", x, y));

                    if (y > Board.boardLayer.getHeight() || y < 0) break;
                    y += y_change;

                }
            }
        }
    }

    private static boolean playerWall(int x, int y, List<PlayerData> playerList) {

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

}