package config;
import javafx.scene.image.Image;

public class GameState {
    public static boolean chestState,traderState,chooseState;
    public static boolean normalState;
    public static boolean nightState=false;
    public static boolean dayIncremented = false;
    public static void update() {
        normalState= !chestState && !traderState;
        if (!nightState) {
            dayPic = Config.sunPic;
        } else {
            dayPic = Config.moonPic;
        }
    }
    public static int traderPage=1;
    public static Image dayPic;
    //0=default play state
    //1=not rendering ui state
}
