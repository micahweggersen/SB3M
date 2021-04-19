package RoboRallySB3M.Network.Server;

import RoboRallySB3M.GameObjects.Board;
import com.badlogic.gdx.math.Vector2;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface GameLogic {

    default void turn(PlayerServer player, ConcurrentHashMap<String, PlayerServer> players) {
        checkFlags(player);
        outOfBounds(player);
        playerCollision(player, players);
        orderHandling(player, players);
        turnHandling(players);

    }

    default void checkFlags(PlayerServer player) {
        int x = (int) player.position.x;
        int y = (int) player.position.y;
        Map<String, Boolean> flags = player.getFlags();
        if(isCellFlag(x,y)){
            if(Board.flagLayer.getCell(x, y).getTile().getProperties().containsKey("1")) {
                flags.put("flag1", true);
            }
            if(Board.flagLayer.getCell(x, y).getTile().getProperties().containsKey("2")) {
                checkPreviousFlags(flags,2);
                flags.put("flag2", true);
            }
            if(Board.flagLayer.getCell(x, y).getTile().getProperties().containsKey("3")) {
                checkPreviousFlags(flags,3);
                flags.put("flag3", true);
            }
            if(Board.flagLayer.getCell(x, y).getTile().getProperties().containsKey("4")) {
                checkPreviousFlags(flags,4);
                flags.put("flag4", true);
            }
        }
    }

    default void outOfBounds(PlayerServer player) {
        int x = Board.boardLayer.getWidth();
        int y = Board.boardLayer.getHeight();

        if(player.position.x > x || player.position.x < 0) {
            player.setPosition(new Vector2(0, 0));
            System.out.println(player.getName() + " x Out of Bounds");
        }
        if(player.position.y > y || player.position.y < 0) {
            player.setPosition(new Vector2(0, 0));
            System.out.println(player.getName() + " y Out of Bounds");

        }


    }

    default void playerCollision(PlayerServer player, ConcurrentHashMap<String, PlayerServer> players) {


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
            playerMovedByObject(players);
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
                    if(i == 3) {
                        //TODO finish win condition
                        System.out.println(player.getName() + " Has won the game!");
                    }
                }
            }
        }
    }



    default void checkPreviousFlags(Map<String, Boolean> flags, int flagID) {
        for (int i = 1; i < 5; i++) {
            if(Boolean.TRUE.equals(flags.get("flag" + i))) {

            }
        }

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
