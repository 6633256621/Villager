package ent;

import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import panel.Gamepanel;
import javafx.scene.image.Image;

import java.awt.*;

public class Player extends Entity {
    private static final int speed = 2;
    private int size = Gamepanel.getInstance().getTileSize();
    private int spriteCounter = 0;
    private int spriteNum = 1;
    private Image down = new Image(ClassLoader.getSystemResourceAsStream("Character_Down.png"));
    private Image up = new Image(ClassLoader.getSystemResourceAsStream("up.png"));

    private Image left = new Image(ClassLoader.getSystemResourceAsStream("left.png"));

    private Image right = new Image(ClassLoader.getSystemResourceAsStream("right.png"));
    private Image down2 = new Image(ClassLoader.getSystemResourceAsStream("down2.png"));
    private Image down3 = new Image(ClassLoader.getSystemResourceAsStream("down3.png"));
    private Image up2 = new Image(ClassLoader.getSystemResourceAsStream("up2.png"));
    private Image up3 = new Image(ClassLoader.getSystemResourceAsStream("up3.png"));
    private Image left2 = new Image(ClassLoader.getSystemResourceAsStream("left2.png"));
    private Image left3 = new Image(ClassLoader.getSystemResourceAsStream("left3.png"));
    private Image right2 = new Image(ClassLoader.getSystemResourceAsStream("right2.png"));
    private Image right3 = new Image(ClassLoader.getSystemResourceAsStream("right3.png"));

    private Image def;

    public Player() {
        x = 100;
        y = 100;
        z=2;
        def = down;
    }

    private void up() {
        y -= speed;
        if (spriteNum == 1) {
            def = up;
        }
        if (spriteNum == 2) {
            def = up2;
        }
        if (spriteNum == 3) {
            def = up3;
        }
    }

    private void down() {
        y += speed;
        if (spriteNum == 1) {
            def = down;
        }
        if (spriteNum == 2) {
            def = down2;
        }
        if (spriteNum == 3) {
            def = down3;
        }
    }

    private void left() {
        x -= speed;
        if (spriteNum == 1) {
            def = left;
        }
        if (spriteNum == 2) {
            def = left2;
        }
        if (spriteNum == 3) {
            def = left3;
        }
    }

    private void right() {
        x += speed;
        if (spriteNum == 1) {
            def = right;
        }
        if (spriteNum == 2) {
            def = right2;
        }
        if (spriteNum == 3) {
            def = right3;
        }
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
        spriteCounter++;
        if (spriteCounter > 60) {
            spriteCounter = 0;
        } else {
            if (spriteCounter < 20) {
                spriteNum = 1;
            } else if (spriteCounter > 20 && spriteCounter <= 40) {
                spriteNum = 2;
            } else if (spriteCounter > 40) {
                spriteNum = 3;
            }
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, Gamepanel.getInstance().getScreenWidth(), Gamepanel.getInstance().getScreenHeight());
        gc.drawImage(def, x, y, size, size);
    }


}
