import inferno.saigo.client.assets.ResourceLocation;
import inferno.saigo.common.maps.Map;
import inferno.saigo.common.maps.MapSave;

public class MapTestLoading {
    public static void main(String... args){
        Map map = MapSave.loadMap(new ResourceLocation("maps/test_map.json"));
    }
}
