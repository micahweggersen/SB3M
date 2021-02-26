package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

public class Player {
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


}
