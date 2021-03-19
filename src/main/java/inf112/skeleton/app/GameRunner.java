package inf112.skeleton.app;

import com.badlogic.gdx.*;
import inf112.skeleton.app.screens.Play;

public class GameRunner extends Game {

    @Override
    public void create() {
        Gdx.app.log("RoboRally", "create");
        setScreen(new Play());
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
}



