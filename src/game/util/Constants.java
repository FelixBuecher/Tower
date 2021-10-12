package game.util;

/**
 * Basically just a collection of some constants that I can access
 * from anywhere if I need to, I might need to look into a JSON solution
 * if this becomes bothersome.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public class Constants {

    private Constants() {

    }

    // The title of the game
    public static final String TITLE = "Tower";

    // The normal tilesize for the game
    public static final int TILESIZE = 32;

    // The resolutions of the game
    public static int width = 960;
    public static int height = width / 16 * 9;
    public static double scale = 1.5;

    // Used to show hitboxes of the respective aspect of the game
    public static boolean drawHitBox = false;

    // The standart volume reduction (Will replace with a proper option later)
    public static int v_bgm = 15;
    public static int v_sfx = 15;

    // Strings for all the paths to resources, if I change my mind about folder
    // Structure I only need to change it in here.
    public static final String LEVELP = "/Textures/Level/";
    public static final String SPRITEP = "/Textures/Sprites/";
    public static final String TILEP = "/Textures/Tiles/";
    public static final String GUIP = "/Textures/GUI/";
    public static final String SFXP = "/Audio/SFX/";
    public static final String MUSICP = "/Audio/Music/";

}
