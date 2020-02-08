package inferno.saigo.client.rendering;

public class Camera {
    private float x, y;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update(float x, float y){
        this.x += x;
        this.y += y;
    }

    public void moveTo(float x, float y){
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
