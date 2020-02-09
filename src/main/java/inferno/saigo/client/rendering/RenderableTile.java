package inferno.saigo.client.rendering;

import inferno.saigo.client.assets.Texture;
import inferno.saigo.client.assets.Textures;
import inferno.saigo.common.tiles.Tile;

import java.awt.*;
import java.io.IOException;

public class RenderableTile extends RenderableCoord {
    private Texture texture;
    private Tile tile;

    public RenderableTile(Tile tile, int x, int y) throws IOException {
        super(x, y);
        this.tile = tile;
        this.texture = Textures.getTexture(tile.getName()).getFirst();
    }

    @Override
    public void render(Graphics g, int tileSize) {
        g.translate(getX() * tileSize, getY() * tileSize);
        g.drawImage(texture.getImage(),getX() * tileSize,getY() * tileSize, tileSize, tileSize,null);
        g.translate(-getX() * tileSize, -getY() * tileSize);
    }
}
