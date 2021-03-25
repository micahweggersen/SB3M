package inf112.skeleton.app.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.GL30;
import inf112.skeleton.app.Board;
import inf112.skeleton.app.Laser;
import inf112.skeleton.app.Player;


public class Play implements Screen {

    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Player player;

    @Override
    public void show() {

        Board.map = new TmxMapLoader().load("src/assets/testmap.tmx");
        Board.boardLayer = (TiledMapTileLayer) Board.map.getLayers().get("Board");
        Board.flagLayer = (TiledMapTileLayer) Board.map.getLayers().get("Flag");
        Board.holeLayer = (TiledMapTileLayer) Board.map.getLayers().get("Hole");
        Board.playerLayer = (TiledMapTileLayer) Board.map.getLayers().get("Player");
        Board.walls = (TiledMapTileLayer) Board.map.getLayers().get("Walls");
        Board.laserHoro = (TiledMapTileLayer) Board.map.getLayers().get("LaserH");
        Board.laserVert = (TiledMapTileLayer) Board.map.getLayers().get("LaserV");

        camera = new OrthographicCamera();
        renderer = new OrthogonalTiledMapRenderer(Board.map, (float) (1.0/300.0));

        player = new Player(new Sprite(new Texture("src/assets/player.png")));
        player.create();
        Laser.createLaser();
        Board.laserHoro.setVisible(true);
        Board.laserVert.setVisible(true);

        Gdx.input.setInputProcessor(player); //tar imot input
    }

    @Override
    public void render(float v) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);



        renderer.getBatch().begin();
        Laser.drawLaser();
        player.draw(renderer.getBatch());
        renderer.getBatch().end();

        renderer.setView(camera);
        renderer.render();

//        Gdx.graphics.setContinuousRendering(false);
//        Gdx.graphics.requestRendering();
    }

    @Override
    public void resize(int i, int i1) {
        camera.setToOrtho(false, 5, 5);
        camera.position.x = 2.5F;
        camera.update();
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose()  {
        Board.map.dispose();
        renderer.dispose();
        player.getTexture().dispose();
    }
}
