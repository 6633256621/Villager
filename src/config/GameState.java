package config;

public class GameState {
    public static boolean chestState,traderState,chooseState;
    public static boolean normalState;
    public static void update() {
        normalState= !chestState && !traderState;
    }
    public static int traderPage=1;
    //0=default play state
    //1=not rendering ui state
}
