package inferno.saigo.client.utils.display;

import inferno.saigo.client.assets.utils.ImageUtils;
import inferno.saigo.client.utils.client.ClientUtils;
import inferno.saigo.common.configuration.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DisplayUtils {
    public static void render() {
        if ( DisplayReference.buffer_strategy == null) {
            DisplayReference.display.getCanvas().createBufferStrategy(2);
            DisplayReference.buffer_strategy = DisplayReference.display.getCanvas().getBufferStrategy();
        }

        Graphics2D viewGraphics = DisplayReference.view.createGraphics();
        viewGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        viewGraphics.setColor(Color.BLACK);
        viewGraphics.fillRect(0,0, DisplayReference.view.getWidth(), DisplayReference.view.getHeight());
        viewGraphics.setColor(Color.WHITE);
        DisplayReference.renderer.render(viewGraphics);

        viewGraphics.dispose();

        Graphics2D drawGraphics = (Graphics2D) DisplayReference.display.getCanvas().getBufferStrategy().getDrawGraphics();
        drawGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawGraphics.setColor(Color.BLACK);
        drawGraphics.fillRect(0,0, DisplayReference.display.getCanvas().getWidth(), DisplayReference.display.getCanvas().getHeight());
        drawGraphics.setColor(Color.WHITE);

        ImageUtils.drawScaledImage(ImageUtils.resize(DisplayReference.view, DisplayReference.display.getCanvas().getWidth(), DisplayReference.display.getCanvas().getHeight()), DisplayReference.display.getCanvas(), drawGraphics);

        if (ClientUtils.shouldScreenshot) {
            File screenshotsFolder = new File(Settings.getHomeDir().getPath() + File.separator + "screenshots");
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
            String strDate = dateFormat.format(date);
            ClientUtils.shouldScreenshot = false;
            try {
                if ( !screenshotsFolder.exists() ) {
                    if(screenshotsFolder.mkdir()) {
                        System.out.println("Screenshots folder created");
                    }
                }
                ImageIO.write(DisplayReference.view, "png", new File(screenshotsFolder.getPath() + File.separator + Settings.name + "-Screenshot-" + strDate + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        drawGraphics.dispose();
        DisplayReference.buffer_strategy.show();
        DisplayReference.FPS++;
    }
}
