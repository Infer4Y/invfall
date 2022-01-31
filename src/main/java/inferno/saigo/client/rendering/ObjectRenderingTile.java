package inferno.saigo.client.rendering;

import inferno.saigo.client.assets.Texture;
import inferno.saigo.client.assets.Textures;
import inferno.saigo.common.tiles.Tile;

import java.awt.*;

public class ObjectRenderingTile extends ObjectRenderingCoord {
    private final Texture texture;

    public ObjectRenderingTile(Tile tile, int x, int y) {
        super(x, y);
        this.texture = Textures.getTexture(tile.getName()).getFirst();
    }

    @Override
    public void render(Graphics g, int tileSize) {
        g.translate(getX() * tileSize, getY() * tileSize);
        g.drawImage(texture.getImage(),getX() * tileSize,getY() * tileSize, tileSize, tileSize,null);
        g.translate(-getX() * tileSize, -getY() * tileSize);
    }
}
