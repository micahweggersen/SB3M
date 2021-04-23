package RoboRallySB3M;

import RoboRallySB3M.Cards.Cards;
import RoboRallySB3M.GameObjects.Board;
import RoboRallySB3M.Network.Data.LaserData;
import RoboRallySB3M.Network.Data.PlayerData;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


import RoboRallySB3M.Network.Server.LaserServer;
import RoboRallySB3M.Network.Server.PlayerServer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import org.junit.Before;
import org.junit.Test;
import RoboRallySB3M.Network.Server.GameLogic;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerLogicTest implements GameLogic{

    PlayerData testPlayer = PlayerData.newPlayer("testPlayer");
    private PlayerServer player = new PlayerServer(Direction.NORTH, "test", 1, 0, 3, "src/assets/playerTexture/player.png");
    private final ConcurrentHashMap<String, PlayerServer> players = new ConcurrentHashMap<String, PlayerServer>();
    //private HashMap<String, LaserData> laserData = new HashMap<>();
    //LaserServer laser = new LaserServer();*/

    @Before
    public void setUp() {
        players.put(player.getName(), player);
        //laserData = laser.findLaserLocation(players.values());*/
        Gdx.gl = mock(GL20.class);
        new HeadlessApplication(new EmptyApplication());
        Board.map = new TmxMapLoader().load("src/assets/newBoard10x10.tmx");
        Board.boardLayer = (TiledMapTileLayer) Board.map.getLayers().get("Board");
        Board.playerLayer = (TiledMapTileLayer) Board.map.getLayers().get("Player");
        Board.walls = (TiledMapTileLayer) Board.map.getLayers().get("Wall");
        Board.holeLayer = (TiledMapTileLayer) Board.map.getLayers().get("Hole");
        Board.repairShop = (TiledMapTileLayer) Board.map.getLayers().get("RepairShop");
        Board.autoWalk = (TiledMapTileLayer) Board.map.getLayers().get("Autowalk");
        Board.rotationGears = (TiledMapTileLayer) Board.map.getLayers().get("RotationGears");

        //player = new PlayerServer(Direction.NORTH, "PlayerTest", 0, 10,10, null);
        player.position = new Vector2(0,0);
    }
    @Test
    public void testDamageTokensAmount() {
        assertEquals(testPlayer.damageToken, 0);
    }

    @Test
    public void testLifeTokensAmount() {
        assertEquals(testPlayer.lifeTokens, 3);
    }

    @Test
    public void testLoseLifeToken() {
        int lifeTokens = 3;
        player.setLifeTokens(lifeTokens);
        loseLifeToken(player);
        assertNotEquals(lifeTokens, player.getLifeTokens());
        assertEquals(lifeTokens-1, player.getLifeTokens());

    }

    @Test
    public void testAddDamage() {
        int damageTokens = 0;
        player.setDamageTokens(damageTokens);
        addDamageToken(player);
        assertEquals(damageTokens + 1, player.getDamageTokens());
    }

    @Test
    public void testAddDamageToLoseLifeToken(){
        int damageTokens = 9;
        int lifeTokens = 3;
        player.setDamageTokens(damageTokens);
        player.setLifeTokens(lifeTokens);
        addDamageToken(player);
        assertEquals(0, player.getDamageTokens());
        assertEquals(lifeTokens-1, player.getLifeTokens());
    }

    @Test
    public void testAddTwoDamageToLoseLifeToken(){
        int damageTokens = 8;
        int lifeTokens = 3;
        player.setDamageTokens(damageTokens);
        player.setLifeTokens(lifeTokens);
        addDamageToken(player);
        addDamageToken(player);
        assertEquals(0, player.getDamageTokens());
        assertEquals(lifeTokens-1, player.getLifeTokens());
    }


    @Test
    public void testPlayerRepair(){
        int damageTokens = 7;
        int lifeTokens = 3;
        player.setDamageTokens(damageTokens);
        player.setLifeTokens(lifeTokens);
        player.setPosition(new Vector2(1,8));
        playerRepairObject(player);
        assertEquals(damageTokens-1, player.getDamageTokens());
    }

    @Test
    public void testPlayerAutoWalk() {
        player.setPosition(new Vector2(1,0));
        playerMovedByAutowalks(players);
        Vector2 newPos = new Vector2(0,1);
        assertEquals(newPos, player.getPosition());
    }

    @Test
    public void testPlayerRotationGear(){
        player.setPosition(new Vector2(0,4));
        handleRotationWheel(players);
        assertEquals(Direction.EAST, player.getDirection());

    }

}
