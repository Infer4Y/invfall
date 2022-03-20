package inferno.saigo.client.assets.objects;

import inferno.saigo.client.Main;
import inferno.saigo.client.assets.collections.Textures;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.IOException;
import java.util.Objects;

public class Texture {
    private BufferedImage image;

    public Texture(ResourceLocation location) {
        try {
            this.image = convertToARGB(ImageIO.read(Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream(location.toString()))));
            image.setAccelerationPriority(1);
        } catch (IOException|IllegalArgumentException|NullPointerException e) {
            System.out.println(location + " could not be loaded or is null");
            this.image = Textures.getTexture("placeholder").getImage();
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    private BufferedImage convertToARGB(BufferedImage image) {
        GraphicsEnvironment localGraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment ();
        GraphicsDevice defaultScreenDevice = localGraphicsEnvironment.getDefaultScreenDevice ();
        GraphicsConfiguration defaultConfiguration = defaultScreenDevice.getDefaultConfiguration ();

        BufferedImage newImage = defaultConfiguration.createCompatibleImage(
                image.getWidth(), image.getHeight(), image.getTransparency());

        Graphics2D newImageGraphics = newImage.createGraphics();

        newImageGraphics.drawImage(image, 0, 0, null);
        newImageGraphics.dispose();

        return newImage;
    }
}
