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
            this.image = ImageIO.read(Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream(location.toString())));
            image.setAccelerationPriority(1);
        } catch (IOException|IllegalArgumentException|NullPointerException e) {
            System.out.println(location + " could not be loaded or is null");
            this.image = Textures.getTexture("placeholder").getImage();
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}
