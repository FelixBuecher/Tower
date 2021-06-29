package game.tools;

/**
 * Basically just a collection of some constants that I can access
 * from anywhere if I need to, I might need to look into a JSON solution
 * if this becomes bothersome.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public class Constants {
    // The title of the game, will be shown in the top left
    public static final String TITLE = "Tower";

    // The normal tilesize for the game
    public static final int TILESIZE = 32;

    // The resolutions of the game
    public static final int WIDTH = 800;
    public static final int HEIGHT = WIDTH / 16 * 9;
    public static final int SCALE = 2;

    // The standart volume reduction (Will replace with a proper option later
    public static final int VOLUME = 20;

    // Strings for all the paths to resources, if I change my mind about folder
    // Structure I only need to change it in here.
    public static final String LEVELP = "/Textures/Level/";
    public static final String SPRITEP = "/Textures/Sprites/";
    public static final String TILEP = "/Textures/Tiles/";
    public static final String GUIP = "/Textures/GUI/";
    public static final String SFXP = "/Audio/SFX/";
    public static final String MUSICP = "/Audio/Music/";

    private Constants() {

    }
}
