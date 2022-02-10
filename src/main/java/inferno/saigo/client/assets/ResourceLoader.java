package inferno.saigo.client.assets;

import inferno.saigo.common.items.Item;
import inferno.saigo.common.tiles.Tile;

import java.util.Properties;

public class ResourceLoader {
    public static final Properties item_properties = new Properties();
    public static final Properties sound_properties = new Properties();
    public static final Properties tile_properties = new Properties();

    public static ResourceLocation item_resource_location;
    public static ResourceLocation sound_resource_location;
    public static ResourceLocation tile_resource_location;


    public static ResourceLocation receiveImagePath(Item item){
        item_resource_location = new ResourceLocation(item.getDomain(), "items/" + item.getName() + ".json");

        return item_resource_location;
    }

    public static ResourceLocation receiveImagePath(Tile tile){
        tile_resource_location = new ResourceLocation(tile.getDomain(), "items/" + tile.getName() + ".json");

        return tile_resource_location;
    }
}
