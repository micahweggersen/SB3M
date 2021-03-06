package RoboRallySB3M.Network.Server;

import RoboRallySB3M.Cards.Cards;
import RoboRallySB3M.Direction;
import RoboRallySB3M.GameObjects.Board;
import RoboRallySB3M.Network.Data.LaserData;
import RoboRallySB3M.Status;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Math.*;

public interface GameLogic {

    default void turn(PlayerServer player, ConcurrentHashMap<String, PlayerServer> players, HashMap<String, LaserData> laserData) throws InterruptedException {

        outOfBounds(player);
        playerMovedByAutowalks(players);
        outOfBounds(player);
        playerCollision(player, players, laserData);
        outOfBounds(player);
        playerMovedByPushers(players);
        outOfBounds(player);
        playerCollision(player, players, laserData);
        outOfBounds(player);
        handleRotationWheel(players);
        checkForDamage(player, laserData);
        checkFlags(player);
        playerRepairObject(player);

        orderHandling(player, players);
        turnHandling(players, laserData);
    }

    default void turnHandling(ConcurrentHashMap<String, PlayerServer> players, HashMap<String, LaserData> laserData) {
        int playersFinishedARound = 0;
        for (PlayerServer player : players.values()) {
            if(player.getFinishedRound()) {
                playersFinishedARound++;
                playerMovedByAutowalks(players);
            }
            else {
                System.out.println(player.getName() + "'s has not completed there turn!");
            }
        }

        //End of turn
        if (playersFinishedARound == players.size()) {
            for (PlayerServer player : players.values()) {
                player.setFinishedRound(false);
            }

            playerMovedByAutowalks(players);
            playerMovedByPushers(players);
            handleRotationWheel(players);
            for (PlayerServer player : players.values()) {
                outOfBounds(player);
                playerRepairObject(player);
                checkFlags(player);
            }
            checkVictoryCondition(players);
            System.out.println("All turns are complete!");
        }
    }

    /*
    Check if player is on a flag and that it's the next flag in the players series, if that's the case the flag
    corrdinate is the new checkpoint for the player.
     */
    default void checkFlags(PlayerServer player) {
        int x = (int) player.position.x;
        int y = (int) player.position.y;
        int id = 0;
        Map<String, Boolean> flags = player.getFlags();
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

    /*
    Change the direction of the player if they by the end of their movment is on a rotation wheel, by the direction indicated
    on the cell.
     */
    default void handleRotationWheel(ConcurrentHashMap<String, PlayerServer> players){
        for (PlayerServer player: players.values()) {
            if (Board.rotationGears.getCell((int) player.position.x, (int) player.position.y) != null){
                Direction dir = (Direction.stringToDirection(
                        Board.rotationGears.getCell((int)player.position.x, (int) player.position.y).getTile().getProperties().
                                get("Direction").toString()));
                player.move(new Cards(0, "Rotate Left", dir, 0));
            }
        }
    }

    /*
    Check that the player is inside the board, if not their reset to last checkpoint
     */
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

    default void playerCollision(PlayerServer player, ConcurrentHashMap<String, PlayerServer> players, HashMap<String, LaserData> laserData) {
        for (PlayerServer p: players.values()) {
            if(!player.getName().equals(p.getName()) && player.position.equals(p.position) ) {
                float x = p.position.x;
                float y = p.position.y;
                if(player.positionStartOfTurn.x < p.position.x) {
                    x = p.position.x += 1;
                }
                if(player.positionStartOfTurn.y < p.position.y) {
                    y = p.position.y += 1;
                }
                if(player.positionStartOfTurn.x > p.position.x) {
                    x = p.position.x -= 1;
                }
                if(player.positionStartOfTurn.y > p.position.y) {
                    y = p.position.y -= 1;
                }
                p.setPosition(new Vector2(x, y));
                p.setPositionStartOfTurn(new Vector2(x, y));
                if (isCellLaser((int)p.position.x ,(int)p.position.y, laserData)) {
                    addDamageToken(p);
                    System.out.println("damagetokenadded");
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

    default void playerMovedByPushers(ConcurrentHashMap<String, PlayerServer> players){

        for (PlayerServer player : players.values()) {
            if (Board.pushers.getCell((int) player.position.x, (int) player.position.y) != null){

                Direction dir = Direction.stringToDirection(
                        Board.pushers.getCell((int)player.position.x, (int) player.position.y).getTile().getProperties().
                                get("Direction").toString());

                player.position.y += Direction.changeInDirectionY(dir);
                player.position.x += Direction.changeInDirectionX(dir);

            }
        }
    }

    default void playerMovedByAutowalks(ConcurrentHashMap<String, PlayerServer> players) {

        for (PlayerServer player: players.values()) {
            if (Board.autoWalk.getCell((int) player.position.x, (int) player.position.y) != null){
                String i = Board.autoWalk.getCell((int) player.position.x, (int) player.position.y).getTile().getProperties().get("MOVE").toString();
                Direction dir = getDir(player);

                handleMovement(player, dir);

                if(Integer.valueOf(i)==2){
                    if(Board.autoWalk.getCell((int) player.position.x, (int) player.position.y) != null) dir = getDir(player);
                    handleMovement(player, dir);
                }
            }
        }
    }

    private Direction getDir(PlayerServer player){
        return (Direction.stringToDirection(
                Board.autoWalk.getCell((int)player.position.x, (int) player.position.y).getTile().getProperties().
                        get("Direction").toString()));
    }

    private void handleMovement(PlayerServer player, Direction dir){
        if(Board.autoWalk.getCell((int) player.position.x, (int) player.position.y) != null)
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

    /*
    If player is on a repair cell by the end of their movement,
    their healthpoint is increased by one, up to max health
     */
    default void playerRepairObject(PlayerServer player) {
        if (Board.repairShop.getCell((int) player.position.x, (int) player.position.y) != null)
            player.setDamageTokens(min(player.getDamageTokens()-1, player.getMaxHealth()));
    }

    default void checkForDamage(PlayerServer player, HashMap<String, LaserData> laserData) {
        System.out.println("checkdamage");
        if (isCellLaser((int)player.position.x ,(int)player.position.y, laserData)) {
            addDamageToken(player);
            System.out.println("damagetokenadded");
        }
        else  if (isCellHole((int)player.position.x,(int)player.position.y)) {
            loseLifeToken(player);
            System.out.println("lifetokenlost");
        }
    }

    /*
    Removes lifetoken if player loses all healthpoints,
    and set total life-tokens to total life-tokens minus one
     */
    default void loseLifeToken(PlayerServer player) {
        int lifeTokens = player.getLifeTokens();
        player.setLifeTokens(player.getLifeTokens()-1);
        player.setDamageTokens(0);
        player.setPosition(new Vector2(player.getPositionSaved()));
        if (lifeTokens <= 0)
            System.out.println("DEAD");
            player.setStatus(Status.DEAD);
    }

    default void addDamageToken(PlayerServer player) {
        player.setDamageTokens(player.getDamageTokens()+1);
        if (player.getDamageTokens() > 9) {
            loseLifeToken(player);
            player.setDamageTokens(0);
        }
    }

    default void checkVictoryCondition(ConcurrentHashMap<String, PlayerServer> players) {
        for (PlayerServer player: players.values()) {
            Map<String, Boolean> flags = player.getFlags();
            int i = 0;
            for (Boolean f: flags.values()) {
                if(Boolean.TRUE.equals(f)) {
                    i++;
                    if(i == 4) { System.out.println(player.getName() + " Has won the game!"); }
                }
            }
        }
    }

    default Boolean checkPreviousFlags(Map<String, Boolean> flags, int flagID) {
        for (int i = 1; i < flagID; i++) {
            if(!Boolean.TRUE.equals(flags.get("flag" + i))) return false;
        }
        return true;
    }

    default boolean isCellFlag(int x, int y) { return Board.flagLayer.getCell(x, y) != null; }

    default boolean isCellHole(int x, int y) { return Board.holeLayer.getCell(x, y) != null; }

    default boolean isCellLaser(int x, int y, HashMap<String, LaserData> lasers) {
        for (LaserData laser: lasers.values()) {
            if(x == laser.x && y == laser.y) {
                return true;
            }
        }
        return false;
    }
}
