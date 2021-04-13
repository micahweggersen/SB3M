package RoboRallySB3M.Screens;


import RoboRallySB3M.GameObjects.Board;
import RoboRallySB3M.Cards.Cards;
import RoboRallySB3M.Cards.Deck;
import RoboRallySB3M.Network.Client.ClientPlayer;
import RoboRallySB3M.GameObjects.Laser;
import RoboRallySB3M.Network.Client.Client;
import RoboRallySB3M.Network.Data.*;
import RoboRallySB3M.Network.Server.Server;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.*;

/**
 * This class is the screen that is rendered when the
 * button "NEW GAME" is pressed in menu
 */
public class Play implements Screen, InputProcessor {

    private SpriteBatch batch;
    private BitmapFont font;

    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera cameraView;

    private String playerName;
    private HashMap<String, ClientPlayer> playerTileCache = new HashMap<>();
    private List<PlayerData> playerData;

    private final int[] numberKeyValues = new int[]{8, 9, 10, 11, 12, 13, 14, 15, 16};

    private List<Cards> dealtCards;
    private Queue<Cards> chosenCards;

    private boolean inputKey = false;
    private boolean newCards = true;

    private boolean isClientOnly;
    private Client client;

    public static final int screenWidth = 1500;
    public static final int screenHeight = 1000;
    public static final int gameboardPlacementX = 0;
    public static final int gameboardPlacementY = 0;



    public Play(boolean isClientOnly) {
        this.isClientOnly = isClientOnly;
    }


    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
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
        Board.laserVertical = (TiledMapTileLayer) Board.map.getLayers().get("LaserVertical");
        Board.laserHorizontal = (TiledMapTileLayer) Board.map.getLayers().get("LaserHorizontal");

        Laser.createLaser();
        Board.laserHorizontal.setVisible(true);
        Board.laserVertical.setVisible(true);

        int mapWidth = 400;
        int mapHeight = 400;
        int tileWidth = Board.holeLayer.getWidth();
        int tileHeight = Board.holeLayer.getHeight();


        //Size of camera in relation to map size
        cameraView = new OrthographicCamera();
        cameraView.setToOrtho(false, (mapWidth * tileWidth) * ((float) screenWidth / (float) screenHeight), mapHeight * tileHeight);
        cameraView.translate(gameboardPlacementX, gameboardPlacementY);


        //Define playerLayer coordinate and playerFigure
        cameraView.update();

        startServer();

        //Load player figure and set size
        renderer = new OrthogonalTiledMapRenderer(Board.map, (float) 1.0 / (tileWidth) * (tileHeight));

        Gdx.input.setInputProcessor(this);

        clientConnectToServer();
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);


        //Check if player-figure is on a cell that effect the state/visual of the player, win-state, lose-state and default state.


        if (playerData != null) {
            Board.clear(Board.playerLayer);
            renderer.getBatch().begin();
            playerRender();
            renderer.getBatch().end();
        }

        Laser.drawLaser(playerData);

        renderer.setView(cameraView);
        renderer.render();
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

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    /**
     * Starts the server
     */
    private void startServer() {
        if (!isClientOnly) {
            Server server = new Server(8818);
            server.start();
        }
    }

    /**
     * Connects a new client to server
     */
    private void clientConnectToServer() {
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
    }

    /**
     * Renders the player figure with some animation
     */
    private void playerRender() {
        for (PlayerData player : playerData) {
            String name = player.playerName;

            if (!playerTileCache.containsKey(name)) {
                playerTileCache.put(name, new ClientPlayer(name, player.position));
            }

            ClientPlayer cp = playerTileCache.get(name);
            if (cp.updatePosition(player)) {
                cp.draw(renderer.getBatch());
            }
        }
    }

    /**
     * @param keycode input key
     * @return if key is a match to numberKeyValues list
     */
    public boolean isNumberKey(int keycode) {
        return Arrays.stream(numberKeyValues).anyMatch(i -> i == keycode);
    }

    /**
     * Creates the possible cards to choose from
     */
    public void createPlayerDeck() {
        newCards = false;
        Deck deck = new Deck();
        dealtCards = deck.dealCards(8);
        chosenCards = new LinkedList<>();
    }

    /**
     * @param keycode chooses cards between numbers 1 to 9
     */
    private void choosingCards(int keycode) {
        if (isNumberKey(keycode)) {
            if (!chosenCards.contains(dealtCards.get(keycode - 8))) {
                chosenCards.add(dealtCards.get(keycode - 8));
                System.out.println("Added Card");
            }
            System.out.println("Choose another card!");
            if (!Deck.checkEnoughCardStatus(chosenCards)) {
                System.out.println("Full hand");
            }
        }
    }

    /**
     * Called when a key was pressed
     *
     * @param keycode one of the constants in {@link Input.Keys}
     * @return whether the input was processed
     */
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    /**
     * @param keycode Keyboard input
     * @return Movement for player according to card or arrow keys
     */
    @Override
    public boolean keyUp(int keycode) {
        //get a deck and lockout other functions
        if (keycode == Input.Keys.D) {
            inputKey = true;
            if(newCards) {
                createPlayerDeck();
            }
        }
        //opens other functions
        if (keycode == Input.Keys.F) {
            inputKey = false;
        }
        if (inputKey) {
            choosingCards(keycode);
        }
        //plays the first card from chosenCards Queue, one per press
        if(keycode == Input.Keys.S) {
            if(chosenCards != null) {
                if(!chosenCards.isEmpty()) {
                    client.sendPayload(Payload.create(PayloadAction.CARD, MoveCardData.create(playerName, chosenCards.poll())));
                } else {
                    System.out.println("Hand is empty!");
                    newCards = true;
                }
            } else {
                System.out.println("You have not chosen any cards!");
            }
        }
        //manual input to test all movement and other things on board
        if (!inputKey) {
            client.sendPayload(Payload.create(PayloadAction.MOVE, MoveData.create(keycode, playerName)));
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}