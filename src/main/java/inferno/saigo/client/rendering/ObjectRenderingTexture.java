package inferno.saigo.client.rendering;

import inferno.saigo.client.assets.objects.Texture;
import inferno.saigo.client.utils.display.DisplayReference;

import java.awt.*;

public class ObjectRenderingTexture extends ObjectRendering {
    private final Texture texture;

    public ObjectRenderingTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void render(Graphics2D graphics, int tileSize) {
        graphics.translate(((DisplayReference.view.getWidth()/2) - tileSize/2), ((DisplayReference.view.getHeight()/2) - tileSize/2));

        //graphics.drawImage(texture.getImage(),(int)(getX()) * tileSize - (tileSize >> 1), (int)(getY()) * tileSize - (tileSize >> 1), tileSize, tileSize,null);
        graphics.drawImage(texture.getImage(),0, 0, tileSize, tileSize,null);

        graphics.translate(-((DisplayReference.view.getWidth()/2) - tileSize/2), -((DisplayReference.view.getHeight()/2) - tileSize/2));
    }
}
