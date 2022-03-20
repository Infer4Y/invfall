package inferno.saigo.client.configuration;

import inferno.saigo.common.configuration.Settings;

import java.awt.event.KeyEvent;

public class ClientSettings {
    public static void default_properties(){
        Settings.properties.setProperty("width",                                    String.valueOf(800));
        Settings.properties.setProperty("height",                                   String.valueOf(600));
        Settings.properties.setProperty("full_screen",                            String.valueOf(false));
        Settings.properties.setProperty("music",                                   String.valueOf(1.0f));
        Settings.properties.setProperty("world",                                   String.valueOf(1.0f));
        Settings.properties.setProperty("speech",                                  String.valueOf(1.0f));
        Settings.properties.setProperty("key_binds.jump",             String.valueOf(KeyEvent.VK_SPACE));
        Settings.properties.setProperty("key_binds.pause",           String.valueOf(KeyEvent.VK_ESCAPE));
        Settings.properties.setProperty("key_binds.debug",               String.valueOf(KeyEvent.VK_F3));
        Settings.properties.setProperty("key_binds.screenshot",          String.valueOf(KeyEvent.VK_F2));
        Settings.properties.setProperty("key_binds.left",                 String.valueOf(KeyEvent.VK_A));
        Settings.properties.setProperty("key_binds.right",                String.valueOf(KeyEvent.VK_D));
        Settings.properties.setProperty("key_binds.forward",              String.valueOf(KeyEvent.VK_W));
        Settings.properties.setProperty("key_binds.backward",             String.valueOf(KeyEvent.VK_S));
        Settings.properties.setProperty("key_binds.use",                  String.valueOf(KeyEvent.VK_E));
        Settings.properties.setProperty("key_binds.map",                  String.valueOf(KeyEvent.VK_M));
        Settings.properties.setProperty("key_binds.speech",               String.valueOf(KeyEvent.VK_V));
        Settings.properties.setProperty("key_binds.inventory",            String.valueOf(KeyEvent.VK_I));
        Settings.properties.setProperty("tutorial",                               String.valueOf(false));
    }

    public static void loadSettings(){
        default_properties();
        Settings.loadSettings();
        Settings.saveSettings();
    }
}

