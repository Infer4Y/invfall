package inferno.saigo.common.maps;

import inferno.saigo.common.tiles.TileData;

public class Map {
    public int width, height;
    public TileData[][] tiles;

    @Override
    public String toString() {
        return "Map[" +
                "width=" + width +
                ", height=" + height +
                ", tiles=" + tiles +
                ']';
    }
}
