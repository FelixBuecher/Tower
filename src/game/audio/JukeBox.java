package game.audio;

import game.tools.Constants;

import javax.sound.sampled.*;
import java.util.HashMap;
import java.util.Objects;

/**
 * This class is used to load music and sound effects into the game.
 * It also is used to play and loop these sounds.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public class JukeBox {


	private static HashMap<String, Clip> clips;
	private static int gap;
	private static final boolean mute = false;

    /**
     * Used to initialize the hashmap to map sounds together with short strings
     * to have an easier time accessing them later.
     */
	public static void init() {
		clips = new HashMap<>();
		gap = 0;
	}

    /**
     * Used to load up a sound file and map it.
     * For now I only support .mp3 files and for this I had to import
     * the mp3spi library.
     * @param s the path to the sound file
     * @param n the string to map the file to
     * @param type if true you load from SFX if false you load from Music
     */
	public static void load(String s, String n, boolean type) {
		if(clips.get(n) != null) return;
		Clip clip;
        String path = Constants.MUSICP;
        if(type) path = Constants.SFXP;
		try {			
			AudioInputStream ais = AudioSystem.getAudioInputStream(Objects.requireNonNull(JukeBox.class.getResourceAsStream(path + s + ".mp3")));
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false);
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
			clips.put(n, clip);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * Used to play a sound effect (The higher v is the quieter the sound gets)
     * @param s the sound file
     * @param v -volume (higher v = quieter sound)
     */
	public static void play(String s, int v) {
		play(s, gap, v);
	}

    /**
     * Used to play a sound effect
     * @param s the sound file
     * @param i the first sound frame to play
     * @param v -volume (higher v = quieter sound)
     */
	public static void play(String s, int i, int v) {
		if(mute) return;
		Clip c = clips.get(s);
		if(c == null) return;
		// Controlling the volume based on v, I plan to add a menu option for sound later
        FloatControl gC = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
        gC.setValue(-1 * v);
		if(c.isRunning()) c.stop();
		c.setFramePosition(i);
		while(!c.isRunning()) c.start();
	}

    /**
     * Stops the sound file if it is playing
     * @param s the sound file
     */
    public static void stop(String s) {
        if(clips.get(s) == null) return;
        if(clips.get(s).isRunning()) clips.get(s).stop();
    }

    /**
     * Used to unmute a sound file if it has only been temporarily muted
     * @param s the sound file
     */
    public static void resume(String s) {
        if(mute) return;
        if(clips.get(s).isRunning()) return;
        clips.get(s).start();
    }

    /**
     * Used to create a looping BGM sound.
     * @param s the sound file
     * @param v -volume (higher v = quieter sound)
     */
	public static void loop(String s, int v) {
		loop(s, gap, gap, clips.get(s).getFrameLength() - 1, v);
	}

    /**
     * Used to create a looping BGM sound that is starting from a specific position.
     * @param s the sound file
     * @param frame the first frame to play from
     * @param v -volume (higher v = quieter sound)
     */
	public static void loop(String s, int frame, int v) {
		loop(s, frame, gap, clips.get(s).getFrameLength() - 1, v);
	}

    /**
     * Used to create a looping BGM sound and to set the boundary's of the loop.
     * @param s the sound file
     * @param start the frame from where to loop
     * @param end the end frame of the loop
     * @param v -volume (higher v = quieter sound)
     */
	public static void loop(String s, int start, int end, int v) {
		loop(s, gap, start, end, v);
	}

    /**
     * Used to create a looping BGM sound.
     * @param s the sound file
     * @param frame the first frame to play from
     * @param start the frame from where to loop
     * @param end the end frame of the loop
     * @param v -volume (higher v = quieter sound)
     */
	public static void loop(String s, int frame, int start, int end, int v) {
		stop(s);
		if(mute) return;
        FloatControl gC = (FloatControl) clips.get(s).getControl(FloatControl.Type.MASTER_GAIN);
        gC.setValue(-1 * v);
		clips.get(s).setLoopPoints(start, end);
		clips.get(s).setFramePosition(frame);
		clips.get(s).loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public static void close(String s) {
		stop(s);
		clips.get(s).close();
	}
	
}