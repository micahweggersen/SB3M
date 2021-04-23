package RoboRallySB3M.Screens;

import RoboRallySB3M.Cards.Cards;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DrawScreenElements implements Screen {

    private Texture damageToken;
    private Texture damageTokenPosition;
    private Texture cardPosition;
    private Texture lifeToken;


    private final ArrayList<Texture> cardsTextures = new ArrayList<>();
    private final ArrayList<Texture> dealtCardsTextures = new ArrayList<>();
    private final ArrayList<Texture> chosenCardsTextures = new ArrayList<>();
    BitmapFont cardPositionNumber;
    BitmapFont chosenCardsOrder;

    // Variables needed for drawing cards
    int[] cardX = new int[9];
    int[] cardY = new int[9];
    boolean[] isCardChosen = new boolean[9];
    int numCardsChosen;
    private boolean dealCardsNow = false;

    @Override
    public void show() {
        cardPositionNumber = new BitmapFont();
        cardPositionNumber.setColor(Color.BLACK);
        cardPositionNumber.getData().setScale(1f);

        chosenCardsOrder = new BitmapFont();
        chosenCardsOrder.setColor(Color.BLACK);
        chosenCardsOrder.getData().setScale(0.7f);

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

    @Override
    public void render(float v) {

    }

    /**
     * Draws the positions of damage tokens
     */
    public void drawDamageTokenPositions(SpriteBatch batch) {
        for (int i = 9; i >= 0; i--) {
            batch.draw(damageTokenPosition, 949 - (i * 48), 150, 40, 50);
        }
    }

    /**
     * Draws the damage tokens the player has received
     */
    public void drawDamageTokens(int damageTokens, SpriteBatch batch) {
        for (int i = 0; i < damageTokens; i++) {
            batch.draw(damageToken, 949 - (i * 48), 150, 40, 50);
        }
    }

    /**
     * Draws the life tokens
     */
    public void drawLifeTokens(int lifeTokens, SpriteBatch batch) {
        for (int i = 0; i < lifeTokens; i++) {
            batch.draw(lifeToken, 770 - (i * 70), 200, 100, 100);
        }

    }

    /**
     * Draws the positions of where chosen cards should be on the screen
     */
    public void drawCardPositions(SpriteBatch batch) {
        for (int i = 5; i > 0; i--) {
            batch.draw(cardPosition, 952 - (i * 98), -15, 240, 180);
            chosenCardsOrder.draw(batch,String.valueOf(i),  1047 - (i * 99), 17);
        }
    }

   /**
     * Draws the cards that a player is dealt at the start of each round
     */
    public void drawDealtCards(List<Cards> dealtCards, SpriteBatch batch, int damageTokenAmount) {
        for (int i = 0; i < 9-damageTokenAmount; i++) {
            Cards card = dealtCards.get(i);
            dealtCardsTextures.add(cardsTextures.get(card.getIdInt(card)));
            batch.draw(dealtCardsTextures.get(i), 490 + cardX[i], 300 + cardY[i], 160, 123);
            cardPositionNumber.draw(batch, String.valueOf(i + 1), 548 + (i * 50), 320);
        }
    }


    public void drawChosenCards(LinkedList<Cards> chosenCards, SpriteBatch batch) {
        for (int i = 0; i < 5; i++) {
            Cards card = chosenCards.get(i);
            chosenCardsTextures.add(cardsTextures.get(card.getIdInt(card)));
            batch.draw(chosenCardsTextures.get(i), 857 - (i * 98), -7, 240, 180);
        }
    }

    /**
     * Sets initial values for dealt and chosen program cards.
     */
    public void initializeCards() {
        for (int i = 0; i < 9; i++) {
            cardX[i] = i * 50;
            cardY[i] = 12;
            isCardChosen[i] = false;
        }
        dealtCardsTextures.clear();
        //play.getChosenCards();
        numCardsChosen = 0;
    }



    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
