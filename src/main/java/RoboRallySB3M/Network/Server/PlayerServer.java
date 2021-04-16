package RoboRallySB3M.Network.Server;

import RoboRallySB3M.Cards.Cards;
import RoboRallySB3M.Direction;
import com.badlogic.gdx.math.Vector2;

import static java.lang.Math.abs;

public class PlayerServer  implements Movement {

    private final String name;
    private int damageToken;
    private int health;
    private int turnOrder;
    private boolean finishedRound;
    private Direction direction;

    //Define player-coordinates
    public Vector2 position;

    public PlayerServer(Direction direction, String name, int turnOrder, int damageToken, int health) {
        this.direction = direction;
        this.name = name;
        this.position = Vector2.Zero;
        this.turnOrder = turnOrder;
        this.finishedRound = false;
        this.damageToken = damageToken;
        this.health = health;
    }

    public int getDamageToken() {
        return damageToken;
    }

    public void setDamageToken(int health) {
        this.damageToken = damageToken;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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
}
