package inferno.saigo.common.world.chunks;

import inferno.saigo.common.tiles.TileState;

public class Chunk {
    private final TileState[][] tiles = new TileState[16][16];

    public void setTile(TileState tile, int x, int y) {
        tiles[x][y] = tile;
    }

    public TileState getTile(int x, int y){
        return tiles[x][y];
    }
}
