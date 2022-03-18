package inferno.saigo.client.utils.client;


import inferno.saigo.client.assets.Sounds;
import inferno.saigo.client.utils.display.DisplayReference;
import inferno.saigo.common.configuration.Settings;
import inferno.saigo.common.init.Items;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ClientUtils {
    public static boolean debugReady = true, mouse_ready = true, mouse2_ready = true;
    static float speed = 1f / (float) (DisplayReference.OPTIMAL_TICKS/2), movementTimeHeld = 0f;
    static int lastMouseX, lastMouseY;
    static double lastMouseRotation;

    public static void update(double delta_dime) {

        DisplayReference.renderer.camera.update(
                (DisplayReference.LEFT.isDown ? -speed : 0f) + (DisplayReference.RIGHT.isDown ? speed : 0f),
                (DisplayReference.UP.isDown ? -speed : 0f) + (DisplayReference.DOWN.isDown ? speed : 0f));

        if (!Boolean.parseBoolean(Settings.getProperty("tutorial"))) {
            if (DisplayReference.UP.isDown || DisplayReference.DOWN.isDown || DisplayReference.LEFT.isDown || DisplayReference.RIGHT.isDown)
                movementTimeHeld += speed / 10f;
            if (DisplayReference.mouse_controller.mouseRotationFromCenter != lastMouseRotation && !DisplayReference.controlsOverlay.shouldRender)
                movementTimeHeld += speed / 10f;

            if (movementTimeHeld > 1f && !(DisplayReference.controlsOverlay.shouldRender == DisplayReference.mouseOverlay.shouldRender)) {
                DisplayReference.mouseOverlay.shouldRender = !DisplayReference.mouseOverlay.shouldRender;
                DisplayReference.controlsOverlay.shouldRender = false;
                movementTimeHeld = 0f;
            }

            if ((DisplayReference.controlsOverlay.shouldRender == DisplayReference.mouseOverlay.shouldRender)) {
                Settings.properties.setProperty("tutorial", String.valueOf(true));
                Settings.saveSettings();
            }
        }


        if (DisplayReference.PAUSE.isDown){
            DisplayReference.running = false;
            DisplayReference.renderer.renderEnabled = false;
            DisplayReference.display.dispose();
        }

        if (DisplayReference.USE.isDown){
            DisplayReference.playerHand.setTexture(Items.PICKAXE);
        }

        if (DisplayReference.DEBUG.isDown && debugReady){
            DisplayReference.locationOverlay.shouldRender = ! DisplayReference.locationOverlay.shouldRender;
            DisplayReference.fpsOverlay.shouldRender = ! DisplayReference.fpsOverlay.shouldRender;
            debugReady = false;
        }

        if (!DisplayReference.DEBUG.isDown && !debugReady){
            debugReady = true;
        }

        DisplayReference.locationOverlay.setText("pos : x= " + format(DisplayReference.renderer.camera.getX()) + ", y= " + format(DisplayReference.renderer.camera.getY()));

        if (DisplayReference.mouse_controller.isMouseLeftDown() && mouse_ready){
            Sounds.playSound(Sounds.RAIN_DROP);
            mouse_ready = false;
        }

        if (!DisplayReference.mouse_controller.isMouseLeftDown() && !mouse_ready){
            mouse_ready = true;
        }

        if (DisplayReference.mouse_controller.isRightMouseDown() && mouse2_ready){
            Sounds.playSound(Sounds.BLOCK_BREAK);
            mouse2_ready = false;
        }

        if (!DisplayReference.mouse_controller.isRightMouseDown() && !mouse2_ready){
            mouse2_ready = true;
        }

        lastMouseX = DisplayReference.mouse_controller.currentX;
        lastMouseY = DisplayReference.mouse_controller.currentY;
        lastMouseRotation = DisplayReference.mouse_controller.mouseRotationFromCenter;
    }

    static DecimalFormat df = new DecimalFormat("#.###");

    public static String format(float num){
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(num);
    }

    public static float getMovementProgress() {
        return movementTimeHeld;
    }
}
