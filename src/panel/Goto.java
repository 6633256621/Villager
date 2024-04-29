package panel;

import config.GameState;
import javafx.geometry.Pos;

public class Goto {
    public static void startGame() {
        GameState.start=true;
        RootPane.getInstance().getChildren().removeLast();
    }
    public static void infoScreen() {
        InfoPane infoPane = new InfoPane();
        RootPane.getInstance().getChildren().add(infoPane);
        RootPane.setAlignment(infoPane, Pos.CENTER);
    }
    public static void closeTab() {
        RootPane.getInstance().getChildren().removeLast();
    }
}