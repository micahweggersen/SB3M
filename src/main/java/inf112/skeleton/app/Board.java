package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Cards.Cards;

public class Board extends InputAdapter implements ApplicationListener {

    private SpriteBatch batch;
    private BitmapFont font;

    private TiledMap map;

    //Define board-layers for board, player, flag and hole
    private TiledMapTileLayer boardLayer;
    private TiledMapTileLayer playerLayer;
    private TiledMapTileLayer flagLayer;
    private TiledMapTileLayer holeLayer;
    public static TiledMapTileLayer walls;

    //Define view states
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera cameraView;

    private Player player;

    @Override
    public void create() {
        player = new Player(0);
        player.create();

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        //Tile file load
        map = new TmxMapLoader().load("src/assets/example.tmx");

        //Representation on GUI map
        boardLayer = (TiledMapTileLayer) map.getLayers().get("Board");
        flagLayer = (TiledMapTileLayer) map.getLayers().get("Flag");
        holeLayer = (TiledMapTileLayer) map.getLayers().get("Hole");
        playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");
        walls = (TiledMapTileLayer) map.getLayers().get("Walls");

        //Size of camera in relation to map size
        cameraView = new OrthographicCamera();
        cameraView.setToOrtho(false, 5, 5);
        cameraView.position.x = 2.5F;

        //Define playerLayer coordinate and playerFigure
        playerLayer.setCell(0,0,Player.playerFigure);
        cameraView.update();

        mapRenderer = new OrthogonalTiledMapRenderer(map, (float) (1.0/300.0));

        Gdx.input.setInputProcessor(this);
    }

//    private void Texture(String s) {
//    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        playerLayer.setCell((int)Player.playerPosition.x, (int)Player.playerPosition.y, Player.playerFigure);

        //playerLayer.setCell( player.move(), (int)Player.playerPosition.y, Player.playerFigure);


        //Check if player-figure is on a cell that effect the state/visual of the player, win-state, lose-state and default state.
        if(isCellFlag(Player.playerPosition)) playerLayer.setCell((int) Player.playerPosition.x, (int) Player.playerPosition.y, Player.playerFigureHasWon.setRotation(player.getDirection()));
        else if(isCellHole(Player.playerPosition)) playerLayer.setCell((int) Player.playerPosition.x, (int) Player.playerPosition.y, Player.playerFigureHasDied.setRotation(player.getDirection()));
        else playerLayer.setCell((int) Player.playerPosition.x, (int) Player.playerPosition.y, Player.playerFigure.setRotation(player.getDirection()));
        mapRenderer.setView(cameraView);
        mapRenderer.render();


    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public boolean keyUp(int keycode){

        playerLayer.setCell((int) Player.playerPosition.x, (int) Player.playerPosition.y, null);

        if (keycode == Input.Keys.NUM_1){ player.move(new Cards(0, "Move One", 0 , 1)); }
        if (keycode == Input.Keys.NUM_2){ player.move(new Cards(0, "Move Two", 0, 2)); }
        if (keycode == Input.Keys.NUM_3){ player.move(new Cards(0, "Move Three", 0, 3)); }
        if (keycode == Input.Keys.NUM_4){ player.move(new Cards(0, "Rotate Left",1,0)); }
        if (keycode == Input.Keys.NUM_5){ player.move(new Cards(0, "Rotate Right",2,0)); }
        if (keycode == Input.Keys.NUM_6){ player.move(new Cards(0, "U-Turn",3,0)); }

        if (keycode == Input.Keys.LEFT && Player.canMove((int) Player.playerPosition.x, (int) Player.playerPosition.y, Direction.WEST)) { Player.playerPosition = new Vector2(Player.playerPosition.x - 1, Player.playerPosition.y); }
        if (keycode == Input.Keys.RIGHT && Player.canMove((int) Player.playerPosition.x, (int) Player.playerPosition.y, Direction.EAST)) { Player.playerPosition = new Vector2(Player.playerPosition.x + 1, Player.playerPosition.y); }
        if (keycode == Input.Keys.UP && Player.canMove((int) Player.playerPosition.x, (int) Player.playerPosition.y, Direction.NORTH)) { Player.playerPosition = new Vector2(Player.playerPosition.x, Player.playerPosition.y + 1); }
        if (keycode == Input.Keys.DOWN && Player.canMove((int) Player.playerPosition.x, (int) Player.playerPosition.y, Direction.SOUTH)) { Player.playerPosition = new Vector2(Player.playerPosition.x, Player.playerPosition.y - 1); }
//
        playerLayer.setCell((int) Player.playerPosition.x, (int) Player.playerPosition.y, Player.playerFigure);
//
        return (keycode == Input.Keys.LEFT || keycode == Input.Keys.RIGHT || keycode == Input.Keys.UP || keycode == Input.Keys.DOWN); //return keycode != 0;
    }

    private boolean isCellFlag(Vector2 playerPosition) { return flagLayer.getCell((int) playerPosition.x, (int) playerPosition.y) != null; }
    private boolean isCellHole(Vector2 playerPosition) { return holeLayer.getCell((int) playerPosition.x, (int) playerPosition.y) != null; }
    public static boolean isCellWall(Vector2 playerPosition) { return walls.getCell((int) playerPosition.x, (int) playerPosition.y) != null; }
}
