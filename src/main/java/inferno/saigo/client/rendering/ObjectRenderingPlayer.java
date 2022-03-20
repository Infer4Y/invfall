package inferno.saigo.client.rendering;

import inferno.saigo.client.assets.objects.Texture;
import inferno.saigo.client.utils.display.DisplayReference;

import java.awt.*;

public class ObjectRenderingPlayer extends ObjectRendering {
    private final Texture texture;

    public ObjectRenderingPlayer(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void render(Graphics2D graphics, int tileSize) {
        graphics.rotate(DisplayReference.mouse_controller.mouseRotationFromCenter, (DisplayReference.view.getWidth() >> 1), (DisplayReference.view.getHeight() >> 1));

        graphics.translate(((DisplayReference.view.getWidth() >> 1) - (tileSize >> 1)), ((DisplayReference.view.getHeight() >> 1) - (tileSize >> 1)));

        graphics.drawImage(texture.getImage(),0, 0, tileSize, tileSize,null);

        graphics.translate(-((DisplayReference.view.getWidth() >> 1) - (tileSize >> 1)), -((DisplayReference.view.getHeight() >> 1) - (tileSize >> 1)));

        graphics.rotate(-DisplayReference.mouse_controller.mouseRotationFromCenter, (DisplayReference.view.getWidth() >> 1) , (DisplayReference.view.getHeight() >> 1));
    }
}
