package inferno.saigo.client.rendering;

public abstract class ObjectRenderingCoord extends ObjectRendering {
    private final float x, y;

    public ObjectRenderingCoord(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
