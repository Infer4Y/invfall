package inferno.saigo.common.tiles;

public class Tile {
    private String name;
    private boolean
            solid = false,
            air = false,
            tickable = false;

    public Tile(String name) {
        this.name = name;
    }

    public Tile(String name, boolean solid, boolean air, boolean tickable) {
        this.name = name;
        this.solid = solid;
        this.air = air;
        this.tickable = tickable;
    }

    public String getName() {
        return name;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean isAir() {
        return air;
    }

    public boolean isTickable() {
        return tickable;
    }
}
