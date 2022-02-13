package inferno.saigo.client.assets;

import com.google.gson.Gson;
import inferno.saigo.client.Main;
import inferno.saigo.common.items.Item;
import inferno.saigo.common.tiles.Tile;

import java.io.*;
import java.util.Objects;

public class ResourceLoader {
    private static final Gson parser = new Gson();

    public static ResourceLocation item_resource_location;
    //public static ResourceLocation sound_resource_location;
    public static ResourceLocation tile_resource_location;

    public static Model item_model = new Model();
    public static Model tile_model = new Model();

    public static ResourceLocation receiveImagePath(Item item){
        item_resource_location = new ResourceLocation(item.getDomain(), "models/items/" + item.getName() + ".json");
        try {
            Reader item_reader = new InputStreamReader(Objects.requireNonNull(Main.class.getResourceAsStream(item_resource_location.toString())));
            item_model = parser.fromJson(item_reader, Model.class);
        } catch (NullPointerException ignored){}
        item_resource_location = new ResourceLocation(item_model.domain, "textures/" + item_model.path);

        return item_resource_location;
    }

    public static ResourceLocation receiveImagePath(Tile tile){
        tile_resource_location = new ResourceLocation(tile.getDomain(), "models/tiles/" + tile.getName() + ".json");

        try {
            Reader tile_reader = new InputStreamReader(Objects.requireNonNull(Main.class.getResourceAsStream(tile_resource_location.toString())));
            tile_model = parser.fromJson(tile_reader, Model.class);
        } catch (NullPointerException e){
            System.out.println(tile_resource_location.toString()+" could not be loaded or is null");
        }

        tile_resource_location = new ResourceLocation(tile_model.domain, "textures/" + tile_model.path);

        return tile_resource_location;
    }
}
