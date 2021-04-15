package com.RoboRallySB3M.Network.Server;

import com.RoboRallySB3M.Cards.Cards;
import com.RoboRallySB3M.Direction;
import com.RoboRallySB3M.GameObjects.Board;
import com.badlogic.gdx.math.Vector2;

import static java.lang.Math.abs;

public class PlayerServer {

    private final String name;
    private int turnOrder;
    private boolean finishedRound;
    private Direction direction;

    //Define player-coordinates
    public Vector2 position;

    public PlayerServer(Direction direction, String name, int turnOrder) {
        this.direction = direction;
        this.name = name;
        this.position = Vector2.Zero;
        this.turnOrder = turnOrder;
        this.finishedRound = false;
    }

    public boolean getFinishedRound() {
        return finishedRound;
    }

    public void setFinishedRound(boolean finishedRound) {
        this.finishedRound = finishedRound;
    }

    public void setTurnOrder(int turnOrder) {
        this.turnOrder = turnOrder;
    }

    public int getTurnOrder() {
        return turnOrder;
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
            momentum *= -1;
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
     * //TODO NOT STATIC
     *
     * @param direction player pointing direction
     * @return true if can move false if cannot move
     */
    public static boolean canMove(Direction direction, int oldX, int oldY) {

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
