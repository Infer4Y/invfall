package inferno.saigo.common.maps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import inferno.saigo.client.Main;
import inferno.saigo.client.assets.objects.ResourceLocation;
import inferno.saigo.common.configuration.Settings;

import java.io.*;
import java.util.Objects;

public class MapSave {
    private static final Gson parser = new Gson();
    private static final File maps_dir = new File(System.getProperty("user.home") + File.separator + Settings.name + File.separator);

    public static void saveMap(ResourceLocation location, Map map) {

        boolean mkdirs = new File((maps_dir.getAbsolutePath() + File.separator + location.toString())).getParentFile().mkdirs();

        try (Writer writer = new FileWriter(maps_dir.getAbsolutePath() + File.separator + location)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(map, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveMapJar(ResourceLocation location, Map map) {

        try (Writer writer = new OutputStreamWriter(Objects.requireNonNull(Main.class.getClassLoader().getResource(location.toString())).openConnection().getOutputStream())) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(map, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map loadMap(ResourceLocation location) {

        Map temp_map = null;

        boolean mkdirs = new File((maps_dir.getAbsolutePath() + File.separator + location.toString())).getParentFile().mkdirs();

        if (mkdirs) temp_map = new Map();

        try (Reader reader = new FileReader((maps_dir.getAbsolutePath() + File.separator + location))) {

            // Convert JSON File to Java Object
            temp_map = parser.fromJson(reader, Map.class);

            // print staff object
            return temp_map;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return temp_map;
    }

    public static Map loadMapJar(ResourceLocation location) {
        Map temp_map;

        try (Reader reader = new InputStreamReader(Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream(location.toString())))) {

            // Convert JSON File to Java Object
            temp_map = parser.fromJson(reader, Map.class);

            // print staff object
            return temp_map;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
