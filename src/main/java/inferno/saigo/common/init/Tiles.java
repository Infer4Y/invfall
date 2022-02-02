package inferno.saigo.common.init;

import inferno.saigo.common.tiles.Tile;

import java.util.HashMap;

public class Tiles {
    private static final HashMap<String, Tile> TILES = new HashMap<>();
    public static final Tile TILE = new Tile("tile");
    public static final Tile BRICK = new Tile("brick");
    public static final Tile BLACK_TILE = new Tile("black_tile");
    public static final Tile WALL = new Tile("wall");
}
