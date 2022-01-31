package inferno.saigo.client.rendering;

public abstract class ObjectRenderingCoord extends ObjectRendering {
    private final int x, y;

    public ObjectRenderingCoord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
