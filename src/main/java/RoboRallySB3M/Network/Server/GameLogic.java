package RoboRallySB3M.Network.Server;

import RoboRallySB3M.Cards.Cards;
import RoboRallySB3M.Direction;
import RoboRallySB3M.GameObjects.Board;
import RoboRallySB3M.Network.Data.LaserData;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Math.max;
import static java.lang.Math.min;

public interface GameLogic {

    default void turn(PlayerServer player, ConcurrentHashMap<String, PlayerServer> players, HashMap<String, LaserData> laserData) {
        checkFlags(player);
        playerRepairObject(player);
        outOfBounds(player);
        checkHole(player);
        checkForDamage(player, laserData);
        playerCollision(player, players);
        orderHandling(player, players);
        turnHandling(players);

    }

    default void checkFlags(PlayerServer player) {
        int x = (int) player.position.x;
        int y = (int) player.position.y;
        int id = 0;
        Map<String, Boolean> flags = player.getFlags();
        System.out.println(flags);
        if(isCellFlag(x,y)){
            if(Board.flagLayer.getCell(x, y).getTile().getProperties().containsKey("1")) {
                id = 1;
                flags.put("flag1", true);
                player.setPositionSaved(new Vector2(x,y));
            }
            if(Board.flagLayer.getCell(x, y).getTile().getProperties().containsKey("2")) {
                id = 2;
                }
            if(Board.flagLayer.getCell(x, y).getTile().getProperties().containsKey("3")) {
                id = 3;
                }
            if(Board.flagLayer.getCell(x, y).getTile().getProperties().containsKey("4")) {
                id = 4;
                }
            if(checkPreviousFlags(flags,id)) {
                flags.put("flag" + id, true);
                player.setPositionSaved(new Vector2(x,y));
            }
        }
    }

    default void outOfBounds(PlayerServer player) {
        int x = Board.boardLayer.getWidth();
        int y = Board.boardLayer.getHeight();

        if(player.position.x > x-1 || player.position.x < 0) {
            player.setPosition(new Vector2(player.getPositionSaved()));
            System.out.println(player.getName() + " x Out of Bounds");
        }
        if(player.position.y > y-1 || player.position.y < 0) {
            player.setPosition(new Vector2(player.getPositionSaved()));
            System.out.println(player.getName() + " y Out of Bounds");
        }
    }

    default void checkHole(PlayerServer player) {
        if(isCellHole((int) player.position.x, (int) player.position.y)) {
            player.setLifeTokens(player.getLifeTokens() - 1);
            System.out.println("HOLE!");
        }
    }

    default void playerCollision(PlayerServer player, ConcurrentHashMap<String, PlayerServer> players) {
        for (PlayerServer p: players.values()) {
            if(!player.getName().equals(p.getName())) {
                if (player.position.equals(p.position)) {
                    System.out.println("Hit a player!");
                }
            }
        }

    }

    default void orderHandling(PlayerServer player, ConcurrentHashMap<String, PlayerServer> players) {

        player.setFinishedRound(true);
        player.setTurnOrder(player.getTurnOrder()-1);

        for (PlayerServer p: players.values()) {
            if (!player.getName().equals(p.getName())) { p.setTurnOrder(p.getTurnOrder()-1); }
            if(p.getTurnOrder() < 0) { p.setTurnOrder(players.size()-1); }
        }
    }

    default void playerMovedByObject(ConcurrentHashMap<String, PlayerServer> players) {

        for (PlayerServer player: players.values()) {
            if (Board.autoWalk.getCell((int) player.position.x, (int) player.position.y) != null){
                //TODO: make a concurrent update of x and y values
                String i = Board.autoWalk.getCell((int) player.position.x, (int) player.position.y).getTile().getProperties().get("MOVE").toString();
                Direction dir = getDir(player);

                handleMovment(player, dir);

                if(Integer.valueOf(i)==2){
                    if(Board.autoWalk.getCell((int) player.position.x, (int) player.position.y) != null) dir = getDir(player);
                    handleMovment(player, dir);
                }
            }
        }
    }

    private Direction getDir(PlayerServer player){
        return (Direction.stringToDirection(
                Board.autoWalk.getCell((int)player.position.x, (int) player.position.y).getTile().getProperties().
                        get("Direction").toString()));
    }

    private void handleMovment(PlayerServer player, Direction dir){
        handleTurning(player);
        player.position.y += Direction.changeInDirectionY(dir);
        player.position.x += Direction.changeInDirectionX(dir);
    }

    private void handleTurning(PlayerServer player){
        if(Board.autoWalk.getCell((int) player.position.x, (int) player.position.y).getTile().getProperties().get("Turning") != null){
            Direction turningDir = Direction.stringToDirection(Board.autoWalk.getCell((int)player.position.x, (int) player.position.y).getTile().getProperties().get("Turning").toString());
            player.move(new Cards(0, "Rotate Left", turningDir, 0));
        }
    }

    default void playerRepairObject(PlayerServer player) {
        if (Board.repairShop.getCell((int) player.position.x, (int) player.position.y) != null)
            player.setHealth(min(player.getHealth()+1, player.getMaxHealth()));
    }

    default void turnHandling(ConcurrentHashMap<String, PlayerServer> players) {
        int temp = 0;
        for (PlayerServer player : players.values()) {
            if(player.getFinishedRound()) {
                temp++;
                playerMovedByObject(players);
                System.out.println(player.getName() + "'s round is finished!");
            }
            else {
                System.out.println(player.getName() + "'s has not completed there turn!");
            }
        }

        //End of turn
        if (temp == players.size()) {
            for (PlayerServer player : players.values()) {
                player.setFinishedRound(false);
            }

            checkVictoryCondition(players);
            System.out.println("All turns are complete!");
        }
    }

    default void checkForDamage(PlayerServer player, HashMap<String, LaserData> laserData) {
        System.out.println("checkdamage");
        if (isCellLaser((int)player.position.x ,(int)player.position.y, laserData)) {
            addDamageToken(player);
            System.out.println("damagetokenadded");
        }
        else if (isCellHole((int)player.position.x,(int)player.position.y)) {
            loseLifeToken(player);
            System.out.println("lifetokenlost");
        }
    }

    default void loseLifeToken(PlayerServer player) {
        int lifeTokens = player.getLifeTokens();
        lifeTokens -= 1;
        if (lifeTokens <= 0)
            player.setStatus(PlayerServer.Status.DEAD);
    }

    default void addDamageToken(PlayerServer player) {
        int damageTokens = player.getDamageTokens();
        damageTokens += 1;
        if (damageTokens >= 10)
            loseLifeToken(player);
    }

    default void checkVictoryCondition(ConcurrentHashMap<String, PlayerServer> players) {
        for (PlayerServer player: players.values()) {
            Map<String, Boolean> flags = player.getFlags();
            int i = 0;
            for (Boolean f: flags.values()) {
                if(Boolean.TRUE.equals(f)) {
                    i++;
                    if(i == 4) {
                        //TODO finish win condition
                        System.out.println(player.getName() + " Has won the game!");
                    }
                }
            }
        }
    }



    default Boolean checkPreviousFlags(Map<String, Boolean> flags, int flagID) {
        for (int i = 1; i < flagID; i++) {
            if(!Boolean.TRUE.equals(flags.get("flag" + i))) {
                System.out.println("Cannot collect flag yet");
                return false;
            }
        }
        System.out.println("Collected flag");
        return true;
    }

    default boolean isCellFlag(int x, int y) {
        return Board.flagLayer.getCell(x, y) != null;
    }
    default boolean isCellHole(int x, int y) {
        return Board.holeLayer.getCell(x, y) != null;
    }
    default boolean isCellLaser(int x, int y, HashMap<String, LaserData> lasers) {
        for (LaserData laser: lasers.values()) {
            if(x == laser.x && y == laser.y) {
                return true;
            }
        }
        return false;
    }
}
