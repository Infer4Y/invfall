package inferno.saigo.client.utils.display;

import inferno.saigo.client.Display;
import inferno.saigo.client.rendering.Renderer;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class DisplayReference {
    public static boolean running = true;
    public static Display display;
    public static Renderer renderer;

    public static final long NANOSECOND        = 1000000000;
    public static final double OPTIMAL_TICKS   = 90.0;
    public static final double OPTIMAL_TIME    = NANOSECOND / OPTIMAL_TICKS;

    public static int FPS;

    public static long last_loop_time = System.nanoTime();
    public static long current_time;
    public static double delta_dime;
    public static long second_timer = System.currentTimeMillis();

    public static BufferStrategy buffer_strategy = null;

    public static BufferedImage view = new BufferedImage(64*12,64*12, BufferedImage.TYPE_4BYTE_ABGR);

    public static GraphicsEnvironment localGraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
    public static GraphicsConfiguration defaultConfiguration = localGraphicsEnvironment.getDefaultScreenDevice().getDefaultConfiguration();
}
