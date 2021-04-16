package com.RoboRallySB3M.Network.Server;

import com.RoboRallySB3M.Direction;
import com.RoboRallySB3M.GameObjects.Board;

public interface Movement {

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
