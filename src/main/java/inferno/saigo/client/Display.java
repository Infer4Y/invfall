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

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        canvas.resize(this.rootPane.getSize());
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
