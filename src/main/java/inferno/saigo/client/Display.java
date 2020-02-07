package inferno.saigo.client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Display extends JFrame {
    private Canvas canvas;

    public Display(String title) throws IOException {
        super(title);
        canvas = new Canvas();
        this.add(canvas);
        canvas.setSize(64*6,64*6);
        pack();
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
