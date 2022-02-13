package inferno.saigo.client.utils;

import inferno.saigo.client.Main;
import inferno.saigo.client.assets.Textures;
import inferno.saigo.client.configuration.ClientSettings;
import inferno.saigo.client.rendering.ObjectRenderingTile;
import inferno.saigo.client.rendering.Renderer;
import inferno.saigo.client.threading.CommonThread;
import inferno.saigo.client.threading.DisplayThread;
import inferno.saigo.client.utils.display.Display;
import inferno.saigo.client.utils.display.DisplayReference;
import inferno.saigo.common.configuration.Settings;
import inferno.saigo.common.init.Items;
import inferno.saigo.common.init.Recipes;
import inferno.saigo.common.init.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameSetup {
    public static void preInitialization(){
        //Settings
        ClientSettings.loadSettings();

        // Image buffer setup
        DisplayReference.view = new BufferedImage(Integer.parseInt(Settings.getProperty("width"))  * 2, Integer.parseInt(Settings.getProperty("height")) * 2, BufferedImage.TYPE_4BYTE_ABGR);
        DisplayReference.view = DisplayReference.defaultConfiguration.createCompatibleImage(
                DisplayReference.view.getWidth(),
                DisplayReference.view.getHeight(),
                Transparency.TRANSLUCENT);
        DisplayReference.view.setAccelerationPriority(1);

        // Window setup
        DisplayReference.display = new Display("Test", Integer.parseInt(Settings.getProperty("width")), Integer.parseInt(Settings.getProperty("height")));

        //Renderer setup
        DisplayReference.renderer = new Renderer();
        DisplayReference.renderer.tileSize = DisplayReference.view.getWidth()/6;

        // Thread setup
        new DisplayThread(Main.INSTANCE, "Display0").start();
        new CommonThread(Main.INSTANCE, "Common0").start();
    }

    public static void initialization(){
        Items.init();
        Tiles.init();
        Recipes.init();
        Textures.init();

        DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.BLACK_TILE, 0,0));
    }
}
