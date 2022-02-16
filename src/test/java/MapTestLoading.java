import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import inferno.saigo.client.assets.ResourceLocation;
import inferno.saigo.common.init.Tiles;
import inferno.saigo.common.maps.Map;
import inferno.saigo.common.maps.MapSave;
import inferno.saigo.common.tiles.TileData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

public class MapTestLoading {
    public static void main(String... args){
        Map map = new Map();
        map.width = map.height = 50;
        map.tiles = new TileData[map.height][map.width];
        for (int i = 0; i < map.height; i++) {
            for (int j = 0; j < map.width; j++) {
                TileData t = new TileData();
                t.x = i;
                t.y = j;
                t.tile = Tiles.TILE;
                map.tiles[i][j] = t;
            }
        }

        MapSave.saveMap(new ResourceLocation("maps/test_map.json"), map);

        map = MapSave.loadMap(new ResourceLocation("maps/test_map.json"));

        Arrays.stream(map.tiles).flatMap(Arrays::stream).map(tile -> tile.tile.toString()).forEach(System.out::println);
    }
}
