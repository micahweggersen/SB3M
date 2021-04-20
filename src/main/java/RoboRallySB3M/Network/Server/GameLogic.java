package RoboRallySB3M.Network.Server;

import RoboRallySB3M.Direction;
import RoboRallySB3M.GameObjects.Board;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public interface GameLogic {


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

    default void playerMovedByObject(ConcurrentHashMap<String, PlayerServer> players) {

        for (PlayerServer player: players.values()) {

            if (Board.autowalk.getCell((int) player.position.x, (int) player.position.y) != null){

                String i = Board.autowalk.getCell((int) player.position.x, (int) player.position.y).getTile().getProperties().get("MOVE").toString();
                int j = Integer.valueOf(i);

                Direction dir =
                        (Direction.stringToDirection(
                                Board.autowalk.getCell((int)player.position.x, (int) player.position.y).getTile().getProperties().get("Direction").toString()));
                player.position.x += Direction.changeInDirectionX(dir)*j;
                player.position.y += Direction.changeInDirectionY(dir)*j;

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
            System.out.println("All turns are complete!");
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
