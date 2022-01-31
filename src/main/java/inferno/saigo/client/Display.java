package inferno.saigo.client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Display extends JFrame {
    private final Canvas canvas = new Canvas();

    public Display(String title) {
        super(title);
        this.add(canvas);
        canvas.setSize(64*6,64*6);
        pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
