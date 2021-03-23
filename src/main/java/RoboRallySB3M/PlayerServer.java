package RoboRallySB3M;

import RoboRallySB3M.Cards.Cards;
import com.badlogic.gdx.math.Vector2;

import static java.lang.Math.abs;

public class PlayerServer {

    private final String name;
    private Direction direction;

    //Define player-coordinates
    public Vector2 position;

    public PlayerServer(Direction direction, String name) {
        this.direction = direction;
        this.name = name;
        this.position = Vector2.Zero;
    }

    public String getName() {
        return name;
    }

    public Direction getDirection() {
        return direction;
    }

    /**
     * @param card Card with directional value
     *             Moves the player to new location
     */
    public void move(Cards card) {
        if (card.getDirection() != null) {
            direction = Direction.toDirection((direction.value + card.getDirection().value) % 4);
        }

        int momentum = (direction == Direction.WEST || direction == Direction.SOUTH) ? -1 : 1;
        if (card.getMomentum() < 0) {
            momentum *= -1; //slå denne sammen med den oppe
            direction = Direction.oppositeDirection(direction);
        }

        if (direction == null) throw new IllegalArgumentException("The direction can't be null");

        for (int i = 0; i < abs(card.getMomentum()); i++) {
            if (!canMove(direction, (int) position.x, (int) position.y)) {
                break;
            }
            if (canMove(direction, (int) position.x, (int) position.y)) {
                if (direction == Direction.SOUTH || direction == Direction.NORTH) position.y += (momentum);
                if (direction == Direction.WEST || direction == Direction.EAST) position.x += (momentum);
            }
        }

        position = new Vector2(position.x + 0.00001F, position.y + 0.00001F);
    }

    /**
     * Checks if player can move to location
     *
     * @param direction player pointing direction
     * @return true if can move false if cannot move
     */
    public boolean canMove(Direction direction, int oldX, int oldY) {

        //skriv om
        int x_change = 0;
        int y_change = 0;
        if (direction == Direction.WEST) x_change = -1;
        if (direction == Direction.EAST) x_change = 1;
        if (direction == Direction.NORTH) y_change = 1;
        if (direction == Direction.SOUTH) y_change = -1;

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
