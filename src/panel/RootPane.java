package panel;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import utility.InputUtility;

public class RootPane extends StackPane {
    public RootPane() {
        this.setBackground(new Background(new BackgroundFill(Color.FORESTGREEN,null,null)));
    }
    public void addlistener() {
        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                InputUtility.setKeyPressed(keyEvent.getCode(), true);
                InputUtility.setKeyTriggered(keyEvent.getCode(),true);
            }
        });
        setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                InputUtility.setKeyPressed(keyEvent.getCode(), false);
                InputUtility.setKeyTriggered(keyEvent.getCode(),false);

            }
        });
    }
}
