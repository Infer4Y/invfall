import inferno.saigo.client.assets.ResourceLocation;
import inferno.saigo.common.init.Tiles;
import inferno.saigo.common.maps.Map;
import inferno.saigo.common.maps.MapSave;
import inferno.saigo.common.tiles.TileData;

import java.util.ArrayList;
import java.util.Arrays;

public class MapTestLoading {
    public static void main(String... args){
        Map map = new Map();
        map.width = map.height = 100;
        map.tiles = new ArrayList<>();
        for (int i = -map.height/2; i < map.height/2; i++) {
            for (int j = -map.height/2; j < map.width/2; j++) {
                TileData t = new TileData();
                t.x = i;
                t.y = j;
                t.tile = (i % 8 == 0) ? Tiles.TILE : (j % 7 == 0) ? Tiles.DIRT : Tiles.PLANKS;
                map.tiles.add(t);
            }
        }

        MapSave.saveMap(new ResourceLocation("maps/test_map.json"), map);

        map = MapSave.loadMap(new ResourceLocation("maps/test_map.json"));

        map.tiles.forEach(System.out::println);
    }
}
