package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

public class HelloWorld extends InputAdapter implements ApplicationListener {

    private SpriteBatch batch;
    private BitmapFont font;

    private TiledMap map;

    //Define board-layers for board, player, flag and hole
    private TiledMapTileLayer boardLayer;
    private TiledMapTileLayer playerLayer;
    private TiledMapTileLayer flagLayer;
    private TiledMapTileLayer holeLayer;
    private TiledMapTileLayer walls;

    //Define view states
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera cameraView;

//    //Define player states
//    private TiledMapTileLayer.Cell playerFigure;
//    private TiledMapTileLayer.Cell playerFigureHasWon;
//    private TiledMapTileLayer.Cell playerFigureHasDied;

//    //Define player-coordinates
//    private Vector2 playerPosition;


    @Override
    public void create() {

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

//        //Load player figure and set size
//        Texture texture = new Texture("src/assets/player.png");
//        TextureRegion[][] playerFig = TextureRegion.split(texture, 300, 300);

        //Tile file load
        map = new TmxMapLoader().load("src/assets/example.tmx");

        //Representation on GUI map
        boardLayer = (TiledMapTileLayer) map.getLayers().get("Board");
        flagLayer = (TiledMapTileLayer) map.getLayers().get("Flag");
        holeLayer = (TiledMapTileLayer) map.getLayers().get("Hole");
        playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");
        walls = (TiledMapTileLayer) map.getLayers().get("Walls");

//        // Set grid coordinate for playerFigure
//        playerPosition = new Vector2(0, 0);

//        //Initialize playerFigure States
//        playerFigure = new TiledMapTileLayer.Cell();
//        playerFigureHasDied = new TiledMapTileLayer.Cell();
//        playerFigureHasWon = new TiledMapTileLayer.Cell();
//
//        //playerFigure States
//        playerFigure.setTile(new StaticTiledMapTile(playerFig[0][0]));
//        playerFigureHasDied.setTile(new StaticTiledMapTile(playerFig[0][1]));
//        playerFigureHasWon.setTile(new StaticTiledMapTile(playerFig[0][2]));

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

    private void Texture(String s) {
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        //batch.begin();
        //font.draw(batch, "Hello World", 200, 200);
        //batch.end();

        playerLayer.setCell((int)Player.playerPosition.x, (int)Player.playerPosition.y, Player.playerFigure);

        //Check if player-figure is on a cell that effect the state/visuall of the player, win-state, lose-state and default state.
        if(isCellFlag(Player.playerPosition)) playerLayer.setCell((int) Player.playerPosition.x, (int) Player.playerPosition.y, Player.playerFigureHasWon);
        if(isCellHole(Player.playerPosition)) playerLayer.setCell((int) Player.playerPosition.x, (int) Player.playerPosition.y, Player.playerFigureHasDied);

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

        playerLayer.setCell((int)Player.playerPosition.x, (int)Player.playerPosition.y, null);

        if (keycode == Input.Keys.LEFT && canMove((int)Player.playerPosition.x, (int)Player.playerPosition.y, Dir.W)) { Player.playerPosition = new Vector2(Player.playerPosition.x - 1, Player.playerPosition.y); }
        if (keycode == Input.Keys.RIGHT && canMove((int)Player.playerPosition.x, (int)Player.playerPosition.y, Dir.E)) { Player.playerPosition = new Vector2(Player.playerPosition.x + 1, Player.playerPosition.y); }
        if (keycode == Input.Keys.UP && canMove((int)Player.playerPosition.x, (int)Player.playerPosition.y, Dir.N)) { Player.playerPosition = new Vector2(Player.playerPosition.x, Player.playerPosition.y + 1); }
        if (keycode == Input.Keys.DOWN && canMove((int)Player.playerPosition.x, (int)Player.playerPosition.y, Dir.S)) { Player.playerPosition = new Vector2(Player.playerPosition.x, Player.playerPosition.y - 1); }

        playerLayer.setCell((int) Player.playerPosition.x, (int) Player.playerPosition.y, Player.playerFigure);

        return (keycode == Input.Keys.LEFT || keycode == Input.Keys.RIGHT || keycode == Input.Keys.UP || keycode == Input.Keys.DOWN); //return keycode != 0;
    }

    private boolean isCellFlag(Vector2 playerPosition) { return flagLayer.getCell((int) playerPosition.x, (int) playerPosition.y) != null; }
    private boolean isCellHole(Vector2 playerPosition) { return holeLayer.getCell((int) playerPosition.x, (int) playerPosition.y) != null; }
    private boolean isCellWall(Vector2 playerPosition) { return walls.getCell((int) playerPosition.x, (int) playerPosition.y) != null; }

    public enum Dir {S, N, W, E}

    public boolean canMove(int x, int y, Dir direction){

        int cellCurrentlyOn;
        int cellMovingTo;

        if(isCellWall(Player.playerPosition)) { cellCurrentlyOn = walls.getCell(x, y).getTile().getId(); }
        else{ cellCurrentlyOn = -1; }

        if(direction == Dir.W){
            if(walls.getCell((int) Player.playerPosition.x-1, (int) Player.playerPosition.y) == null) {cellMovingTo = -1;}
            else {cellMovingTo = walls.getCell(x-1, y).getTile().getId();}
            return cellMovingTo != 16 && cellMovingTo != 8 && cellMovingTo != 23 && cellCurrentlyOn != 32 && cellCurrentlyOn != 30 && cellCurrentlyOn != 24;
        }
        if(direction == Dir.E){
            if(walls.getCell((int) Player.playerPosition.x+1, (int) Player.playerPosition.y) == null) {cellMovingTo = -1;}
            else {cellMovingTo = walls.getCell(x+1, y).getTile().getId();}
            return cellMovingTo != 32 && cellMovingTo != 30 && cellMovingTo != 24 && cellCurrentlyOn != 8 && cellCurrentlyOn != 16 && cellCurrentlyOn != 23;
        }
        if(direction == Dir.N){
            if(walls.getCell((int) Player.playerPosition.x, (int) Player.playerPosition.y+1) == null) {cellMovingTo = -1;}
            else {cellMovingTo = walls.getCell(x, y+1).getTile().getId();}
            return cellMovingTo != 32 && cellMovingTo != 29 && cellMovingTo != 8 && cellCurrentlyOn != 31 && cellCurrentlyOn != 16 && cellCurrentlyOn != 24;
        }
        if(direction == Dir.S){
            if(walls.getCell((int) Player.playerPosition.x, (int) Player.playerPosition.y-1) == null) {cellMovingTo = -1;}
            else {cellMovingTo = walls.getCell(x, y-1).getTile().getId();}
            return cellMovingTo != 31 && cellMovingTo != 16 && cellMovingTo != 24 && cellCurrentlyOn != 8 && cellCurrentlyOn != 29 && cellCurrentlyOn != 32;
        }
        return true;
    }
}
