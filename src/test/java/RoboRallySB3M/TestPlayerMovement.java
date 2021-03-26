package RoboRallySB3M;

import RoboRallySB3M.Cards.Cards;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import org.junit.Assert;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.Before;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mockito.Mockito;
import com.badlogic.gdx.ApplicationListener;


public class TestPlayerMovement {

    private PlayerServer player;
    private Cards card;

    @Before
    public void setUp() {
        player = new PlayerServer(Direction.NORTH, "PlayerTest");
        player.position = new Vector2(0,0);

        Gdx.gl = mock(GL20.class);
        new HeadlessApplication(new EmptyApplication());
        Board.map = new TmxMapLoader().load("src/assets/example.tmx");
        Board.boardLayer = (TiledMapTileLayer) Board.map.getLayers().get("Board");
        Board.flagLayer = (TiledMapTileLayer) Board.map.getLayers().get("Flag");
        Board.holeLayer = (TiledMapTileLayer) Board.map.getLayers().get("Hole");
        Board.playerLayer = (TiledMapTileLayer) Board.map.getLayers().get("Player");
        Board.walls = (TiledMapTileLayer) Board.map.getLayers().get("Walls");
    }

    @Test
    public void playerMovesOneUpTest(){
        card = new Cards(180, "Move One", Direction.NORTH , 1);
        player.move(card);

        assertEquals(0, (int)player.position.x);
        assertEquals(1, (int)player.position.y);
    }

    @Test
    public void playerMovesTwoUpTest(){
        card = new Cards(180, "Move Two", Direction.NORTH , 2);
        player.move(card);

        assertEquals(0, (int)player.position.x);
        assertEquals(2, (int)player.position.y);
    }


    @Test
    public void playerMovesThreeUpTest(){
        card = new Cards(180, "Move Three", Direction.NORTH , 3);
        player.move(card);

        assertEquals(0, (int)player.position.x);
        assertEquals(3, (int)player.position.y);
    }

    @Test
    public void playerBackUpTest(){
        player = new PlayerServer(Direction.NORTH, "PlayerTest");
        player.position = new Vector2(0,1);

        card = new Cards(180, "Back Up", Direction.NORTH , -1);
        player.move(card);

        assertEquals(0, (int)player.position.x);
        assertEquals(0, (int)player.position.y);
    }

    @Test
    public void playerRotateLeftTest(){
        card = new Cards(180, "Rotate Left", Direction.EAST , 0);
        player.move(card);

        assertEquals(Direction.EAST, player.getDirection());
        assertEquals(0, (int)player.position.x);
        assertEquals(0, (int)player.position.y);
    }

    @Test
    public void playerRotateRightTest(){
        card = new Cards(180, "Rotate Right", Direction.EAST, 0);
        player.move(card);

        assertEquals(Direction.EAST, player.getDirection());
        assertEquals(0, (int)player.position.x);
        assertEquals(0, (int)player.position.y);
    }
}

