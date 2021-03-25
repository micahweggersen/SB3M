package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Cards.Cards;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Math.abs;


public class Player extends Sprite implements InputProcessor {

    //Define player states
    public TiledMapTileLayer.Cell playerFigure;
    public TiledMapTileLayer.Cell playerFigureHasWon;
    public TiledMapTileLayer.Cell playerFigureHasDied;

    public static Vector2 playerPosition = new Vector2(0,0);
    private int direction;
    public Vector2 newPlayerPosition = null;
    private float alpha = 0;
    float newY;
    float newX;

    public Player(Sprite sprite){
        super(sprite);
        this.direction = 0;
    }

    @Override
    public void draw(Batch spriteBatch){
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }

    public void update(float delta){
        Board.clear(Board.playerLayer);
        if(newPlayerPosition != null) {
            float alpha = getAlpha();
            playerPosition.lerp(newPlayerPosition, alpha);
            System.out.println(alpha);
        }

        //vil oppdatere x og y om den er en epsilon verdi unna et helt tall.
        //evt holder en desimal verdi over en periode så skal den rundes ned
        setPlayerFigure(Board.playerLayer);
    }

    private float getAlpha(){
        alpha += 0.1;
        if (alpha>=1) alpha = 0;
        if(playerPosition.epsilonEquals(newPlayerPosition, 0.005F)) alpha = 1;
        return alpha;
    }

    public int getDirection(){ return direction; }

    public void create(){

        // Set grid coordinate for playerFigure
//        playerPosition = new Vector2(5, 5);

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

    /**
     * Decides player image according to legal placement on board
     * @param playerLayer Image of player character
     */
    public void setPlayerFigure(TiledMapTileLayer playerLayer) {
        if(Board.isCellFlag())
            playerLayer.setCell((int)playerPosition.x, (int)playerPosition.y, playerFigureHasWon.setRotation(getDirection()));
        else if(Board.isCellHole())
            playerLayer.setCell((int)playerPosition.x, (int)playerPosition.y, playerFigureHasDied.setRotation(getDirection()));
        else
            playerLayer.setCell((int)playerPosition.x, (int)playerPosition.y, playerFigure.setRotation(getDirection()));
    }

    @Override
    public boolean keyDown(int i) { return false; }

    /**
     * @param keycode Keyboard input
     * @return Movement for player according to card or arrow keys
     */
    @Override
    public boolean keyUp(int keycode) {

//        int oldX = (int) playerPosition.x;
//        int oldY = (int) playerPosition.y;
//        Direction dir = null;
        Queue<Cards> q = new LinkedList<>();

        if (keycode == Input.Keys.D){
            q.add(new Cards(0, "Move One", 0, 1));
            q.add(new Cards(0, "Move One", 0, 1));
            q.add(new Cards(0, "Rotate Right", 3, 0));
            q.add(new Cards(0, "Move One", 0, 1));
            q.add(new Cards(0, "Move Two", 0, 2));

           for( Cards c : q){
               //kan lage en vektor for hvertkort å bruke lerp mellom hver av dem
               move(c);

               //               update(0);
           }


        }

        if (keycode == Input.Keys.NUM_1) move(new Cards(0, "Move One", 0, 1));
        if (keycode == Input.Keys.NUM_2) move(new Cards(0, "Move Two", 0, 2));
        if (keycode == Input.Keys.NUM_3) move(new Cards(0, "Move Three", 0, 3));
        if (keycode == Input.Keys.NUM_4) move(new Cards(0, "Rotate Left", 3, 0));
        if (keycode == Input.Keys.NUM_5) move(new Cards(0, "Rotate Right", 1, 0));
        if (keycode == Input.Keys.NUM_6) move(new Cards(0, "U-Turn", 2, 0));
        if (keycode == Input.Keys.NUM_7) move(new Cards(0, "Back Up", 0, -1)); //denne kan lure sæ igjennom vegger

//        if(dir == null) dir = Direction.intToDirection(getDirection());
//        if (canMove(dir, oldX, oldY)) Board.playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, playerFigure);
//        else {
//            playerPosition.x = oldX;
//            playerPosition.y = oldY;
//            Board.playerLayer.setCell(oldX, oldY, playerFigure);
//        }
        return true;
//        if (keycode == Input.Keys.D) {
//            deck = new Deck();
//            Deck.chooseCardNow = true; //(true); //chooseCardsNow(true); // = true;
//            Deck.dealtCards = deck.dealCards(8);
//        }
//        if(Deck.chooseCardNow && isNumberKey(keycode)){
//            Deck.chosenCards.add(Deck.dealtCards.get(keycode-8));
//            try {
//                Deck.chooseCardNow = Deck.checkCardStatus(Deck.chosenCards, player, Deck.chooseCardNow);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
    }

    /**
     * @param card Card with directional value
     *             Moves the player to new location
     */
    public void move(Cards card){

        newPlayerPosition = null;

        if(card.getDirection() != 0){
            direction = (direction + card.getDirection())%4;
            return;
        }
        Direction dir = Direction.intToDirection(direction);

        int magnitude = (dir == Direction.WEST || dir == Direction.SOUTH) ? -1 : 1;
        if(card.getMomentum() < 0) {
            magnitude *= -1; //slå denne sammen med den oppe
            dir = Direction.oppositeDirection(dir);
        }

//        float newY = (dir == Direction.SOUTH || dir == Direction.NORTH) ? playerPosition.y + card.getMomentum()*magnitude : playerPosition.y;
//        float newX = (dir == Direction.WEST || dir == Direction.EAST) ? playerPosition.x + card.getMomentum()*magnitude : playerPosition.x;
//        if(dir == Direction.SOUTH || dir == Direction.NORTH)newY = playerPosition.y + card.getMomentum()*magnitude;
//        if(dir == Direction.EAST || dir == Direction.WEST) newX = playerPosition.x + card.getMomentum()*magnitude;

        if (dir == null) throw new IllegalArgumentException("The direction can't be null");

        for(int i = 0; i < abs(card.getMomentum()); i++){
            if(!canMove(dir, (int) newX, (int)newY)) break;
            if(canMove(dir, (int)newX, (int)newY)){
                if(dir == Direction.SOUTH || dir == Direction.NORTH) newY += (magnitude);
                if(dir == Direction.WEST || dir == Direction.EAST) newX += (magnitude);
            }
        }
        newPlayerPosition = new Vector2(newX+0.00001F, newY+0.00001F);
    }

    /**
     * Checks if player can move to location
     * @param direction player pointing direction
     * @return true if can move false if cannot move
     */
    public static boolean canMove(Direction direction, int oldX, int oldY) {

        //skriv om
        int x_change = 0;
        int y_change = 0;
        if (direction == Direction.WEST) x_change = -1;
        if (direction == Direction.EAST) x_change = 1;
        if (direction == Direction.NORTH) y_change = 1;
        if (direction == Direction.SOUTH) y_change = -1;

        if(Board.walls.getCell(oldX, oldY) != null){
            if (Board.walls.getCell(oldX, oldY).getTile().getProperties().containsKey(direction.toString())) return false;
        }
        if (Board.walls.getCell((oldX+x_change), oldY + y_change) != null){
            return (!Board.walls.getCell(oldX + x_change, oldY + y_change).getTile().getProperties()
                    .containsKey(Direction.oppositeDirection(direction).toString()));
        }
        return true;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
