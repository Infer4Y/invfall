package inferno.saigo.client.rendering;

import inferno.saigo.client.assets.ResourceLocation;
import inferno.saigo.client.assets.Texture;
import inferno.saigo.common.tiles.Tile;

import java.awt.*;
import java.io.IOException;

public class RenderableTile extends Renderable {
    private ResourceLocation location;
    private Texture texture;
    private Tile tile;

    public RenderableTile(Tile tile) throws IOException {
        this.tile = tile;
        this.location = new ResourceLocation("textures/tiles/"+tile.getName()+".png");
        this.texture = new Texture(location);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture.getImage(),0,0,64,64,null);
    }
}
