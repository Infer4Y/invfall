package inferno.saigo.common.tiles;

import inferno.saigo.common.maps.MapWorld;

public class TileData {
    public Tile tile;
    public int x, y;


    @Override
    public String toString() {
        return "TileData[" +
                "tile=" + tile +
                ", x=" + x +
                ", y=" + y +
                ']';
    }

    public void update(MapWorld world) {
        tile.update(world, this);
    }
}
