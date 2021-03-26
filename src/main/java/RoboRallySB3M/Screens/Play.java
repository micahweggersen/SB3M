package RoboRallySB3M.Screens;


import RoboRallySB3M.Board;
import RoboRallySB3M.Cards.Cards;
import RoboRallySB3M.Cards.Deck;
import RoboRallySB3M.ClientPlayer;
import RoboRallySB3M.Laser;
import RoboRallySB3M.Network.Client.Client;
import RoboRallySB3M.Network.Data.*;
import RoboRallySB3M.Network.Server.Server;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.*;

public class Play implements Screen, InputProcessor {

    private SpriteBatch batch;
    private BitmapFont font;

    private final int[] numberKeyValues = new int[]{8, 9, 10, 11, 12, 13, 14, 15, 16};

    //Define view states
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera cameraView;
    private String playerName;

    private HashMap<String, ClientPlayer> playerTileCache = new HashMap<>();
    private List<PlayerData> playerData;
    private List<Cards> dealtCards;
    private Queue<Cards> chosenCards;
    private boolean inputKey = false;
    private boolean newCards = true;

    private boolean isClientOnly;
    private Client client;

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
        renderer = new OrthogonalTiledMapRenderer(Board.map, (float) (1.0 / 600.0));


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


    public boolean isNumberKey(int keycode) {
        return Arrays.stream(numberKeyValues).anyMatch(i -> i == keycode);
    }

    public void createPlayerDeck() {
        newCards = false;
        Deck deck = new Deck();
        dealtCards = deck.dealCards(8);
        chosenCards = new LinkedList<>();
    }

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
        if (keycode == Input.Keys.D) {
            inputKey = true;
            if(newCards) {
                createPlayerDeck();
            }
        }
        if (keycode == Input.Keys.F) {
            inputKey = false;
        }
        if (inputKey) {
            choosingCards(keycode);
        }
        if(keycode == Input.Keys.S) {
            if(!chosenCards.isEmpty()) {
                client.sendPayload(Payload.create(PayloadAction.CARD, MoveCardData.create(playerName, chosenCards.poll())));
            } else {
                System.out.println("Hand is empty!");
                newCards = true;
            }
        }

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