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
    static final double OPTIMAL_TICKS   = 50.0;
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

        renderer.gridspaceX = map[0].length-1;

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


        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                renderer.add(0, new RenderableText(x+","+y));;
            }
        }

        /*for (int[] ints : map) { // y
            for (int anInt : ints) { // x
                renderer.add(0, renderableHashMap.get(anInt));
            }
        }*/

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

        BufferedImage view = new BufferedImage(400,400, BufferedImage.TYPE_4BYTE_ABGR);

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
    }

    static void update() {
        renderer.clear();
        /*for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                renderer.add(0, renderableHashMap.get(map[y][x]));

                map[y][x] = map[y][x] + new Random().nextInt(1);
                if (map[y][x] == 5) map[y][x] = 0;
            }
        }*/

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                renderer.add(0, new RenderableText(x+","+y));;
            }
        }

        if (!(renderer.camera.getX() >  map[0].length - 5)) {

            renderer.camera.update(0.025f, 0);
        }
        if (!(renderer.camera.getY() >  map.length - 5)) {
            renderer.camera.update(0, 0.025f);
        }
    }
}
