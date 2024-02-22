package ent;

import utility.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import panel.Gamepanel;
import javafx.scene.image.Image;

import static utility.LoadUtility.*;

public class Player extends Entity {
    private static double speed = 2;//player spped
    private static double sideSpeed = speed * (Math.cos(Math.toRadians(45.0)));//speed when sidewalk
    private int tileSize = Gamepanel.getInstance().getTileSize();//tile size
    private int spriteCounter = 0;
    private int spriteNum = 1;
    private Image def;//display image at that moment

    //constructor
    public Player() {
        x = 355;
        y = 250;
        z = 2;
        def = down;
        playerLoad();
    }
    
    //movement
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
    
    //fetch position
    public void update() {
        if (InputUtility.isKeyPressed(KeyCode.W) && InputUtility.isOnlyOneKeyPressed()) {
            up();
        }
        if (InputUtility.isKeyPressed(KeyCode.S) && InputUtility.isOnlyOneKeyPressed()) {
            down();
        }
        if (InputUtility.isKeyPressed(KeyCode.A) && InputUtility.isOnlyOneKeyPressed()) {
            left();
        }
        if (InputUtility.isKeyPressed(KeyCode.D) && InputUtility.isOnlyOneKeyPressed()) {
            right();
        }
        if (InputUtility.isKeyPressed(KeyCode.W) && InputUtility.isKeyPressed(KeyCode.A)) {
            upleft();
        }
        if (InputUtility.isKeyPressed(KeyCode.W) && InputUtility.isKeyPressed(KeyCode.D)) {
            upright();
        }
        if (InputUtility.isKeyPressed(KeyCode.S) && InputUtility.isKeyPressed(KeyCode.D)) {
            downright();
        }
        if (InputUtility.isKeyPressed(KeyCode.S) && InputUtility.isKeyPressed(KeyCode.A)) {
            downleft();
        }
        spriteCount();
    }
    
    //sprite counter so my player can walk
    private void spriteCount() {
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
    
    //draw image
    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(def, x, y, tileSize, tileSize);
    }
}
