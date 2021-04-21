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

    public void drawLaser(HashMap<String, LaserData> laserData, List<PlayerData> playerData, List<LaserData> laserLocation){

        for (LaserData v : laserLocation) {

            Direction dir = Direction.oppositeDirection(
                    Objects.requireNonNull(Direction.stringToDirection(Board.walls.getCell(v.x, v.y).getTile().getProperties().get("direction").toString())));

            int xChange = Direction.changeInDirectionX(dir);
            int yChange = Direction.changeInDirectionY(dir);

            int x = v.x;
            int y = v.y;
            //Draws lasers Horizontal
            TiledMapTileLayer.Cell placeholder = laserH;
            if (Board.walls.getCell(v.x, v.y).getTile().getProperties().get("Laser").equals("H")) {
                Board.laserHorizontal.setCell(x, y, laserH);
                while (canMove(dir, x, y)) {
                    //If laser hits a player, set the laser draw value to null
                    if (playerWall(playerData, x , y)) {
                        placeholder = null;
                    }
                    Board.laserHorizontal.setCell(x+xChange, y, placeholder);
                    if(x > Board.boardLayer.getWidth() || x < 0 ) break;
                    x += xChange;
                }
            }
            //Draws lasers Vertical
            placeholder = laserV;
            if (Board.walls.getCell(v.x, v.y).getTile().getProperties().get("Laser").equals("V")) {
                Board.laserVertical.setCell(x, y, laserV);
                while (canMove(dir, x, y)) {
                    //If laser hits a player, set the laser draw value to null
                    if (playerWall(playerData, x , y)) {
                        placeholder = null;
                    }
                    Board.laserVertical.setCell(x, y + yChange, placeholder);
                    if (y > Board.boardLayer.getHeight() || y < 0) break;
                    y += yChange;

                }
            }
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


    /**
     * This method is duplicate as it cannot be reached from a client perspective while the original method is on
     * server side.
     * @param direction a
     * @param oldX a
     * @param oldY a
     * @return a
     */
    private boolean canMove(Direction direction, int oldX, int oldY) {
        int xChange = Direction.changeInDirectionX(direction);
        int yChange = Direction.changeInDirectionY(direction);

        if (Board.walls.getCell(oldX, oldY) != null && Board.walls.getCell(oldX, oldY).getTile().getProperties().containsKey(direction.toString())) {
            return false;
        }
        if (Board.walls.getCell((oldX + xChange), oldY + yChange) != null) {
            return (!Board.walls.getCell(oldX + xChange, oldY + yChange).getTile().getProperties()
                    .containsKey(Objects.requireNonNull(Direction.oppositeDirection(direction)).toString()));
        }
        return true;
    }

}