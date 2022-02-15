package inferno.saigo.client.assets;

import inferno.saigo.client.Main;
import inferno.saigo.client.utils.display.DisplayReference;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Fonts {
    public static Font OPEN_SANS = new Font(Font.SERIF, Font.PLAIN, 12);

    public static void init (){
        try {
            OPEN_SANS = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream("assets/saigo/fonts/OpenSans-Regular.ttf")));
            OPEN_SANS = OPEN_SANS.deriveFont(28f);
        } catch (FontFormatException | IOException ignored) {}

        DisplayReference.localGraphicsEnvironment.registerFont(OPEN_SANS);
    }
}
