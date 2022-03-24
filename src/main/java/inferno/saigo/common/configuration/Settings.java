package inferno.saigo.common.configuration;

import java.io.*;
import java.util.Properties;

public class Settings {
    public static final Properties properties = new Properties();
    public static final String name = "Saigo";
    public static final String version = "1.0.001-Test";

    public static void default_properties(){
    }

    public static String getProperty(String key){
        return properties.getProperty(key, null);
    }

    public static void loadSettings(){
        File settings = new File(System.getProperty("user.home")+File.separator+name+File.separator+"settings.properties");
        if (settings.exists()){
            try {
                InputStream input = new FileInputStream(System.getProperty("user.home")+File.separator+name+File.separator+"settings.properties");
                properties.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            default_properties();
        }
    }

    public static void saveSettings(){
        File settings = new File(System.getProperty("user.home")+File.separator+name+File.separator+"settings.properties");

        try {
            FileOutputStream fr = new FileOutputStream(settings);
            properties.store(fr, "Settings "+name+"-"+version);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getHomeDir() {
        return new File(System.getProperty("user.home")+File.separator+name);
    }

    public static File getPluginDir() {
        return new File(System.getProperty("user.home")+File.separator+name+File.separator+"mods");
    }
}

