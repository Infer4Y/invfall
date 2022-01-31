package inferno.saigo.client;

import inferno.saigo.client.assets.Textures;
import inferno.saigo.client.rendering.ObjectRenderingText;
import inferno.saigo.client.rendering.ObjectRenderingTile;
import inferno.saigo.client.rendering.Renderer;
import inferno.saigo.client.threading.CommonThread;
import inferno.saigo.client.threading.DisplayThread;
import inferno.saigo.client.utils.display.DisplayReference;
import inferno.saigo.common.init.Tiles;

import java.awt.*;
import java.io.IOException;

public class Main implements Runnable{
    public static int[][] map = new int[200][200];

    public static void main(String[] args) {
        new Main().run();
    }

    @Override
    public void run() {
        DisplayReference.display = new Display("Test");
        DisplayReference.renderer = new Renderer();

        DisplayReference.view.setAccelerationPriority(1);

        DisplayReference.renderer.gridSpaceX = map[0].length-1;
        DisplayReference.renderer.tileSize = DisplayReference.view.getWidth()/6;

        DisplayReference.view = DisplayReference.defaultConfiguration.createCompatibleImage(
                DisplayReference.view.getWidth(),
                DisplayReference.view.getHeight(),
                Transparency.TRANSLUCENT);

        try {
            Textures.registerTexture(Tiles.TILE);
        } catch (IOException e) {
            e.printStackTrace();
        }


        int tile_id = 0;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                //DisplayReference.renderer.add(0, new ObjectRenderingText(String.valueOf(tile_id),x,y));
                DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.TILE,x,y));
                tile_id++;
            }
        }
        new DisplayThread(this, "Display0").start();
        new CommonThread(this, "Common0").start();
    }
}
