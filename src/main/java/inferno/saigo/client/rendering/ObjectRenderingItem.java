package inferno.saigo.client.rendering;

import inferno.saigo.client.assets.objects.Texture;
import inferno.saigo.client.assets.collections.Textures;
import inferno.saigo.common.items.Item;

import java.awt.*;

public class ObjectRenderingItem extends ObjectRenderingCoord {
    private final Texture texture;

    public ObjectRenderingItem(Item item, int x, int y) {
        super(x, y);
        this.texture = Textures.getTexture(item.toString());
    }

    @Override
    public void render(Graphics2D graphics, int tileSize) {
        graphics.translate((int)(getY()) * tileSize - (tileSize >> 1), (int)(getX()) * tileSize - (tileSize >> 1));

        //graphics.drawImage(texture.getImage(),(int)(getX()) * tileSize - (tileSize >> 1), (int)(getY()) * tileSize - (tileSize >> 1), tileSize, tileSize,null);
        graphics.drawImage(texture.getImage(),0, 0, tileSize, tileSize,null);

        graphics.translate(-(int)(getY()) * tileSize + (tileSize >> 1), -(int)(getX()) * tileSize + (tileSize >> 1));
    }
}
