package inferno.saigo.client.utils;

import inferno.saigo.client.Main;
import inferno.saigo.client.assets.Fonts;
import inferno.saigo.client.assets.ResourceLocation;
import inferno.saigo.client.assets.Textures;
import inferno.saigo.client.configuration.ClientSettings;
import inferno.saigo.client.rendering.*;
import inferno.saigo.client.threading.CommonThread;
import inferno.saigo.client.threading.DisplayThread;
import inferno.saigo.client.utils.client.KeyController;
import inferno.saigo.client.utils.client.MouseController;
import inferno.saigo.client.utils.display.Display;
import inferno.saigo.client.utils.display.DisplayReference;
import inferno.saigo.common.configuration.Settings;
import inferno.saigo.common.init.Items;
import inferno.saigo.common.init.Recipes;
import inferno.saigo.common.init.Tiles;
import inferno.saigo.common.maps.Map;
import inferno.saigo.common.maps.MapSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

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
        DisplayReference.key_controller = new KeyController();
        DisplayReference.mouse_controller = new MouseController();
        DisplayReference.display.getCanvas().addKeyListener(DisplayReference.key_controller);
        DisplayReference.display.getCanvas().addMouseListener(DisplayReference.mouse_controller);
        DisplayReference.display.getCanvas().requestFocus();

        //Renderer setup
        DisplayReference.renderer = new Renderer();
        DisplayReference.renderer.tileSize = DisplayReference.view.getWidth()/7;

        DisplayReference.renderer.add(2, DisplayReference.fpsOverlay = new ObjectRenderingText("fps : 0", 8,28));
        DisplayReference.renderer.add(2, DisplayReference.locationOverlay = new ObjectRenderingText("pos : 0, 0", 8,52));

        // Thread setup
        new DisplayThread(Main.INSTANCE, "Display0").start();
        new CommonThread(Main.INSTANCE, "Common0").start();
    }

    public static void initialization(){
        Map map;

        Items.init();
        Tiles.init();
        Recipes.init();
        Textures.init();
        Fonts.init();

        map = MapSave.loadMapJar(new ResourceLocation("maps/test_map_1.json"));

        Objects.requireNonNull(map).tiles.forEach(tile -> DisplayReference.renderer.add(0, new ObjectRenderingTile(tile.tile,tile.x, tile.y)));

        DisplayReference.renderer.add(2, new ObjectRenderingTexture(Textures.getTexture("crosshair")));

        DisplayReference.renderer.renderEnabled = true;
    }
}
