package ent;

import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import panel.Gamepanel;

public class Player extends Entity{
    private static final int speed =1;
    private int size = Gamepanel.getInstance().getTileSize();
    public Player() {
        x=100;
        y=100;
    }
    private void up() {
        y-=speed;
    }
    private void down() {
        y+=speed;
    }
    private void left() {
        x-=speed;
    }
    private void right() {
        x+=speed;
    }
    public void update() {
        if (InputUtility.getKeyPressed(KeyCode.W)) {
            up();
        }
        if (InputUtility.getKeyPressed(KeyCode.S)) {
            down();
        }
        if (InputUtility.getKeyPressed(KeyCode.A)) {
            left();
        }
        if (InputUtility.getKeyPressed(KeyCode.D)) {
            right();
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, Gamepanel.getInstance().getWidth(), Gamepanel.getInstance().getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(x,y,size,size);
    }
}
