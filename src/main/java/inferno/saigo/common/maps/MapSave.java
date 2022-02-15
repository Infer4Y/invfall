package inferno.saigo.common.maps;

import com.google.gson.Gson;
import inferno.saigo.client.Main;
import inferno.saigo.client.assets.ResourceLocation;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

public class MapSave {
    private static final Gson parser = new Gson();

    public static void saveMap(ResourceLocation location, Map map) {
    }

    public static Map loadMap(ResourceLocation location) {
        Map temp_map;
        try {
            Reader item_reader = new InputStreamReader(Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream(location.toString())));
            temp_map = parser.fromJson(item_reader, Map.class);
        } catch (NullPointerException e){
            System.out.println(location +" could not be loaded or is null");
            return null;
        }

        return temp_map;
    }
}
