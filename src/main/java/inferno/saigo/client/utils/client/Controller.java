package inferno.saigo.client.utils.client;

import inferno.saigo.client.utils.display.DisplayReference;
import inferno.saigo.common.configuration.Settings;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Controller implements KeyListener {
    public HashMap<Integer, Key> keyBindings = new HashMap<>();
    public static boolean[] other = new boolean[256];

    public void init() {
        bind(Integer.parseInt(Settings.getProperty("key_binds.forward")),    DisplayReference.UP);
        bind(Integer.parseInt(Settings.getProperty("key_binds.backward")), DisplayReference.DOWN);
        bind(Integer.parseInt(Settings.getProperty("key_binds.left")),     DisplayReference.LEFT);
        bind(Integer.parseInt(Settings.getProperty("key_binds.right")),   DisplayReference.RIGHT);
        bind(Integer.parseInt(Settings.getProperty("key_binds.use")),       DisplayReference.USE);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        other[e.getExtendedKeyCode()] = true;
        keyBindings.get(e.getKeyCode()).isDown = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        other[e.getExtendedKeyCode()] = false;
        keyBindings.get(e.getKeyCode()).isDown = false;
    }

    public boolean isKeyBinded(int extendedKey){
        return keyBindings.containsKey(extendedKey);
    }

    @Override
    public void keyTyped(KeyEvent e) {}


    public void bind(Integer keyCode, Key key){
        keyBindings.put(keyCode, key);
    }

    public void releaseAll(){
        for(Key key : keyBindings.values()){
            key.isDown = false;
        }
    }
}
