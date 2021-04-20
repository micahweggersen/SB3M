package RoboRallySB3M.Network.Server;

import RoboRallySB3M.GameObjects.Board;
import com.badlogic.gdx.math.Vector2;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface GameLogic {

    default void turn(PlayerServer player, ConcurrentHashMap<String, PlayerServer> players) {
        checkFlags(player);
        outOfBounds(player);
        checkHole(player);
        playerCollision(player, players);
        orderHandling(player, players);
        turnHandling(players);

    }

    default void checkFlags(PlayerServer player) {
        int x = (int) player.position.x;
        int y = (int) player.position.y;
        int id = 0;
        Map<String, Boolean> flags = player.getFlags();
        System.out.println(flags.values());
        if(isCellFlag(x,y)){
            if(Board.flagLayer.getCell(x, y).getTile().getProperties().containsKey("1")) {
                id = 1;
                flags.put("flag1", true);
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
            System.out.println("HOLE!");
        }
    }

    default void playerCollision(PlayerServer player, ConcurrentHashMap<String, PlayerServer> players) {
        for (PlayerServer p: players.values()) {
            if(player.position.equals(p.position)) {
                System.out.println("Hit a player!");
            }
        }

    }

    default void orderHandling(PlayerServer player, ConcurrentHashMap<String, PlayerServer> players) {
        player.setFinishedRound(true);
        player.setTurnOrder(player.getTurnOrder()-1);

        for (PlayerServer p: players.values()) {
            if (!player.getName().equals(p.getName())) {
                p.setTurnOrder(p.getTurnOrder()-1);
            }
            if(p.getTurnOrder() < 0) {
                p.setTurnOrder(players.size()-1);
            }
        }
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

    default void playerMovedByObject(ConcurrentHashMap<String, PlayerServer> players) {
        for (PlayerServer player: players.values()) {
            //TODO: use move methode
            if(isCellSpeedOne((int) player.position.x, (int) player.position.y)) {
                player.position.y += 1;
            }
            if(isCellSpeedTwo((int) player.position.x, (int) player.position.y)) {
                player.position.y += 2;
            }

        }
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

    default boolean isCellSpeedOne(int x, int y) {
        return Board.speedOne.getCell(x,y) != null;
    }
    default boolean isCellSpeedTwo(int x, int y) {
        return Board.speedTwo.getCell(x,y) != null;
    }
    default boolean isCellFlag(int x, int y) {
        return Board.flagLayer.getCell(x, y) != null;
    }
    default boolean isCellHole(int x, int y) {
        return Board.holeLayer.getCell(x, y) != null;
    }

}
