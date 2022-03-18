package inferno.saigo.client.rendering;

import inferno.saigo.client.assets.Texture;
import inferno.saigo.client.assets.Textures;
import inferno.saigo.client.utils.display.DisplayReference;
import inferno.saigo.common.items.Item;

import java.awt.*;

public class ObjectRenderingItemForPlayer extends ObjectRendering {
    private Texture texture;

    public ObjectRenderingItemForPlayer(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void render(Graphics2D graphics, int tileSize) {
        float xPos = (float) (tileSize/2 * Math.sin(Math.toRadians(DisplayReference.mouse_controller.mouseRotationFromCenter)));
        float yPos = (float) (tileSize/2 * Math.cos(Math.toRadians(DisplayReference.mouse_controller.mouseRotationFromCenter)));

        graphics.rotate(DisplayReference.mouse_controller.mouseRotationFromCenter, (DisplayReference.view.getWidth() >> 1), (DisplayReference.view.getHeight() >> 1));

        graphics.translate(((DisplayReference.view.getWidth() >> 1) - (tileSize >> 1))+xPos, ((DisplayReference.view.getHeight() >> 1) - (tileSize >> 1))+yPos);

        graphics.rotate(Math.toRadians(135), tileSize>>1, tileSize>>1);

        graphics.drawImage(texture.getImage(),0, 0, tileSize>>1, tileSize>>1,null);

        graphics.rotate(Math.toRadians(-135),tileSize>>1, tileSize>>1);

        graphics.translate(-((DisplayReference.view.getWidth() >> 1) - (tileSize >> 1))-xPos, -((DisplayReference.view.getHeight() >> 1) - (tileSize >> 1))-yPos);

        graphics.rotate(-DisplayReference.mouse_controller.mouseRotationFromCenter, (DisplayReference.view.getWidth() >> 1) , (DisplayReference.view.getHeight() >> 1));
    }

    public Texture getTexture() {
        return texture;
    }

    public ObjectRenderingItemForPlayer setTexture(Item item) {
        this.texture = Textures.getTexture(item.toString());
        return this;
    }
}
