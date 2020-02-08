package inferno.saigo.client;

import inferno.saigo.client.rendering.Renderable;
import inferno.saigo.client.rendering.RenderableTile;
import inferno.saigo.client.rendering.Renderer;
import inferno.saigo.common.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class Main {
    static boolean running = true;
    static Display display;
    static Renderer renderer;

    static final long NANOSECOND        = 1000000000;
    static final double OPTIMAL_TICKS   = 30.0;
    static final double OPTIMAL_TIME    = NANOSECOND / OPTIMAL_TICKS;

    static long lastLoopTime = System.nanoTime();
    static long currentTime;
    static double deltaTime;
    static long secondTimer = System.currentTimeMillis();

    static HashMap<Integer, Renderable> renderableHashMap = new HashMap<>();
    static HashMap<Integer, Renderable> renderableHashMap1 = new HashMap<>();
    static BufferStrategy bs = null;

    static int map[][] = new int[30][70];

    public static void main(String[] args) throws IOException {
        display = new Display("Test");
        renderer = new Renderer();

        renderer.gridspaceX = map[0].length;

        renderableHashMap.put(0, new RenderableTile(new Tile("wall")));
        renderableHashMap.put(1, new RenderableTile(new Tile("tile")));
        renderableHashMap.put(2, new RenderableTile(new Tile("brick")));
        renderableHashMap.put(3, new RenderableTile(new Tile("black_tile")));
        renderableHashMap.put(4, new RenderableTile(new Tile("diag_brick")));

        renderableHashMap1.put(0, new RenderableTile(new Tile("ruin")));
        renderableHashMap1.put(1, new RenderableTile(new Tile("ruin1")));
        renderableHashMap1.put(2, new RenderableTile(new Tile("ruin2")));
        renderableHashMap1.put(3, new RenderableTile(new Tile("ruin3")));
        renderableHashMap1.put(4, new RenderableTile(new Tile("ruin4")));

        for (int[] ints : map) { // y
            for (int anInt : ints) { // x
                renderer.add(0, renderableHashMap.get(anInt));
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
            }

        }

    }

    static void render() {
        if ( bs == null) {
            display.getCanvas().createBufferStrategy(4);
            bs = display.getCanvas().getBufferStrategy();
        }

        Graphics g = display.getCanvas().getBufferStrategy().getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0, display.getWidth(), display.getHeight());
        g.setColor(Color.WHITE);
        renderer.render(g);
        g.dispose();
        bs.show();
    }

    static void update() throws IOException {
        renderer.clear();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                renderer.add(0, renderableHashMap.get(map[y][x]));

                map[y][x] = map[y][x] + new Random().nextInt(1);
                if (map[y][x] == 5) map[y][x] = 0;
            }
        }

        renderer.camera.update(.01f,0.01f);

        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 6; x++) {
                //renderer.add(1, renderableHashMap1.get(map[y][x]));
            }
        }
    }
}
