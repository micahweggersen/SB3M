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
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import inf112.skeleton.app.Cards.Cards;
import inf112.skeleton.app.Cards.Deck;
import inf112.skeleton.app.Network.Client.*;
import inf112.skeleton.app.Network.Data.*;
import inf112.skeleton.app.Network.Server.Server;

import java.util.*;

public class GameRunner extends InputAdapter implements ApplicationListener {

    private SpriteBatch batch;
    private BitmapFont font;

    private int[] numberKeyValues = new int[]{8, 9, 10, 11, 12, 13, 14, 15, 16};

    //Define view states
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera cameraView;
    private String playerName;
    private Deck deck;
    public static TextureRegion[][] playerFig;

    private HashMap<String, TiledMapTileLayer.Cell> playerTileCache = new HashMap<>();
    private List<PlayerData> playerData;
    private List<Cards> dealtCards;
    private Queue<Cards> chosenCards;
    private boolean inputKey = false;

    private boolean isClientOnly;
    private Client client;

    public GameRunner(boolean isClientOnly) {
        this.isClientOnly = isClientOnly;

        System.out.println(isClientOnly);
    }

    @Override
    public void create() {
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

        if (!isClientOnly) {
            Server server = new Server(8818);
            server.start();
        }

        //Define playerLayer coordinate and playerFigure
        cameraView.update();

        //Load player figure and set size
        Texture texture = new Texture("src/assets/player.png");
        playerFig = TextureRegion.split(texture, 300, 300);

        mapRenderer = new OrthogonalTiledMapRenderer(Board.map, (float) (1.0 / 600.0));

        Gdx.input.setInputProcessor(this);

        client = new Client("127.0.0.1", 8818, data -> playerData = data);

        if (!client.startConnection()) {
            System.err.println("Connect failed!");
            return;
        }

        System.out.println("Connect successful!");

        playerName = "Player" + System.currentTimeMillis();
        Payload success = client.sendPayload(Payload.create(PayloadAction.JOIN, PlayerData.newPlayer(playerName)));

        if (success.action == PayloadAction.SUCCESS) {
            System.out.println("Joined successful!");
            client.start();
        }

        // Server notices that there's a new connection. Asks the new connection about a preferred name.
        // Check if name already taken – make sure player name is unique
        // Server creates the player, based on client input.
        // Server returns the player id!
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

        //Check if player-figure is on a cell that effect the state/visual of the player, win-state, lose-state and default state.
        if (playerData != null) {
            Board.clear(Board.playerLayer);

            for (PlayerData player : playerData) {
                setPlayerFigure(player);
            }
        }

        mapRenderer.setView(cameraView);
        mapRenderer.render();
    }

    private void setPlayerFigure(PlayerData player) {
        int x = (int) player.x;
        int y = (int) player.y;

        if (!playerTileCache.containsKey(player.playerName)) {
            playerTileCache.put(player.playerName, new TiledMapTileLayer.Cell());
        }

        TiledMapTileLayer.Cell cell = playerTileCache.get(player.playerName);

        if (player.playerName.equals(this.playerName)) {
            // Customize your own piece
            cell.setFlipHorizontally(true);
        }

        if (Board.isCellFlag(x, y)) {
            cell.setTile(new StaticTiledMapTile(playerFig[0][2]));
        } else if (Board.isCellHole(x, y)) {
            cell.setTile(new StaticTiledMapTile(playerFig[0][1]));
        } else {
            cell.setTile(new StaticTiledMapTile(playerFig[0][0]));
        }

        Board.playerLayer.setCell(x, y, cell.setRotation(player.direction));
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    public boolean isNumberKey(int keycode) {
        return Arrays.stream(numberKeyValues).anyMatch(i -> i == keycode);
    }

    public void createPlayerDeck (){
        Deck deck = new Deck();
        dealtCards = deck.dealCards(8);
        chosenCards = new LinkedList<>();
    }

    private void choosingCards(int keycode) {
        if (isNumberKey(keycode)) {
            if(!chosenCards.contains(dealtCards.get(keycode - 8))) {
                chosenCards.add(dealtCards.get(keycode - 8));
                System.out.println("Added Card");
            }
            System.out.println("Choose another card!");
            if (!Deck.checkEnoughCardStatus(chosenCards)) {
                client.sendPayload(Payload.create(PayloadAction.CARD, MoveCardData.create(playerName, chosenCards.poll())));
            }
        }
    }

    /**
     * @param keycode Keyboard input
     * @return Movement for player according to card or arrow keys
     */
    //TODO Skriv if statements om til en
    @Override
    public boolean keyUp(int keycode) {


        if (keycode == Input.Keys.D) {
            inputKey = true;
            createPlayerDeck();
        }
        if (inputKey) {
            choosingCards(keycode);
        }

        if (!inputKey) {
            client.sendPayload(Payload.create(PayloadAction.MOVE, MoveData.create(keycode, playerName)));
        }
        return true;
    }


}



