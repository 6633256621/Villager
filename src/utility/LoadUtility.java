package utility;

import javafx.scene.image.Image;

public class LoadUtility {
    public static Image down,down2,down3,up,up2,up3,left,left2,left3,right,right2,right3,
                        downleft,downleft2,downleft3,downright,downright2,downright3,
                        upleft,upleft2,upleft3,upright,upright2,upright3;
    static {
        playerLoad();
    }
    public static void playerLoad() {
        down = new Image(ClassLoader.getSystemResourceAsStream("Character_Down.png"));
        up = new Image(ClassLoader.getSystemResourceAsStream("up.png"));
        left = new Image(ClassLoader.getSystemResourceAsStream("left.png"));
        right = new Image(ClassLoader.getSystemResourceAsStream("right.png"));
        down2 = new Image(ClassLoader.getSystemResourceAsStream("down2.png"));
        down3 = new Image(ClassLoader.getSystemResourceAsStream("down3.png"));
        up2 = new Image(ClassLoader.getSystemResourceAsStream("up2.png"));
        up3 = new Image(ClassLoader.getSystemResourceAsStream("up3.png"));
        left2 = new Image(ClassLoader.getSystemResourceAsStream("left2.png"));
        left3 = new Image(ClassLoader.getSystemResourceAsStream("left3.png"));
        right2 = new Image(ClassLoader.getSystemResourceAsStream("right2.png"));
        right3 = new Image(ClassLoader.getSystemResourceAsStream("right3.png"));
        upleft = new Image(ClassLoader.getSystemResourceAsStream("upleft.png"));
        upleft2 = new Image(ClassLoader.getSystemResourceAsStream("upleft2.png"));
        upleft3 = new Image(ClassLoader.getSystemResourceAsStream("upleft3.png"));
        upright = new Image(ClassLoader.getSystemResourceAsStream("upright.png"));
        upright2 = new Image(ClassLoader.getSystemResourceAsStream("upright2.png"));
        upright3 = new Image(ClassLoader.getSystemResourceAsStream("upright3.png"));
        downright = new Image(ClassLoader.getSystemResourceAsStream("downright.png"));
        downright2 = new Image(ClassLoader.getSystemResourceAsStream("downright2.png"));
        downright3 = new Image(ClassLoader.getSystemResourceAsStream("downright3.png"));
        downleft = new Image(ClassLoader.getSystemResourceAsStream("downleft.png"));
        downleft2 = new Image(ClassLoader.getSystemResourceAsStream("downleft2.png"));
        downleft3 = new Image(ClassLoader.getSystemResourceAsStream("downleft3.png"));
    }
}
