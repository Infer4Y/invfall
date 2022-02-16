package inferno.saigo.common.maps;

import inferno.saigo.common.tiles.TileData;

import java.util.List;

public class Map {
    public int width, height;
    public List<TileData> tiles;

    @Override
    public String toString() {
        return "Map[" +
                "width=" + width +
                ", height=" + height +
                ", tiles=" + tiles +
                ']';
    }
}
