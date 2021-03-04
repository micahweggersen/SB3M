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
import inf112.skeleton.app.Cards.Deck;
import inf112.skeleton.app.Cards.MoveTwo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.LongStream;

public class Board extends InputAdapter implements ApplicationListener {

    private int[] numberKeyValues = new int[]{8, 9, 10, 11, 12, 13, 14, 15, 16};

    private SpriteBatch batch;
    private BitmapFont font;

    private TiledMap map;

    //Define board-layers for board, player, flag and hole
    private TiledMapTileLayer boardLayer;
    private TiledMapTileLayer playerLayer;
    private static TiledMapTileLayer flagLayer;
    private static TiledMapTileLayer holeLayer;
    public static TiledMapTileLayer walls;
    boolean chooseCardsNow = false;
    ArrayList<Cards> dealtCards;
    Queue<Cards> chosenCards = new LinkedList<>();
    boolean dealCardMode = false;
    Deck deck;

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
        Player.setPlayerFigure(playerLayer);
        mapRenderer.setView(cameraView);
        mapRenderer.render();
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }


    // flytt til deck
    public void checkCardStatus() {
        if (chosenCards.size() >= 5) {
            chooseCardsNow = false;
            System.out.println("enough choosing!");
//            movePlayerBySelectedCards();
            for (Cards card : chosenCards) {
                player.move(card);
            }
        }
    }

    @Override
    public boolean keyUp(int keycode){

        playerLayer.setCell((int) Player.playerPosition.x, (int) Player.playerPosition.y, null);

        //skriv dette til en metode
        if (keycode == Input.Keys.D) {
            deck = new Deck();
            chooseCardsNow = true;
            dealtCards = deck.dealCards(8);
        }

        if(chooseCardsNow && legalValue(keycode)){
            chosenCards.add(dealtCards.get(keycode-8));
            checkCardStatus();
        }

//        if (keycode == Input.Keys.NUM_1){ player.move(new Cards(0, "Move One", 0 , 1)); }
//        if (keycode == Input.Keys.NUM_2){ player.move(new Cards(0, "Move Two", 0, 2)); }
//        if (keycode == Input.Keys.NUM_3){ player.move(new Cards(0, "Move Three", 0, 3)); }
//        if (keycode == Input.Keys.NUM_4){ player.move(new Cards(0, "Rotate Left",3,0)); }
//        if (keycode == Input.Keys.NUM_5){ player.move(new Cards(0, "Rotate Right",1,0)); }
//        if (keycode == Input.Keys.NUM_6){ player.move(new Cards(0, "U-Turn",2,0)); }

        //kan skrives om ved å bruke tall verdier
        if (keycode == Input.Keys.LEFT && Player.canMove((int) Player.playerPosition.x, (int) Player.playerPosition.y, Direction.WEST))  { Player.playerPosition = new Vector2(Player.playerPosition.x - 1, Player.playerPosition.y); }
        if (keycode == Input.Keys.RIGHT && Player.canMove((int) Player.playerPosition.x, (int) Player.playerPosition.y, Direction.EAST)) { Player.playerPosition = new Vector2(Player.playerPosition.x + 1, Player.playerPosition.y); }
        if (keycode == Input.Keys.UP && Player.canMove((int) Player.playerPosition.x, (int) Player.playerPosition.y, Direction.NORTH))   { Player.playerPosition = new Vector2(Player.playerPosition.x, Player.playerPosition.y + 1); }
        if (keycode == Input.Keys.DOWN && Player.canMove((int) Player.playerPosition.x, (int) Player.playerPosition.y, Direction.SOUTH)) { Player.playerPosition = new Vector2(Player.playerPosition.x, Player.playerPosition.y - 1); }

        playerLayer.setCell((int) Player.playerPosition.x, (int) Player.playerPosition.y, Player.playerFigure);

        return true; //(keycode == Input.Keys.LEFT || keycode == Input.Keys.RIGHT || keycode == Input.Keys.UP || keycode == Input.Keys.DOWN); //return keycode != 0;  //må se på denne
    }

    private boolean legalValue(int keycode) { return Arrays.stream(numberKeyValues).anyMatch(i -> i == keycode); }
    public static boolean isCellFlag(Vector2 playerPosition) { return flagLayer.getCell((int) playerPosition.x, (int) playerPosition.y) != null; }
    public static boolean isCellHole(Vector2 playerPosition) { return holeLayer.getCell((int) playerPosition.x, (int) playerPosition.y) != null; }
    public static boolean isCellWall(Vector2 playerPosition) { return walls.getCell((int) playerPosition.x, (int) playerPosition.y) != null; }
}
