package config;

public class GameState {
    public static boolean chestState,traderState;
    public static boolean normalState;
    public static void update() {
        normalState= !chestState && !traderState;
    }
    //0=default play state
    //1=not rendering ui state
}
