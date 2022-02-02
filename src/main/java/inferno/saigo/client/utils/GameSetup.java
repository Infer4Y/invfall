package inferno.saigo.client.utils;

import inferno.saigo.client.Main;
import inferno.saigo.client.configuration.ClientSettings;
import inferno.saigo.client.rendering.ObjectRenderingTile;
import inferno.saigo.client.rendering.Renderer;
import inferno.saigo.client.threading.CommonThread;
import inferno.saigo.client.threading.DisplayThread;
import inferno.saigo.client.utils.display.Display;
import inferno.saigo.client.utils.display.DisplayReference;
import inferno.saigo.common.configuration.Settings;
import inferno.saigo.common.init.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameSetup {
    public static void preinit(){
        ClientSettings.loadSettings();

        DisplayReference.view = new BufferedImage(Integer.parseInt(Settings.getProperty("width"))  * 2, Integer.parseInt(Settings.getProperty("height")) * 2, BufferedImage.TYPE_4BYTE_ABGR);

        DisplayReference.display = new Display("Test", Integer.parseInt(Settings.getProperty("width")), Integer.parseInt(Settings.getProperty("height")));

        DisplayReference.renderer = new Renderer();

        DisplayReference.view.setAccelerationPriority(1);

        DisplayReference.renderer.gridSpaceX = Main.map[0].length-1;
        DisplayReference.renderer.tileSize = DisplayReference.view.getWidth()/6;

        DisplayReference.view = DisplayReference.defaultConfiguration.createCompatibleImage(
                DisplayReference.view.getWidth(),
                DisplayReference.view.getHeight(),
                Transparency.TRANSLUCENT);

        int tile_id = 0;
        for (int y = 0; y < Main.map.length; y++) {
            for (int x = 0; x < Main.map[y].length; x++) {
                if (tile_id % 3 == 0){
                    DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.TILE,x,y));
                } else if (tile_id % 5 == 0){
                    DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.BRICK,x,y));
                } else if (tile_id % 7 == 0){
                    DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.WALL,x,y));
                }else {
                    DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.BLACK_TILE,x,y));
                }
                tile_id++;
            }
        }
        new DisplayThread(Main.INSTANCE, "Display0").start();
        new CommonThread(Main.INSTANCE, "Common0").start();
    }
}
