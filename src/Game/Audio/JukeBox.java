package Game.Audio;

import javax.sound.sampled.*;
import java.util.HashMap;


/**
 * Jukebox for playing BGM and SFX.
 *
 * @Author: Felix Buecher
 * @Version: 1.0
 */
public class JukeBox {
	
	private static HashMap<String, Clip> clips;
	private static int gap;
	private static boolean mute = false;
	
	public static void init() {
		clips = new HashMap<String, Clip>();
		gap = 0;
	}
	
	public static void load(String s, String n) {
		if(clips.get(n) != null) return;
		Clip clip;
		try {			
			AudioInputStream ais = AudioSystem.getAudioInputStream(JukeBox.class.getResourceAsStream(s));
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(
				AudioFormat.Encoding.PCM_SIGNED,
				baseFormat.getSampleRate(),
				16,
				baseFormat.getChannels(),
				baseFormat.getChannels() * 2,
				baseFormat.getSampleRate(),
				false
			);
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
			clips.put(n, clip);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void play(String s, int v) {
		play(s, gap, v);
	}
	
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

    public static void stop(String s) {
        if(clips.get(s) == null) return;
        if(clips.get(s).isRunning()) clips.get(s).stop();
    }

    public static void resume(String s) {
        if(mute) return;
        if(clips.get(s).isRunning()) return;
        clips.get(s).start();
    }
	
	public static void loop(String s, int v) {
		loop(s, gap, gap, clips.get(s).getFrameLength() - 1, v);
	}
	
	public static void loop(String s, int frame, int v) {
		loop(s, frame, gap, clips.get(s).getFrameLength() - 1, v);
	}

	public static void loop(String s, int start, int end, int v) {
		loop(s, gap, start, end, v);
	}
	
	public static void loop(String s, int frame, int start, int end, int v) {
		stop(s);
		if(mute) return;
        FloatControl gC = (FloatControl) clips.get(s).getControl(FloatControl.Type.MASTER_GAIN);
        gC.setValue(-1 * v);
		clips.get(s).setLoopPoints(start, end);
		clips.get(s).setFramePosition(frame);
		clips.get(s).loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public static void setPosition(String s, int frame) {
		clips.get(s).setFramePosition(frame);
	}
	
	public static void close(String s) {
		stop(s);
		clips.get(s).close();
	}
	
}