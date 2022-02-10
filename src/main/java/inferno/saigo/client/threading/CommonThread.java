package inferno.saigo.client.threading;

import inferno.saigo.client.utils.client.ClientUtils;
import inferno.saigo.client.utils.display.DisplayReference;

public class CommonThread extends Thread{
    public CommonThread(Runnable runnable, String name) {
        super(runnable, name);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted() && DisplayReference.running) {
            DisplayReference.current_time = System.nanoTime();
            DisplayReference.delta_time += (DisplayReference.current_time - DisplayReference.last_loop_time) / DisplayReference.OPTIMAL_TIME;
            DisplayReference.last_loop_time = DisplayReference.current_time;

            while (DisplayReference.delta_time >= 1) {
                ClientUtils.update(DisplayReference.delta_time);
                DisplayReference.delta_time--;
            }
        }
    }
}
