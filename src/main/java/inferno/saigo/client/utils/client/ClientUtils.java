package inferno.saigo.client.utils.client;


import inferno.saigo.client.utils.display.DisplayReference;

public class ClientUtils {
    public static void update(double delta_dime) {
        if (DisplayReference.UP.isDown){
            DisplayReference.renderer.camera.update(0, -1f / (float) (DisplayReference.OPTIMAL_TICKS/2));
        }
        if (DisplayReference.DOWN.isDown){
            DisplayReference.renderer.camera.update(0, 1f / (float) (DisplayReference.OPTIMAL_TICKS/2));
        }
        if (DisplayReference.LEFT.isDown){
            DisplayReference.renderer.camera.update(-1f / (float) (DisplayReference.OPTIMAL_TICKS/2), 0);
        }
        if (DisplayReference.RIGHT.isDown){
            DisplayReference.renderer.camera.update(1f / (float) (DisplayReference.OPTIMAL_TICKS/2), 0);
        }
    }
}
