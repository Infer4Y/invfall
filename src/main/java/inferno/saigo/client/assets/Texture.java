package inferno.saigo.client.assets;

import inferno.saigo.client.Main;
import inferno.saigo.client.utils.display.DisplayReference;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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
        BufferedImage newImage = new BufferedImage(
                image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }
}
