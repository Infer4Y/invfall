package inferno.saigo.client.assets;

import com.google.gson.Gson;
import inferno.saigo.client.Main;
import inferno.saigo.common.items.Item;
import inferno.saigo.common.tiles.Tile;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceLoader {
    private static final Gson parser = new Gson();
    private static Reader reader;

    public static ResourceLocation item_resource_location;
    public static ResourceLocation sound_resource_location;
    public static ResourceLocation tile_resource_location;
    public static Model item_model;
    public static Model tile_model;


    public static ResourceLocation receiveImagePath(Item item){
        item_resource_location = new ResourceLocation(item.getDomain(), "items/" + item.getName() + ".json");

        try {
            reader = Files.newBufferedReader(Paths.get(Main.class.getClassLoader().getResource(item_resource_location.toString()).getPath()));
            item_model = parser.fromJson(reader, Model.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tile_resource_location = new ResourceLocation(item_model.domain, "textures/" + item_model.path);

        return item_resource_location;
    }

    public static ResourceLocation receiveImagePath(Tile tile){
        tile_resource_location = new ResourceLocation(tile.getDomain(), "items/" + tile.getName() + ".json");

        try {
            reader = Files.newBufferedReader(Paths.get(Main.class.getClassLoader().getResource(tile_resource_location.toString()).getPath()));
            tile_model = parser.fromJson(reader, Model.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tile_resource_location = new ResourceLocation(tile_model.domain, "textures/" + tile_model.path);

        return tile_resource_location;
    }
}
