package inferno.saigo.client.utils.client;

import inferno.saigo.client.utils.display.DisplayReference;
import inferno.saigo.common.configuration.Settings;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class KeyController implements KeyListener {
    public HashMap<Integer, Key> keyBindings = new HashMap<>();
    public static boolean[] other = new boolean[256];
    private final Key dummy = new Key();

    public void init() {
        bind(Integer.parseInt(Settings.getProperty("key_binds.forward")),                          DisplayReference.UP);
        bind(Integer.parseInt(Settings.getProperty("key_binds.backward")),                       DisplayReference.DOWN);
        bind(Integer.parseInt(Settings.getProperty("key_binds.left")),                           DisplayReference.LEFT);
        bind(Integer.parseInt(Settings.getProperty("key_binds.right")),                         DisplayReference.RIGHT);
        bind(Integer.parseInt(Settings.getProperty("key_binds.use")),                             DisplayReference.USE);
        bind(Integer.parseInt(Settings.getProperty("key_binds.pause")),                         DisplayReference.PAUSE);
        bind(Integer.parseInt(Settings.getProperty("key_binds.debug")),                         DisplayReference.DEBUG);
        bind(Integer.parseInt(Settings.getProperty("key_binds.screenshot")),               DisplayReference.SCREENSHOT);
        bind(Integer.parseInt(Settings.getProperty("key_binds.map")),                             DisplayReference.MAP);
        bind(Integer.parseInt(Settings.getProperty("key_binds.speech")),                       DisplayReference.SPEECH);
        bind(Integer.parseInt(Settings.getProperty("key_binds.inventory")),                 DisplayReference.INVENTORY);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        other[e.getExtendedKeyCode()] = true;
        keyBindings.getOrDefault(e.getKeyCode(), dummy).isDown = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        other[e.getExtendedKeyCode()] = false;
        keyBindings.getOrDefault(e.getKeyCode(), dummy).isDown = false;
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
