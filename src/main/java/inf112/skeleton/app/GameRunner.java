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
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Cards.Cards;
import inf112.skeleton.app.Cards.Deck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class GameRunner extends InputAdapter implements ApplicationListener {

    private SpriteBatch batch;
    private BitmapFont font;

    private int[] numberKeyValues = new int[]{8, 9, 10, 11, 12, 13, 14, 15, 16};

    //Define view states
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera cameraView;
    private Player player;
    private Deck deck;

    @Override
    public void create() {
        player = new Player(0);
        player.create();

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        //Tile file load
        Board.map = new TmxMapLoader().load("src/assets/example.tmx");

        //Representation on GUI map
        Board.boardLayer = (TiledMapTileLayer) Board.map.getLayers().get("Board");
        Board.flagLayer = (TiledMapTileLayer) Board.map.getLayers().get("Flag");
        Board.holeLayer = (TiledMapTileLayer) Board.map.getLayers().get("Hole");
        Board.playerLayer = (TiledMapTileLayer) Board.map.getLayers().get("Player");
        Board.walls = (TiledMapTileLayer) Board.map.getLayers().get("Walls");

        //Size of camera in relation to map size
        cameraView = new OrthographicCamera();
        cameraView.setToOrtho(false, 5, 5);
        cameraView.position.x = 2.5F;

        //Define playerLayer coordinate and playerFigure
        Board.playerLayer.setCell((int) player.playerPosition.x,(int) player.playerPosition.y,Player.playerFigure);
        cameraView.update();

        mapRenderer = new OrthogonalTiledMapRenderer(Board.map, (float) (1.0/600.0));

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

        //Check if player-figure is on a cell that effect the state/visual of the player, win-state, lose-state and default state.
        Player.setPlayerFigure(Board.playerLayer);
        mapRenderer.setView(cameraView);
        mapRenderer.render();
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }


    public boolean isNumberKey(int keycode) { return Arrays.stream(numberKeyValues).anyMatch(i -> i == keycode); }

    /**
     * @param keycode Keyboard input
     * @return Movement for player according to card or arrow keys
     */
    //TODO Skriv if statements om til en
    @Override
    public boolean keyUp(int keycode){

        Board.playerLayer.setCell((int) Player.playerPosition.x, (int) Player.playerPosition.y, null);

        //TODO skriv dette til en metode
        if (keycode == Input.Keys.D) {
            deck = new Deck();
            Deck.chooseCardNow = true; //(true); //chooseCardsNow(true); // = true;
            Deck.dealtCards = deck.dealCards(8);
        }

        if(Deck.chooseCardNow && isNumberKey(keycode)){
            Deck.chosenCards.add(Deck.dealtCards.get(keycode-8));
            Deck.chooseCardNow = Deck.checkCardStatus(Deck.chosenCards, player, Deck.chooseCardNow);
        }

//        For testing av kort, wall, og player
 /*       if (keycode == Input.Keys.NUM_1){ player.move(new Cards(0, "Move One", 0 , 1)); }
        if (keycode == Input.Keys.NUM_2){ player.move(new Cards(0, "Move Two", 0, 2)); }
        if (keycode == Input.Keys.NUM_3){ player.move(new Cards(0, "Move Three", 0, 3)); }
        if (keycode == Input.Keys.NUM_4){ player.move(new Cards(0, "Rotate Left",3,0)); }
        if (keycode == Input.Keys.NUM_5){ player.move(new Cards(0, "Rotate Right",1,0)); }
        if (keycode == Input.Keys.NUM_6){ player.move(new Cards(0, "U-Turn",2,0)); }
        if (keycode == Input.Keys.NUM_7){ player.move(new Cards(0, "Back Up",0,-1)); }*/
        if (keycode == Input.Keys.LEFT && Player.canMove(Direction.WEST))  { Player.playerPosition = new Vector2(Player.playerPosition.x - 1, Player.playerPosition.y); }
        if (keycode == Input.Keys.RIGHT && Player.canMove(Direction.EAST)) { Player.playerPosition = new Vector2(Player.playerPosition.x + 1, Player.playerPosition.y); }
        if (keycode == Input.Keys.UP && Player.canMove(Direction.NORTH))   { Player.playerPosition = new Vector2(Player.playerPosition.x, Player.playerPosition.y + 1); }
        if (keycode == Input.Keys.DOWN && Player.canMove(Direction.SOUTH)) { Player.playerPosition = new Vector2(Player.playerPosition.x, Player.playerPosition.y - 1); }

        Board.playerLayer.setCell((int) Player.playerPosition.x, (int) Player.playerPosition.y, Player.playerFigure);

        return true; //(keycode == Input.Keys.LEFT || keycode == Input.Keys.RIGHT || keycode == Input.Keys.UP || keycode == Input.Keys.DOWN); //return keycode != 0;  //må se på denne
    }
}



