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
import inf112.skeleton.app.Cards.Cards;
import inf112.skeleton.app.Cards.Deck;
import inf112.skeleton.app.Network.Client.Client;
import inf112.skeleton.app.Network.Server.Server;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameRunner extends InputAdapter implements ApplicationListener {

    private SpriteBatch batch;
    private BitmapFont font;

    private int[] numberKeyValues = new int[]{8, 9, 10, 11, 12, 13, 14, 15, 16};

    //Define view states
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera cameraView;
    private Player currentPlayer;
    private Deck deck;
    public List<Player> playerList = new ArrayList<>();

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

        Player player1 = new Player(0, "client player");
        player1.create(0, 0, Board.playerLayer);
        playerList.add(player1);
        System.out.println(player1.getName());

        Player player2 = new Player(0, "server player");
        player2.create(1, 1, Board.playerLayer);
        playerList.add(player2);
        System.out.println(player2.getName());

        if (!isClientOnly) {
            Server server = new Server(8818, playerList);
            server.start();

            currentPlayer = playerList.get(1);
        } else {
            currentPlayer = playerList.get(0);
        }

        //Define playerLayer coordinate and playerFigure
        cameraView.update();

        mapRenderer = new OrthogonalTiledMapRenderer(Board.map, (float) (1.0 / 600.0));

        Gdx.input.setInputProcessor(this);

        client = new Client("127.0.0.1", 8818);

        if (!client.startConnection()) {
            System.err.println("Connect failed!");
            return;
        }

        System.out.println("Connect successful!");

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
        for (Player player : playerList) {
            player.setPlayerFigure(Board.playerLayer);
        }

        mapRenderer.setView(cameraView);
        mapRenderer.render();
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

    /**
     * @param keycode Keyboard input
     * @return Movement for player according to card or arrow keys
     */
    //TODO Skriv if statements om til en
    @Override
    public boolean keyUp(int keycode) {
        Board.playerLayer.setCell((int) currentPlayer.playerPosition.x, (int) currentPlayer.playerPosition.y, null);

        //TODO skriv dette til en metode
        if (keycode == Input.Keys.D) {
            deck = new Deck();
            Deck.chooseCardNow = true; //(true); //chooseCardsNow(true); // = true;
            Deck.dealtCards = deck.dealCards(8);
        }

        if (Deck.chooseCardNow && isNumberKey(keycode)) {
            Deck.chosenCards.add(Deck.dealtCards.get(keycode - 8));
            Deck.chooseCardNow = Deck.checkCardStatus(Deck.chosenCards, currentPlayer, Deck.chooseCardNow);
        }

        Cards cards = null;
        Float playerX = null;
        Float playerY = null;

//        For testing av kort, wall, og player
        switch (keycode) {
            case Input.Keys.NUM_1:
                cards = new Cards(0, "Move One", 0, 1);
                break;
            case Input.Keys.NUM_2:
                cards = new Cards(0, "Move Two", 0, 2);
                break;
            case Input.Keys.NUM_3:
                cards = new Cards(0, "Move Three", 0, 3);
                break;
            case Input.Keys.NUM_4:
                cards = new Cards(0, "Rotate Left", 3, 0);
                break;
            case Input.Keys.NUM_5:
                cards = new Cards(0, "Rotate Right", 1, 0);
                break;
            case Input.Keys.NUM_6:
                cards = new Cards(0, "U-Turn", 2, 0);
                break;
            case Input.Keys.NUM_7:
                cards = new Cards(0, "Back Up", 0, -1);
                break;
            case Input.Keys.LEFT:
                if (currentPlayer.canMove(Direction.WEST)) {
                    playerX = currentPlayer.playerPosition.x - 1;
                    playerY = currentPlayer.playerPosition.y;
                }
                break;
            case Input.Keys.RIGHT:
                if (currentPlayer.canMove(Direction.EAST)) {
                    playerX = currentPlayer.playerPosition.x + 1;
                    playerY = currentPlayer.playerPosition.y;
                }
                break;
            case Input.Keys.UP:
                if (currentPlayer.canMove(Direction.NORTH)) {
                    playerX = currentPlayer.playerPosition.x;
                    playerY = currentPlayer.playerPosition.y + 1;
                }
                break;
            case Input.Keys.DOWN:
                if (currentPlayer.canMove(Direction.SOUTH)) {
                    playerX = currentPlayer.playerPosition.x;
                    playerY = currentPlayer.playerPosition.y - 1;
                }
                break;
        }

        JSONObject obj = new JSONObject();
        obj.put("player", currentPlayer.getName());
        if (cards != null) {
            obj.put("cards", cards.toJson());
        }
        if (playerX != null) {
            obj.put("playerX", playerX);
            obj.put("playerY", playerY);
        }

        JSONObject returnJson = new JSONObject(client.sendMessage(obj.toString()));

        if (returnJson.has("error")) {
            System.out.println(returnJson.getString("error"));
            return false;
        }

        String playerName = returnJson.getString("player");
        if (!playerName.equals(currentPlayer.getName())) {
            System.out.println("Wrong player");
            return false;
        }

        currentPlayer.playerPosition.x = returnJson.getFloat("playerX");
        currentPlayer.playerPosition.y = returnJson.getFloat("playerY");

        return true; //(keycode == Input.Keys.LEFT || keycode == Input.Keys.RIGHT || keycode == Input.Keys.UP || keycode == Input.Keys.DOWN); //return keycode != 0;  //må se på denne
    }
}



