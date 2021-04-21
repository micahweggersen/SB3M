package RoboRallySB3M.Screens;


import RoboRallySB3M.GameObjects.Board;
import RoboRallySB3M.GameObjects.Laser;
import RoboRallySB3M.Network.Client.Client;
import RoboRallySB3M.Network.Client.ClientPlayer;
import RoboRallySB3M.Network.Server.Server;
import RoboRallySB3M.Cards.Cards;
import RoboRallySB3M.Cards.Deck;
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
import RoboRallySB3M.Network.Data.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

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
    private PlayerData player;
    private HashMap<String, LaserData> laserData;
    //private PlayerServer player = new PlayerServer();

    private final int[] numberKeyValues = new int[]{8, 9, 10, 11, 12, 13, 14, 15, 16};

    private List<Cards> dealtCards;
    private LinkedList<Cards> chosenCards = new LinkedList<>();

    // Variables needed for drawing cards
    int[] cardX = new int[9];
    int[] cardY = new int[9];
    boolean[] isCardChosen = new boolean[9];
    int numCardsChosen;
    private boolean dealCardsNow = false;

    BitmapFont cardPositionNumber;

    private boolean inputKey = false;
    private boolean newCards = true;

    private boolean isClientOnly;
    private Client client;
    private Laser laser;

    public static final int SCREEN_WIDTH = 1500;
    public static final int SCREEN_HEIGHT = 1000;

    public static final int GAMEBOARD_PLACEMENT_X = 0;
    public static final int GAMEBOARD_PLACEMENT_Y = 0;

    private Texture damageToken;
    private Texture damageTokenPosition;
    private Texture cardPosition;
    private Texture lifeToken;

    private final ArrayList<Texture> cardsTextures = new ArrayList<>();
    private final ArrayList<Texture> dealtCardsTextures = new ArrayList<>();
    private final ArrayList<Texture> chosenCardsTextures = new ArrayList<>();
    private List<LaserData> laserLocation;


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

        /*Skin skin = new Skin(Gdx.files.internal("src/assets/quantum-horizon/skin/quantum-horizon-ui.json"));
        TextButton exit = new TextButton("Exit", skin);
        exit.setSize(60,60);
        exit.setPosition(500, 600);
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });*/

        cardPositionNumber = new BitmapFont();
        cardPositionNumber.setColor(Color.BLACK);
        cardPositionNumber.getData().setScale(1f);

        //Tile file load
        Board.map = new TmxMapLoader().load("src/assets/testAutoWalk.tmx");

        //Representation on GUI map
        Board.boardLayer = (TiledMapTileLayer) Board.map.getLayers().get("Board");
        Board.flagLayer = (TiledMapTileLayer) Board.map.getLayers().get("Flag");
        Board.holeLayer = (TiledMapTileLayer) Board.map.getLayers().get("Hole");
        Board.playerLayer = (TiledMapTileLayer) Board.map.getLayers().get("Player");
        Board.walls = (TiledMapTileLayer) Board.map.getLayers().get("Walls");
        Board.laserVertical = (TiledMapTileLayer) Board.map.getLayers().get("LaserVertical");
        Board.laserHorizontal = (TiledMapTileLayer) Board.map.getLayers().get("LaserHorizontal");
        Board.autoWalk = (TiledMapTileLayer) Board.map.getLayers().get("Autowalk");
        Board.repairShop = (TiledMapTileLayer) Board.map.getLayers().get("RepairShop");
        Board.rotationGears = (TiledMapTileLayer) Board.map.getLayers().get("RotationGears");
        Board.pushers = (TiledMapTileLayer) Board.map.getLayers().get("Pushers");

        laser = new Laser();
        laser.initializeLaser();

        Board.laserHorizontal.setVisible(true);
        Board.laserVertical.setVisible(true);

        int mapWidth = 400;
        int mapHeight = 300;
        int tileWidth = Board.holeLayer.getWidth();
        int tileHeight = Board.holeLayer.getHeight();

        //Size of camera in relation to map size
        cameraView = new OrthographicCamera();
        cameraView.setToOrtho(false, (mapWidth * tileWidth) * ((float) SCREEN_WIDTH / (float) SCREEN_HEIGHT), mapHeight * tileHeight);
        cameraView.translate(GAMEBOARD_PLACEMENT_X, GAMEBOARD_PLACEMENT_Y);

        //Define playerLayer coordinate and playerFigure
        cameraView.update();

        startServer();

        //Load player figure and set size
        renderer = new OrthogonalTiledMapRenderer(Board.map, (float) 1.0 / (tileWidth) * (tileHeight));

        Gdx.input.setInputProcessor(this);

        clientConnectToServer();

        damageToken = new Texture("src/assets/damage_token.png");
        damageTokenPosition = new Texture("src/assets/damage_token_grey.png");
        lifeToken = new Texture("src/assets/life_token.png");
        cardPosition = new Texture("src/assets/cards/CardSpotHolder.png");

        cardsTextures.add(new Texture(Gdx.files.internal("src/assets/cards/move1-1.png")));
        cardsTextures.add(new Texture(Gdx.files.internal("src/assets/cards/move2-1.png")));
        cardsTextures.add(new Texture(Gdx.files.internal("src/assets/cards/move3-1.png")));
        cardsTextures.add(new Texture(Gdx.files.internal("src/assets/cards/back_up-1.png")));
        cardsTextures.add(new Texture(Gdx.files.internal("src/assets/cards/rotate_left-1.png")));
        cardsTextures.add(new Texture(Gdx.files.internal("src/assets/cards/rotate_right.png")));
        cardsTextures.add(new Texture(Gdx.files.internal("src/assets/cards/u-turn-1.png")));

        initializeCards();
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(150/255f, 150/255f, 150/255f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        //Check if player-figure is on a cell that effect the state/visual of the player, win-state, lose-state and default state.
        if (playerData != null) {
            Board.clear(Board.playerLayer);
            renderer.getBatch().begin();
            playerRender();
            renderer.getBatch().end();
        }
        laser.drawLaser(laserData, playerData);

        batch.begin();
        drawDamageTokenPositions();
        drawCardPositions();


        for (PlayerData player : playerData) {
            drawDamageTokens(player.damageToken);
            drawLifeTokens(player.lifeTokens);
        }

        if(dealCardsNow){
            drawDealtCards();}

        if(chosenCards.size() == 5){
            drawChosenCards();
            dealCardsNow = false;
        }
        batch.end();

        renderer.setView(cameraView);
        renderer.render();


    }


    /**
     * Draws the positions of damage tokens
     */
    private void drawDamageTokenPositions(){
        for(int i = 10; i >= 0; i--){
            batch.draw(damageTokenPosition, 995-(i*48), 150, 40, 50);
        }
     }

    /**
     * Draws the damage tokens the player has received
     */
    private void drawDamageTokens(int damageTokens){
        for (int i = damageTokens; i >= 10; i--) {
            batch.draw(damageToken, 995 - (i * 48), 150, 40, 50);
        }
    }

    /**
     * Draws the life tokens
     */
    private void drawLifeTokens(int lifeTokens) {
            for (int i = 0; i < lifeTokens; i++) {
                batch.draw(lifeToken, 770 - (i * 70), 200, 100, 100);
            }

    }

    /**
     * Draws the positions of where chosen cards should be on the screen
     */
     private void drawCardPositions(){
        for (int i = 5; i>0; i--){
            batch.draw(cardPosition, 952-(i*98), -15, 240, 180);
        }
     }

    /**
     * Draws the cards that a player is dealt at the start of each round
     */
    private void drawDealtCards() {
         for (int i = 0; i < 9; i++) {
             Cards card = dealtCards.get(i);
             dealtCardsTextures.add(cardsTextures.get(card.getIdInt(card)));
             batch.draw(dealtCardsTextures.get(i), 490 + cardX[i], 300 + cardY[i], 160, 123);
             cardPositionNumber.draw(batch, String.valueOf(i+1), 548 + (i*50), 320);
         }
     }

     private void drawChosenCards() {
        for (int i = 0; i < 5; i++) {
            Cards card = chosenCards.get(i);
            chosenCardsTextures.add(cardsTextures.get(card.getIdInt(card)));
            batch.draw(chosenCardsTextures.get(i), 857-(i*98), -7, 240, 180);
        }
     }

    /**
     * Sets initial values for dealt and chosen program cards.
     */
    private void initializeCards() {
        for (int i = 0; i < 9; i++) {
            cardX[i] = i*50;
            cardY[i] = 12;
            isCardChosen[i] = false;
        }
        dealtCardsTextures.clear();
        getChosenCards();
        numCardsChosen = 0;
    }

    private LinkedList<Cards> getChosenCards(){
        return chosenCards;
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
        client = new Client("127.0.0.1", 8818, (data, lData) -> {
            playerData = data;
            laserData = lData;
        });

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

    private boolean isYourTurn() {
        for (PlayerData p :playerData) {
            if(!p.playerName.equals(playerName) && p.turnOrder == 0){
                System.out.println("Not your turn");
                return false;
            }
        }
        return true;
    }

    /**
     * Creates the possible cards to choose from
     */
    public void createPlayerDeck() {
        newCards = false;
        Deck deck = new Deck();
        dealtCards = deck.dealCards(8);
        dealCardsNow = true;
        chosenCards = new LinkedList<>();
    }

    /**
     * @param keycode chooses cards between numbers 1 to 9
     */
    private void choosingCards(int keycode) {
        if (isNumberKey(keycode)) {
            if (!chosenCards.contains(dealtCards.get(keycode - 8))) {
                chosenCards.addFirst(dealtCards.get(keycode - 8));
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
        if(!isYourTurn()) {
            return false;
        }

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