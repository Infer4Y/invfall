package inferno.saigo.client.utils.display;

import inferno.saigo.client.rendering.*;
import inferno.saigo.client.utils.client.KeyController;
import inferno.saigo.client.utils.client.Key;
import inferno.saigo.client.utils.client.MouseController;
import inferno.saigo.common.maps.MapWorld;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class DisplayReference {
    public static boolean running = true;
    public static Display display;
    public static KeyController key_controller;
    public static MouseController mouse_controller;
    public static Renderer renderer;

    public static final long NANOSECOND        = 1000000000;
    public static final double OPTIMAL_TICKS   = 90.0;
    public static final double OPTIMAL_TIME    = NANOSECOND / OPTIMAL_TICKS;

    public static int FPS;
    public static ObjectRenderingText fpsOverlay;

    public static ObjectRenderingText locationOverlay;

    public static ObjectRenderingMovementInfo controlsOverlay;
    public static ObjectRenderingMovementInfo mouseOverlay;

    public static ObjectRenderingPlayer player;
    public static ObjectRenderingItemForPlayer playerHand;

    public static MapWorld world;

    public static long last_loop_time = System.nanoTime();
    public static long current_time;
    public static double delta_time;
    public static long second_timer = System.currentTimeMillis();

    public static BufferStrategy buffer_strategy = null;

    public static BufferedImage view;
    public static int viewScale = 2;

    public static GraphicsEnvironment localGraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
    public static GraphicsConfiguration defaultConfiguration = localGraphicsEnvironment.getDefaultScreenDevice().getDefaultConfiguration();

    public static Key UP = new Key();
    public static Key LEFT = new Key();
    public static Key DOWN = new Key();
    public static Key RIGHT = new Key();
    public static Key USE = new Key();
    public static Key PAUSE = new Key();
    public static Key DEBUG = new Key();
    public static Key MAP = new Key();
    public static Key SPEECH = new Key();
    public static Key INVENTORY = new Key();
}
