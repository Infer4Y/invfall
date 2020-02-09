package inferno.saigo.client;

import inferno.saigo.client.assets.ImageUtils;
import inferno.saigo.client.rendering.Renderable;
import inferno.saigo.client.rendering.RenderableText;
import inferno.saigo.client.rendering.RenderableTile;
import inferno.saigo.client.rendering.Renderer;
import inferno.saigo.common.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class Main {
    static boolean running = true;
    static Display display;
    static Renderer renderer;

    static final long NANOSECOND        = 1000000000;
    static final double OPTIMAL_TICKS   = 10.0;
    static final double OPTIMAL_TIME    = NANOSECOND / OPTIMAL_TICKS;

    static int FPS;

    static long lastLoopTime = System.nanoTime();
    static long currentTime;
    static double deltaTime;
    static long secondTimer = System.currentTimeMillis();

    static BufferStrategy bs = null;

    static int map[][] = new int[200][200];

    public static void main(String[] args) throws IOException {
        display = new Display("Test");
        renderer = new Renderer();

        renderer.gridspaceX = map[0].length-1;
        renderer.tileSize = 32;

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                renderer.add(0, new RenderableText(String.valueOf(x+y),x,y));;
            }
        }

        while (running){
            currentTime = System.nanoTime ();
            deltaTime += (currentTime - lastLoopTime) / OPTIMAL_TIME;
            lastLoopTime = currentTime;

            while (deltaTime >= 1) {
                update();
                deltaTime--;
            }

            render();

            if (System.currentTimeMillis () - secondTimer > 1000) {
                secondTimer += 1000;
                System.out.print (FPS+"\n");
                FPS = 0;
            }
        }
    }

    static BufferedImage view = new BufferedImage(64*6,64*6, BufferedImage.TYPE_4BYTE_ABGR);

    static void render() {
        if ( bs == null) {
            display.getCanvas().createBufferStrategy(4);
            bs = display.getCanvas().getBufferStrategy();
        }

        Graphics g = view.createGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0, view.getWidth(), view.getHeight());
        g.setColor(Color.WHITE);
        renderer.render(g);
        g.dispose();

        Graphics g1 = display.getCanvas().getBufferStrategy().getDrawGraphics();

        g1.setColor(Color.BLACK);
        g1.fillRect(0,0, display.getCanvas().getWidth(), display.getCanvas().getHeight());
        g1.setColor(Color.WHITE);

        ImageUtils.drawScaledImage(ImageUtils.resize(view, display.getCanvas().getWidth(), display.getCanvas().getHeight()), display.getCanvas(), g1);

        bs.show();
        FPS++;
    }

    static void update() {
        if (!(renderer.camera.getX() >  map[0].length - 5)) {
            renderer.camera.update(.75f, 0);
        } else {
            renderer.camera.moveTo(0, renderer.camera.getY());
        }
        if (!(renderer.camera.getY() >  map.length - 5)) {
            renderer.camera.update(0,  (1f / (float) (OPTIMAL_TICKS*4)));
        } else {
            renderer.camera.moveTo(renderer.camera.getX(), 0);
        }
    }
}
