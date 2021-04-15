package com.RoboRallySB3M.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class ParentScreen extends Game {

    private boolean isClientOnly;
    private LoadingScreen loadingScreen;
    private PreferencesScreen preferencesScreen;
    private MenuScreen menuScreen;
    private EndScreen endScreen;
    private Play play;

    public final static int MENU = 0;
    public final static int PREFERENCES = 1;
    public final static int APPLICATION = 2;
    public final static int JOINAPPLICATION = 3;
    public final static int ENDGAME = 4;

    public ParentScreen() {
    }

    @Override
    public void create() {
        Gdx.app.log("RoboRally", "create");
        loadingScreen = new LoadingScreen(this);
        setScreen(loadingScreen);
    }

    @Override
    public void dispose() {
        Gdx.app.log("RoboRally", "dispose");
        super.dispose();
    }

    @Override
    public void render() { super.render(); }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("RoboRally", "resize");
        super.resize(width, height);
    }

    @Override
    public void pause() {
        Gdx.app.log("RoboRally", "pause");
        super.pause();
    }

    @Override
    public void resume() {
        Gdx.app.log("RoboRally", "resume");
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
                isClientOnly = false;
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
        }
    }
}

