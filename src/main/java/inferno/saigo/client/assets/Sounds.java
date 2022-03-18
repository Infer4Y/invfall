package inferno.saigo.client.assets;

import inferno.saigo.client.Main;
import inferno.saigo.client.configuration.ClientSettings;
import inferno.saigo.common.configuration.Settings;

import javax.sound.sampled.*;
import java.util.HashMap;
import java.util.Objects;

public class Sounds {
    private static final HashMap<String, Sound> SOUNDS = new HashMap<>();
    public static final Sound BLOCK_BREAK = new Sound().setRegistryName("break_block").setSoundType(Sound.Type.WORLD);
    public static final Sound RAIN_DROP = new Sound().setRegistryName("rain_drop").setSoundType(Sound.Type.WORLD);
    public static final Sound SONG_ZERO = new Sound().setRegistryName("song_zero");
    public static final Sound SONG_ONE = new Sound().setRegistryName("song_one");
    public static final Sound SONG_TWO = new Sound().setRegistryName("song_two");
    public static final Sound SONG_THREE = new Sound().setRegistryName("song_three");
    public static final Sound SONG_FOUR = new Sound().setRegistryName("song_four");
    public static final Sound SONG_FIVE = new Sound().setRegistryName("song_five");

    public static void init(){
        register(BLOCK_BREAK);
        register(RAIN_DROP);
        register(SONG_ZERO);
        register(SONG_ONE);
        register(SONG_TWO);
        register(SONG_THREE);
        register(SONG_FOUR);
        register(SONG_FIVE);
    }

    public static void register(Sound sound){
        SOUNDS.put(sound.toString(), sound);
    }

    public synchronized static void playSound(Sound sound) {
        new Thread(() -> {
            //System.out.println(ResourceLoader.receiveSoundPath(SOUNDS.get(name)).toString());
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(Main.class.getClassLoader().getResource(Objects.requireNonNull(ResourceLoader.receiveSoundPath(sound)).toString()));

                clip.open(inputStream);

                clip.start();

                setVolume(clip, Float.parseFloat(Settings.getProperty(sound.getSoundType().name().toLowerCase())));

                if (sound.getSoundType() == Sound.Type.MUSIC) {
                    clip.loop(3);
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }).start();
    }

    public static void setVolume(Clip clip, float volume) {
        // Volume 0.0 - 1.0
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    public static HashMap<String, Sound> getSounds(){
        return SOUNDS;
    }
}
