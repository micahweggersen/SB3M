package RoboRallySB3M;

import RoboRallySB3M.Screens.Play;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class GameRunner extends Game {

    private boolean isClientOnly;

    public GameRunner(boolean isClientOnly) {
        this.isClientOnly = isClientOnly;
    }

    @Override
    public void create() {
        Gdx.app.log("RoboRally", "create");
        setScreen(new Play(isClientOnly));
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

