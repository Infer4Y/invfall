package inferno.saigo.client.assets;

import inferno.saigo.client.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Texture {
    private BufferedImage image;
    private ResourceLocation location;

    public Texture(ResourceLocation location) throws IOException {
        this.location = location;
        this.image = ImageIO.read(Main.class.getClassLoader().getResourceAsStream(location.toString()));
    }

    public BufferedImage getImage() {
        return image;
    }
}
