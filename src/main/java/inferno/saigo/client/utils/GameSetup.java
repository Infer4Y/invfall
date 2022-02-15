package inferno.saigo.client.utils;

import inferno.saigo.client.Main;
import inferno.saigo.client.assets.Fonts;
import inferno.saigo.client.assets.Textures;
import inferno.saigo.client.configuration.ClientSettings;
import inferno.saigo.client.rendering.*;
import inferno.saigo.client.threading.CommonThread;
import inferno.saigo.client.threading.DisplayThread;
import inferno.saigo.client.utils.client.Controller;
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
        DisplayReference.controller = new Controller();
        DisplayReference.display.getCanvas().addKeyListener(DisplayReference.controller);
        DisplayReference.display.getCanvas().requestFocus();

        //Renderer setup
        DisplayReference.renderer = new Renderer();
        DisplayReference.renderer.tileSize = DisplayReference.view.getWidth()/7;

        // Thread setup
        new DisplayThread(Main.INSTANCE, "Display0").start();
        new CommonThread(Main.INSTANCE, "Common0").start();
    }

    public static void initialization(){

        Items.init();
        Tiles.init();
        Recipes.init();
        Textures.init();
        Fonts.init();

        for (int x = -2; x <= 2; x++) {
            for (int y = -2; y <= 2; y++) {
                DisplayReference.renderer.add(0, new ObjectRenderingText("Coord" + x +',' + y, x,y));
            }
        }


        DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.LOG, -1,0));
        DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.BLACK_TILE, 0,0));
        DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.WALL, 1,0));
        DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.PLANKS, 0,-1));
        DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.DIRT, 0,1));
        DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.TILE, -1,-1));
        DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.RUBY_TILE, 1,-1));
        DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.BRICK, 2,-1));
        DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.PLACEHOLDER, -1,1));
        DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.PAINTING_ONE, -2,-2));
        DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.PAINTING_TWO, -1,-2));
        DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.PAINTING_THREE, -0,-2));
        DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.DIAG_BRICK_ZERO, 1,1));
        DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.DIAG_BRICK_ONE, 2,1));
        DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.DIAG_BRICK_TWO, 1,2));
        DisplayReference.renderer.add(0, new ObjectRenderingTile(Tiles.DIAG_BRICK_THREE, 2,2));
        DisplayReference.renderer.add(0, new ObjectRenderingItem(Items.STICK, 0,2));
        DisplayReference.renderer.add(0, new ObjectRenderingItem(Items.RUBY, 1,-2));
        DisplayReference.renderer.add(0, new ObjectRenderingItem(Items.DIAMOND, 2,0));
        DisplayReference.renderer.add(0, new ObjectRenderingItem(Items.INGOT, -2,0));
        DisplayReference.renderer.add(0, new ObjectRenderingItem(Items.COAL, -2,1));
        DisplayReference.renderer.add(0, new ObjectRenderingItem(Items.SHOVEL, -1,2));
        DisplayReference.renderer.add(0, new ObjectRenderingItem(Items.PICKAXE, -2,2));

        DisplayReference.renderer.add(2, new ObjectRenderingTexture(Textures.getTexture("crosshair")));
    }
}
