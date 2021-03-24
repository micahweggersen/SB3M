package RoboRallySB3M.Screens;

import RoboRallySB3M.GameRunner;
import com.badlogic.gdx.Screen;


public class LoadingScreen implements Screen {
    private GameRunner parent; //a field to store orchestrator

    public LoadingScreen(GameRunner gameRunner){
        parent = gameRunner;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        parent.changeScreen(GameRunner.MENU);
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