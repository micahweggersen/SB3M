package RoboRallySB3M.Network.Client;

import RoboRallySB3M.GameObjects.Board;
import RoboRallySB3M.Network.Data.PlayerData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

public class ClientPlayer extends Sprite {

    public TextureRegion[][] playerFig;

    public final String name;
    public final TiledMapTileLayer.Cell cell;

    public Vector2 position;
    public Vector2 positionUpdating = null;

    private float alpha = 0;

    /**
     * @param name of player
     * @param position of player on map
     */
    public ClientPlayer(String name, Vector2 position) {
        //gets texture
        super(new Texture("src/assets/player.png"));
        //sets playerFig
        if (playerFig == null) {
            playerFig = TextureRegion.split(getTexture(), 300, 300);
        }

        this.name = name;
        this.cell = new TiledMapTileLayer.Cell();
        this.position = position;
    }

    /**
     * @param player updates the visual location of each player. This is not the actual location of player.
     * @return true if player was not null
     */
    public boolean updatePosition(PlayerData player) {

        if (player.position == null) {
            return false;
        }

        this.positionUpdating = player.position;

        int x = (int) this.position.x;
        int y = (int) this.position.y;

        if (player.playerName.equals(this.name)) {
            if(player.playerTexture != null) {
                playerFig = TextureRegion.split(new Texture(player.playerTexture), 300, 300);
            }
            // Customize your own piece
            cell.setFlipHorizontally(true);
        }

        if (Board.isCellFlag(x, y)) {
            cell.setTile(new StaticTiledMapTile(playerFig[0][2]));
        } else if (Board.isCellHole(x, y)) {
            cell.setTile(new StaticTiledMapTile(playerFig[0][1]));
        } else {
            cell.setTile(new StaticTiledMapTile(playerFig[0][0]));
        }

        Board.playerLayer.setCell(x, y, cell.setRotation(player.direction.value));

        return true;
    }

    /**
     * @param spriteBatch draws the player figure from update
     */
    @Override
    public void draw(Batch spriteBatch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }

    /**
     * @param delta
     * a path from start position to final position and updates at each point
     */
    public void update(float delta) {
        Vector2 temp = positionUpdating;
        if (temp != null) {
            float alpha = getAlpha();
            position.lerp(temp, alpha);
        }
    }

    /**
     * @return pacing of the path
     */
    private float getAlpha() {
        alpha += 0.7;
        if (alpha >= 1) alpha = 0;
        if (position.epsilonEquals(positionUpdating, 0.005F)) {
            alpha = 1;

            positionUpdating = null;
        }
        return alpha;
    }
}
