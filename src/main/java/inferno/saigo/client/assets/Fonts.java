package inferno.saigo.client.assets;

import inferno.saigo.client.Main;
import inferno.saigo.client.utils.display.DisplayReference;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Fonts {
    public static Font OPEN_SANS = new Font(Font.SERIF, Font.BOLD, 24);
    public static Font JETBRAINS_MONO = new Font(Font.MONOSPACED, Font.BOLD, 24);
    public static Font AUDIO_WIDE = new Font(Font.DIALOG, Font.BOLD, 24);

    public static void init (){
        try {
            OPEN_SANS = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream("assets/saigo/fonts/OpenSans-Regular.ttf")));
            OPEN_SANS = OPEN_SANS.deriveFont(Font.BOLD, 28f);
        } catch (FontFormatException | IOException ignored) {}

        try {
            JETBRAINS_MONO = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream("assets/saigo/fonts/JetBrainsMono-Regular.ttf")));
            JETBRAINS_MONO = JETBRAINS_MONO.deriveFont(Font.BOLD, 28f);
        } catch (FontFormatException | IOException ignored) {}

        try {
            AUDIO_WIDE = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream("assets/saigo/fonts/Audiowide-Regular.ttf")));
            AUDIO_WIDE = AUDIO_WIDE.deriveFont(Font.BOLD, 28f);
        } catch (FontFormatException | IOException ignored) {}

        DisplayReference.localGraphicsEnvironment.registerFont(OPEN_SANS);
        DisplayReference.localGraphicsEnvironment.registerFont(JETBRAINS_MONO);
    }
}
