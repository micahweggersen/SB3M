package com.RoboRallySB3M.Network.Server;

import com.RoboRallySB3M.Cards.Cards;
import com.RoboRallySB3M.Direction;
import com.RoboRallySB3M.GameObjects.Board;
import com.badlogic.gdx.Input;

public interface Movement {

    default void moveByKeyPress(int keycode, PlayerServer player) {
        switch (keycode) {
            case Input.Keys.NUM_1:
                player.move(new Cards(0, "Move One", Direction.NORTH, 1));
                break;
            case Input.Keys.NUM_2:
                player.move(new Cards(0, "Move Two", Direction.NORTH, 2));
                break;
            case Input.Keys.NUM_3:
                player.move(new Cards(0, "Move Three", Direction.NORTH, 3));
                break;
            case Input.Keys.NUM_4:
                player.move(new Cards(0, "Rotate Left", Direction.EAST, 0));
                break;
            case Input.Keys.NUM_5:
                player.move(new Cards(0, "Rotate Right", Direction.WEST, 0));
                break;
            case Input.Keys.NUM_6:
                player.move(new Cards(0, "U-Turn", Direction.SOUTH, 0));
                break;
            case Input.Keys.NUM_7:
                player.move(new Cards(0, "Back Up", Direction.NORTH, -1));
                break;
            case Input.Keys.LEFT:
                if (player.canMove(Direction.WEST, (int) player.position.x, (int) player.position.y)) {
                    player.position.x -= 1;
                }
                break;
            case Input.Keys.RIGHT:
                if (player.canMove(Direction.EAST, (int) player.position.x, (int) player.position.y)) {
                    player.position.x += 1;
                }
                break;
            case Input.Keys.UP:
                if (player.canMove(Direction.NORTH, (int) player.position.x, (int) player.position.y)) {
                    player.position.y += 1;
                }
                break;
            case Input.Keys.DOWN:
                if (player.canMove(Direction.SOUTH, (int) player.position.x, (int) player.position.y)) {
                    player.position.y -= 1;
                }
                break;
        }
    }

    /**
     * Checks if player can move to location
     *
     * @param direction player pointing direction
     * @return true if can move false if cannot move
     */
    default boolean canMove(Direction direction, int oldX, int oldY) {
        int x_change = Direction.changeInXdir(direction);
        int y_change = Direction.changeInYdir(direction);

        if (Board.walls.getCell(oldX, oldY) != null) {
            if (Board.walls.getCell(oldX, oldY).getTile().getProperties().containsKey(direction.toString()))
                return false;
        }
        if (Board.walls.getCell((oldX + x_change), oldY + y_change) != null) {
            return (!Board.walls.getCell(oldX + x_change, oldY + y_change).getTile().getProperties()
                    .containsKey(Direction.oppositeDirection(direction).toString()));
        }
        return true;
    }


}
