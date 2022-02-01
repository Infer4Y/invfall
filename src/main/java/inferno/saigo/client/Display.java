package inferno.saigo.client;

import inferno.saigo.client.utils.display.DisplayReference;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Display extends JFrame {
    private final Canvas canvas = new Canvas();

    public Display(String title, int width, int height) {
        super(title);
        this.add(canvas);
        canvas.setSize(width,height);
        pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DisplayReference.running=false;
                System.exit(0);
            }
        });
    }

    public Canvas getCanvas() {
        return canvas;
    }


}
