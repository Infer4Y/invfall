package inferno.saigo.client.utils;

import inferno.saigo.api.plugins.Mod;
import inferno.saigo.client.Main;
import inferno.saigo.client.assets.collections.Fonts;
import inferno.saigo.client.assets.loaders.PluginLoader;
import inferno.saigo.client.assets.objects.ResourceLocation;
import inferno.saigo.client.assets.collections.Sounds;
import inferno.saigo.client.assets.collections.Textures;
import inferno.saigo.client.configuration.ClientSettings;
import inferno.saigo.client.rendering.*;
import inferno.saigo.client.threading.CommonThread;
import inferno.saigo.client.threading.DisplayThread;
import inferno.saigo.client.threading.WorldThread;
import inferno.saigo.client.utils.client.KeyController;
import inferno.saigo.client.utils.client.MouseController;
import inferno.saigo.client.utils.display.Display;
import inferno.saigo.client.utils.display.DisplayReference;
import inferno.saigo.common.configuration.Settings;
import inferno.saigo.common.init.Entities;
import inferno.saigo.common.init.Items;
import inferno.saigo.common.init.Recipes;
import inferno.saigo.common.init.Tiles;
import inferno.saigo.common.maps.MapSave;
import inferno.saigo.common.maps.MapWorld;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameSetup {
    public static void preInitialization(){
        //Settings
        ClientSettings.loadSettings();
        DisplayReference.pluginLoader = new PluginLoader(Settings.getPluginDir());

        DisplayReference.pluginLoader.loadPlugins();

        DisplayReference.pluginLoader.getPluginFactoriesMap().forEach((s, pluginFactory) -> DisplayReference.modArrayList.add(pluginFactory.build()));

        // Image buffer setup
        DisplayReference.view = new BufferedImage((int) (Integer.parseInt(Settings.getProperty("width"))  * DisplayReference.viewScale), (int) (Integer.parseInt(Settings.getProperty("height")) * DisplayReference.viewScale), BufferedImage.TYPE_4BYTE_ABGR);
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
        DisplayReference.display.getCanvas().addMouseMotionListener(DisplayReference.mouse_controller);
        DisplayReference.display.getCanvas().addMouseWheelListener(DisplayReference.mouse_controller);
        DisplayReference.display.getCanvas().requestFocus();

        //Renderer setup
        DisplayReference.renderer = new Renderer();
        DisplayReference.renderer.tileSize = DisplayReference.view.getWidth()/10;

        DisplayReference.renderer.add(2, DisplayReference.fpsOverlay = new ObjectRenderingText("fps : 0", 8,28));
        DisplayReference.renderer.add(2, DisplayReference.locationOverlay = new ObjectRenderingText("pos : 0, 0", 8,70));

        if (!Boolean.parseBoolean(Settings.getProperty("tutorial"))) {
            DisplayReference.renderer.add(2, DisplayReference.controlsOverlay = new ObjectRenderingMovementInfo(8, 64 * 2, "Press W, A, S, or D to move your character up, down, left, or right.", "W=Up,", "A=Left,", "S=Down,", "D=Right."));
            DisplayReference.renderer.add(2, DisplayReference.mouseOverlay = new ObjectRenderingMovementInfo(8, 64 * 2, "Moving your mouse will move your character to face it.", "Give it a spin.", "Look around."));

            DisplayReference.controlsOverlay.shouldRender = true;
        }

        DisplayReference.modArrayList.forEach(Mod::preInit);

        // Thread setup
        new DisplayThread(Main.INSTANCE, "Display0").start();
        new CommonThread(Main.INSTANCE, "Common0").start();
        new WorldThread(Main.INSTANCE, "Common0-World").start();
    }

    public static void initialization(){
        DisplayReference.world = new MapWorld();

        Items.init();
        Tiles.init();
        Entities.init();
        Sounds.init();
        Recipes.init();
        Textures.init();
        Fonts.init();

        DisplayReference.world.setMap(MapSave.loadMapJar(new ResourceLocation("maps/test_map_1.json")));

        DisplayReference.world.getMap().tiles.forEach(tile -> DisplayReference.renderer.add(0, new ObjectRenderingTile(tile.tile,tile.x, tile.y)));
        DisplayReference.world.getEntities().values().forEach(entityData -> DisplayReference.renderer.add(1, new ObjectRenderingEntity(entityData)));

        DisplayReference.renderer.add(2, DisplayReference.player = new ObjectRenderingPlayer(Textures.getTexture("crosshair")));
        DisplayReference.renderer.add(2, DisplayReference.playerHand = new ObjectRenderingItemForPlayer(Textures.getTexture("saigo:items.ray_blaster")));

        DisplayReference.renderer.renderEnabled = true;

        Sounds.playSound(Sounds.SONG_THREE);

        DisplayReference.display.setCursor(DisplayReference.display.getToolkit().createCustomCursor(Textures.getTexture("cursor").getImage(), new Point(0, 0), "null"));

        DisplayReference.modArrayList.forEach(Mod::init);
    }

    public static void postInitialization(){
        DisplayReference.modArrayList.forEach(Mod::postInit);
    }
}
