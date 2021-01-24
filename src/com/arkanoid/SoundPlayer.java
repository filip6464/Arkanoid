package com.arkanoid;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundPlayer {

    public final static String BLOCK_BREAK = "src/resources/1.wav";
    public final static String PADDLE_COLISION = "src/resources/2.wav";
    public final static String GAME_START = "src/resources/3.wav";
    public final static String GAME_OVER = "src/resources/4.wav";
    public final static String WIN = "src/resources/5.wav";

    public static synchronized void playSound(String url){
        try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
