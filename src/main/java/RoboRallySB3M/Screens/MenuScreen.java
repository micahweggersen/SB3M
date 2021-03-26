package RoboRallySB3M.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * This is the main screen class for managing of the menu bar, the code is derived from
 * https://www.gamedevelopment.blog/full-libgdx-game-tutorial-project-setup/
 */

public class MenuScreen implements Screen {

    private ParentScreen parent; //a field to store orchestrator
    private Stage stage;

    public MenuScreen(ParentScreen gameRunner){
        parent = gameRunner;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }
    //
    //https://www.gamedevelopment.blog/full-libgdx-game-tutorial-menu-control/

    @Override
    public void show() {
        Table table = new Table();
        table.setFillParent(true);
        //DEBUG MODE
        table.setDebug(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("src/assets/quantum-horizon/skin/quantum-horizon-ui.json"));

        TextButton newGame = new TextButton("New Game", skin);
        TextButton joinGame = new TextButton("Join Game", skin);
        TextButton preferences = new TextButton("Preferences", skin);
        TextButton exit = new TextButton("Exit", skin);

        //adding buttons
        table.add(newGame).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(joinGame).fillX().uniformX();
        table.row();
        table.add(preferences).fillX().uniformX();
        table.row();
        table.add(exit).fillX().uniformX();


        //create button listeners
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        //changes screens when corresponding button is clicked
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(ParentScreen.APPLICATION);
            }
        });
        //changes screens when corresponding button is clicked
        joinGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                parent.changeScreen(ParentScreen.JOINAPPLICATION);
            }
        });
        //changes screens when corresponding button is clicked
        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(ParentScreen.PREFERENCES);
            }
        });

    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        //change the stage's viewport when the screen size is changed
        stage.getViewport().update(width, height, true);

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
        stage.dispose();
    }
}