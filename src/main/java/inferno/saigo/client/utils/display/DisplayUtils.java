package inferno.saigo.client.utils.display;

import inferno.saigo.client.assets.ImageUtils;

import java.awt.*;

public class DisplayUtils {
    public static void render() {
        if ( DisplayReference.buffer_strategy == null) {
            DisplayReference.display.getCanvas().createBufferStrategy(3);
            DisplayReference.buffer_strategy = DisplayReference.display.getCanvas().getBufferStrategy();
        }

        Graphics2D viewGraphics = DisplayReference.view.createGraphics();
        viewGraphics.setColor(Color.BLACK);
        viewGraphics.fillRect(0,0, DisplayReference.view.getWidth(), DisplayReference.view.getHeight());
        viewGraphics.setColor(Color.WHITE);
        DisplayReference.renderer.render(viewGraphics);
        viewGraphics.dispose();

        Graphics2D drawGraphics = (Graphics2D) DisplayReference.display.getCanvas().getBufferStrategy().getDrawGraphics();

        drawGraphics.setColor(Color.BLACK);
        drawGraphics.fillRect(0,0, DisplayReference.display.getCanvas().getWidth(), DisplayReference.display.getCanvas().getHeight());
        drawGraphics.setColor(Color.WHITE);

        ImageUtils.drawScaledImage(ImageUtils.resize(DisplayReference.view, DisplayReference.display.getCanvas().getWidth(), DisplayReference.display.getCanvas().getHeight()), DisplayReference.display.getCanvas(), drawGraphics);

        DisplayReference.buffer_strategy.show();
        DisplayReference.FPS++;
    }
}
