package inferno.saigo.client.threading;

import inferno.saigo.client.Main;
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

            DisplayReference.controller.init();

            if (System.currentTimeMillis() - DisplayReference.second_timer > 1000) {
                DisplayReference.second_timer += 1000;
                System.out.print(DisplayReference.FPS + "\n");
                DisplayReference.FPS = 0;
            }
        }
    }
}
