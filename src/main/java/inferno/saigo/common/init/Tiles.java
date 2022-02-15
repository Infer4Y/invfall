package inferno.saigo.common.init;

import inferno.saigo.common.tiles.Tile;

import java.util.HashMap;

public class Tiles {
    private static final HashMap<String, Tile> TILES = new HashMap<>();
    public static final Tile TILE = new Tile("tile");
    public static final Tile BRICK = new Tile("brick");
    public static final Tile BLACK_TILE = new Tile("black_tile");
    public static final Tile WALL = new Tile("wall");
    public static final Tile LOG = new Tile("log");
    public static final Tile PLANKS = new Tile("plank");
    public static final Tile DIRT = new Tile("dirt");
    public static final Tile STONE = new Tile("stone");
    public static final Tile RUBY_TILE = new Tile("ruby_tile");
    public static final Tile PAINTING_ONE = new Tile("painting_one");
    public static final Tile PAINTING_TWO = new Tile("painting_two");
    public static final Tile PAINTING_THREE = new Tile("painting_three");
    public static final Tile PLACEHOLDER = new Tile("placeholder");

    public static void init(){
        register(TILE);
        register(BRICK);
        register(BLACK_TILE);
        register(WALL);
        register(PAINTING_ONE, PAINTING_TWO, PAINTING_THREE);
        register(LOG, PLANKS, DIRT, STONE, RUBY_TILE, PLACEHOLDER);
    }

    public static Tile getTile(String name) {
        return TILES.getOrDefault(name, PLACEHOLDER);
    }

    public static void register(Tile tile){
        TILES.put(tile.toString(), tile);
        Items.register(tile.getTileItem());
        //System.out.println(tile.getName());
    }

    public static void register(Tile... tiles){
        for (Tile tile: tiles) {
            register(tile);
        }
    }

    public static HashMap<String, Tile> getTiles(){
        return TILES;
    }
}
