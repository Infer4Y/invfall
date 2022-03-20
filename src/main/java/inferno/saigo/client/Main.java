package inferno.saigo.client;

import inferno.saigo.client.utils.GameSetup;

public class Main implements Runnable{

    public static final Main INSTANCE = new Main();

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        INSTANCE.run();
    }

    @Override
    public void run() {
        GameSetup.preInitialization();
        GameSetup.initialization();
        GameSetup.postInitialization();
    }
}
