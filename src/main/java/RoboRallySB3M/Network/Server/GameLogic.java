package RoboRallySB3M.Network.Server;

import RoboRallySB3M.Direction;
import RoboRallySB3M.GameObjects.Board;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Math.max;
import static java.lang.Math.min;

public interface GameLogic {

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

                String i = Board.autoWalk.getCell((int) player.position.x, (int) player.position.y).getTile().getProperties().get("MOVE").toString();
                Direction dir =
                        (Direction.stringToDirection(
                                Board.autoWalk.getCell((int)player.position.x, (int) player.position.y).getTile().getProperties().get("Direction").toString()));

                player.position.x += Direction.changeInDirectionX(dir)*Integer.valueOf(i);
                player.position.y += Direction.changeInDirectionY(dir)*Integer.valueOf(i);
            }
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
            System.out.println("All turns are complete!");
        }
    }

    default boolean isCellFlag(int x, int y) { return Board.flagLayer.getCell(x, y) != null; }
    default boolean isCellHole(int x, int y) { return Board.holeLayer.getCell(x, y) != null; }

}
