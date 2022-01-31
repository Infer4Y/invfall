package inferno.saigo.client.utils.client;

import inferno.saigo.client.Main;
import inferno.saigo.client.utils.display.DisplayReference;

public class ClientUtils {
    public static void update() {
        if (!(DisplayReference.renderer.camera.getX() >  Main.map[0].length - 5)) {
            DisplayReference.renderer.camera.update((10f / (float) (DisplayReference.OPTIMAL_TICKS)), 0);
        } else {
            DisplayReference.renderer.camera.moveTo(0, DisplayReference.renderer.camera.getY());
        }
        if (!(DisplayReference.renderer.camera.getY() >  Main.map.length - 5)) {
            DisplayReference.renderer.camera.update(0,  (1f / (float) (DisplayReference.OPTIMAL_TICKS*4)));
        } else {
            DisplayReference.renderer.camera.moveTo(DisplayReference.renderer.camera.getX(), 0);
        }
    }
}
