package RoboRallySB3M;

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

    public static TextureRegion[][] playerFig;

    public final String name;
    public final TiledMapTileLayer.Cell cell;

    public Vector2 position;
    public Vector2 positionUpdating = null;

    private float alpha = 0;

    public ClientPlayer(String name, Vector2 position) {
        super(new Texture("src/assets/player.png"));

        if (playerFig == null) {
            playerFig = TextureRegion.split(getTexture(), 300, 300);
        }

        this.name = name;
        this.cell = new TiledMapTileLayer.Cell();
        this.position = position;
    }

    public boolean updatePosition(PlayerData player) {
        Vector2 playerPosition = position;
        Vector2 newPlayerPosition = player.position;

        if (player.position == null) {
            return false;
        }

        this.positionUpdating = player.position;

        int x = (int) this.position.x;
        int y = (int) this.position.y;

        if (player.playerName.equals(this.name)) {
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

    @Override
    public void draw(Batch spriteBatch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }

    public void update(float delta) {
        Vector2 temp = positionUpdating;
        if (temp != null) {
            float alpha = getAlpha();
            position.lerp(temp, alpha);
        }
    }

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
