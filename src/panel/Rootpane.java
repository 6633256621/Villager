package panel;

import utility.InputUtility;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

public class Rootpane extends StackPane {
    public void addlistener() {
        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                InputUtility.setKeyPressed(keyEvent.getCode(), true);
            }
        });
        setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                InputUtility.setKeyPressed(keyEvent.getCode(), false);
            }
        });
    }
}
