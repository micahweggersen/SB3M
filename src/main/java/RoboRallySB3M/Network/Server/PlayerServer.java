package RoboRallySB3M.Network.Server;

import RoboRallySB3M.Cards.Cards;
import RoboRallySB3M.Direction;
import RoboRallySB3M.GameObjects.Board;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;

public class PlayerServer  implements Movement {

    private final String name;
    private int damageTokens;
    private int health;
    private int turnOrder;
    private boolean finishedRound;
    private Direction direction;
    private int lifeTokens;
    private Status status;
    private Map<String, Boolean> flags;
    private String playerTexture;

    //Define player-coordinates
    public Vector2 position;
    public Vector2 positionSaved;

    public Vector2 getPosition() {
        return position;
    }


    public PlayerServer(Direction direction, String name, int turnOrder, int damageToken, int lifeTokens, String playerTexture) {
        this.direction = direction;
        this.name = name;
        this.position = Vector2.Zero;
        this.positionSaved = Vector2.Zero;
        this.turnOrder = turnOrder;
        this.finishedRound = false;
        this.damageTokens = damageTokens;
        this.lifeTokens = lifeTokens;
        this.damageTokens = damageToken;
        this.health = health;
        this.flags = initializeFlags();
        this.playerTexture = playerTexture;
    }

    public Vector2 getPositionSaved() {
        return positionSaved;
    }

    public void setPositionSaved(Vector2 positionSaved) {
        this.positionSaved = positionSaved;
    }
    public String getPlayerTexture() {
        return playerTexture;
    }

    public void setPlayerTexture(String playerTexture) {
        this.playerTexture = playerTexture;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    private Map<String, Boolean> initializeFlags() {
        flags = new HashMap();
        for (int i = 1; i < 5; i++) {
            flags.put("flag"+i, false);
        }
        return flags;
    }

    public Map<String, Boolean> getFlags() {
        return flags;
    }

    public void setFlags(Map<String, Boolean> flags) {
        this.flags = flags;
    }

    public int getDamageTokens() {
        return damageTokens;
    }

    public void setDamageTokens(int health) {
        this.damageTokens = damageTokens;
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
        checkForDamage();
    }

    public void checkForDamage() {
        float currentX = position.x;
        float currentY = position.y;
        System.out.println("checkdamage");
        if (Board.isCellLaser((int)currentX,(int)currentY)) {
            addDamageToken();
            System.out.println("damagetokenadded");
        }
        else if (Board.isCellHole((int)currentX,(int)currentY)) {
            loseLifeToken();
            System.out.println("lifetokenlost");
        }

    }

    public void loseLifeToken() {
        lifeTokens -= 1;
        if (lifeTokens <= 0)
            setStatus(Status.DEAD);
    }

    public int getLifeTokens() {
        return lifeTokens;
    }

    public void addDamageToken() {
        damageTokens += 1;
        if (damageTokens >= 10)
            loseLifeToken();
    }

    public void powerDown() {
        setStatus(Status.POWERDOWN);
        damageTokens = 0;
    }

    public boolean isPowerDown() {
        return status == Status.POWERDOWN;
    }

    public void setStatus(Status newStatus) {
        this.status = newStatus;
    }

    public Status getStatus() {
        return status;
    }

    public enum Status {
        ALIVE,
        DEAD,
        POWERDOWN
    }
}
