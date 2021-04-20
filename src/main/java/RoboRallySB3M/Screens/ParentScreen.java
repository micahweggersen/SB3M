package RoboRallySB3M.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class ParentScreen extends Game {

    private LoadingScreen loadingScreen;
    private PreferencesScreen preferencesScreen;
    private MenuScreen menuScreen;
    private EndScreen endScreen;
    private Play play;

    public static final int MENU = 0;
    public static final int PREFERENCES = 1;
    public static final int APPLICATION = 2;
    public static final int JOINAPPLICATION = 3;
    public static final int ENDGAME = 4;
    private static final String ROBORALLY = "RoboRally";

    public ParentScreen() {
        //Empty as it does not not any values
    }

    @Override
    public void create() {
        Gdx.app.log(ROBORALLY, "create");
        loadingScreen = new LoadingScreen(this);
        setScreen(loadingScreen);
    }

    @Override
    public void dispose() {
        Gdx.app.log(ROBORALLY, "dispose");
        super.dispose();
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(ROBORALLY, "resize");
        super.resize(width, height);
    }

    @Override
    public void pause() {
        Gdx.app.log(ROBORALLY, "pause");
        super.pause();
    }

    @Override
    public void resume() {
        Gdx.app.log(ROBORALLY, "resume");
        super.resume();
    }

    /**
     * @param screen screen type
     */
    public void changeScreen(int screen){
        switch(screen){
            case MENU:
                if(menuScreen == null) menuScreen = new MenuScreen(this);
                this.setScreen(menuScreen);
                break;
            case PREFERENCES:
                if(preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
                this.setScreen(preferencesScreen);
                break;
            case APPLICATION:
                boolean isClientOnly = false;
                if(play == null) play = new Play(isClientOnly);
                this.setScreen(play);
                break;
            case JOINAPPLICATION:
                isClientOnly = true;
                if(play == null) play = new Play(isClientOnly);
                this.setScreen(play);
                break;
            case ENDGAME:
                if(endScreen == null) endScreen = new EndScreen(this);
                this.setScreen(endScreen);
                break;
            default:
                System.err.println("No screen selected");
        }
    }
}

