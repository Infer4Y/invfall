package inferno.saigo.client.threading;

import inferno.saigo.client.utils.display.DisplayReference;
import inferno.saigo.client.utils.display.DisplayUtils;

public class DisplayThread extends Thread{
    public DisplayThread(Runnable runnable, String name) {
        super(runnable, name);
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted() && DisplayReference.running) {
            DisplayUtils.render();

            DisplayReference.key_controller.init();

            if (System.currentTimeMillis() - DisplayReference.second_timer > 1000) {
                DisplayReference.second_timer += 1000;

                if ( DisplayReference.fpsOverlay != null) {
                    DisplayReference.fpsOverlay.setText("fps : " + DisplayReference.FPS);
                }

                System.out.print(DisplayReference.FPS + "\n");
                DisplayReference.FPS = 0;
            }
        }
    }
}
