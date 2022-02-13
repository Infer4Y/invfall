package inferno.saigo.client.utils.client;

public class Key {

    /* toggles the keys current state*/
    public void toggle(){
        isDown =  !isDown;
    }

    public boolean isDown;
}
