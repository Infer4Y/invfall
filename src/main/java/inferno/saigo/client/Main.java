package inferno.saigo.client;

import inferno.saigo.client.utils.GameSetup;

public class Main implements Runnable{
    public static int[][] map = new int[200][200];

    public static final Main INSTANCE = new Main();

    public static void main(String[] args) {
        INSTANCE.run();
    }

    @Override
    public void run() {
        GameSetup.preinit();
    }
}
