package inferno.saigo.common.maps;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import inferno.saigo.common.configuration.Settings;
import inferno.saigo.common.tiles.Tile;

import java.io.*;

public class MapSave {
    public static JsonWriter writer;
    public static JsonReader reader;

    public static void saveMap(String name, Tile[][] tile_map) throws IOException {
        File settings = new File(System.getProperty("user.home")+File.separator+Settings.name +File.separator+name+".json");

        writer = new JsonWriter(new FileWriter(settings));


    }

    public static Tile[][] loadMap(String name) throws FileNotFoundException {
        File settings = new File(System.getProperty("user.home")+File.separator+Settings.name +File.separator+name+".json");

        reader = new JsonReader(new FileReader(settings));

        return null;
    }
}
