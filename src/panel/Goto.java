package panel;

import config.GameState;

public class Goto {
    public static void startGame() {
        GameState.start=true;
        RootPane.getInstance().getChildren().removeLast();
    }
}
