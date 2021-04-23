package RoboRallySB3M.Network.Server;

import RoboRallySB3M.Cards.Cards;
import RoboRallySB3M.Direction;
import RoboRallySB3M.GameObjects.Board;
import com.badlogic.gdx.Input;

import java.util.Objects;

public interface Movement {

    /**
     * @param keycode key indicator
     * @param player character to be moved
     */
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
            default:
        }
    }

    /**
     * Checks if player can move to location
     *
     * @param direction player pointing direction
     * @return true if can move false if cannot move
     */
    default boolean canMove(Direction direction, int oldX, int oldY) {
        int xChange = Direction.changeInDirectionX(direction);
        int yChange = Direction.changeInDirectionY(direction);

        if (Board.walls.getCell(oldX, oldY) != null && Board.walls.getCell(oldX, oldY).getTile().getProperties().containsKey(direction.toString())) {
            return false;
        }
        if (Board.walls.getCell((oldX + xChange), oldY + yChange) != null) {
            return (!Board.walls.getCell(oldX + xChange, oldY + yChange).getTile().getProperties()
                    .containsKey(Objects.requireNonNull(Direction.oppositeDirection(direction)).toString()));
        }
        return true;
    }


}
