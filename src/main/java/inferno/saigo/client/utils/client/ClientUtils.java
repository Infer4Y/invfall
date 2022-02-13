package inferno.saigo.client.utils.client;

import inferno.saigo.client.Main;
import inferno.saigo.client.utils.display.DisplayReference;

public class ClientUtils {
    public static void update(double delta_dime) {
        if (!(DisplayReference.renderer.camera.getX() >  0)) {
            DisplayReference.renderer.camera.update((1f / (float) (DisplayReference.OPTIMAL_TICKS)) * 10, 0);
        } else {
            DisplayReference.renderer.camera.moveTo(0, DisplayReference.renderer.camera.getY());
        }
        if (!(DisplayReference.renderer.camera.getY() >  0)) {
            DisplayReference.renderer.camera.update(0,  (1f / (float) (DisplayReference.OPTIMAL_TICKS*4)) * 10);
        } else {
            DisplayReference.renderer.camera.moveTo(DisplayReference.renderer.camera.getX(), 0);

        }
    }
}
