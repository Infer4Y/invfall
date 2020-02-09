package inferno.saigo.client.rendering;

public abstract class RenderableCoord extends Renderable {
    private int x, y;

    public RenderableCoord(int x, int y) {
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
