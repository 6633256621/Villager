package ent;

import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import panel.Gamepanel;
import javafx.scene.image.Image;

import java.awt.*;

import static java.lang.Math.sqrt;

public class Player extends Entity {
    private static final double speed = 2;
    private static final double sideSpeed = sqrt((speed*speed)/2);

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
    private Image upleft = new Image(ClassLoader.getSystemResourceAsStream("upleft.png"));
    private Image upleft2 = new Image(ClassLoader.getSystemResourceAsStream("upleft2.png"));
    private Image upleft3 = new Image(ClassLoader.getSystemResourceAsStream("upleft3.png"));
    private Image upright = new Image(ClassLoader.getSystemResourceAsStream("upright.png"));
    private Image upright2 = new Image(ClassLoader.getSystemResourceAsStream("upright2.png"));
    private Image upright3 = new Image(ClassLoader.getSystemResourceAsStream("upright3.png"));
    private Image downright = new Image(ClassLoader.getSystemResourceAsStream("downright.png"));
    private Image downright2 = new Image(ClassLoader.getSystemResourceAsStream("downright2.png"));
    private Image downright3 = new Image(ClassLoader.getSystemResourceAsStream("downright3.png"));
    private Image downleft = new Image(ClassLoader.getSystemResourceAsStream("downleft.png"));
    private Image downleft2 = new Image(ClassLoader.getSystemResourceAsStream("downleft2.png"));
    private Image downleft3 = new Image(ClassLoader.getSystemResourceAsStream("downleft3.png"));


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
    private void upleft() {
        x -= sideSpeed;
        y -= sideSpeed;
        if (spriteNum == 1) {
            def = upleft;
        }
        if (spriteNum == 2) {
            def = upleft2;
        }
        if (spriteNum == 3) {
            def = upleft3;
        }
    }
    private void upright() {
        x += sideSpeed;
        y -= sideSpeed;
        if (spriteNum == 1) {
            def = upright;
        }
        if (spriteNum == 2) {
            def = upright2;
        }
        if (spriteNum == 3) {
            def = upright3;
        }
    }
    private void downright() {
        x += sideSpeed;
        y += sideSpeed;
        if (spriteNum == 1) {
            def = downright;
        }
        if (spriteNum == 2) {
            def = downright2;
        }
        if (spriteNum == 3) {
            def = downright3;
        }
    }
    private void downleft() {
        x -= sideSpeed;
        y += sideSpeed;
        if (spriteNum == 1) {
            def = downleft;
        }
        if (spriteNum == 2) {
            def = downleft2;
        }
        if (spriteNum == 3) {
            def = downleft3;
        }
    }

    public void update() {
        if (InputUtility.getKeyPressed(KeyCode.W)&&InputUtility.onlyKeypressed()) {
            up();
        }
        if (InputUtility.getKeyPressed(KeyCode.S)&&InputUtility.onlyKeypressed()) {
            down();
        }
        if (InputUtility.getKeyPressed(KeyCode.A)&&InputUtility.onlyKeypressed()) {
            left();
        }
        if (InputUtility.getKeyPressed(KeyCode.D)&&InputUtility.onlyKeypressed()) {
            right();
        }
        if (InputUtility.getKeyPressed(KeyCode.W)&&InputUtility.getKeyPressed(KeyCode.A)) {
            upleft();
        }
        if (InputUtility.getKeyPressed(KeyCode.W)&&InputUtility.getKeyPressed(KeyCode.D)) {
            upright();
        }
        if (InputUtility.getKeyPressed(KeyCode.S)&&InputUtility.getKeyPressed(KeyCode.D)) {
            downright();
        }
        if (InputUtility.getKeyPressed(KeyCode.S)&&InputUtility.getKeyPressed(KeyCode.A)) {
            downleft();
        }
        spriteCounter++;
        if (spriteCounter > 45) {
            spriteCounter = 0;
        } else {
            if (spriteCounter < 15) {
                spriteNum = 1;
            } else if (spriteCounter > 15 && spriteCounter <= 30) {
                spriteNum = 2;
            } else if (spriteCounter > 30) {
                spriteNum = 3;
            }
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(def, x, y, size, size);
    }


}
