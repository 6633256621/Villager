package utility;

import javafx.scene.image.Image;

public class LoadUtility {
    //player image
    public static Image down,down2,down3,up,up2,up3,left,left2,left3,right,right2,right3,
                        downleft,downleft2,downleft3,downright,downright2,downright3,
                        upleft,upleft2,upleft3,upright,upright2,upright3, slime_jump_1,
                        slime_jump_2, slime_jump_3;

    //group of load
    static {
        playerLoad();
    }


    public static void playerLoad() {
        down = new Image(ClassLoader.getSystemResourceAsStream("player/Character_Down.png"));
        up = new Image(ClassLoader.getSystemResourceAsStream("player/up.png"));
        left = new Image(ClassLoader.getSystemResourceAsStream("player/left.png"));
        right = new Image(ClassLoader.getSystemResourceAsStream("player/right.png"));
        down2 = new Image(ClassLoader.getSystemResourceAsStream("player/down2.png"));
        down3 = new Image(ClassLoader.getSystemResourceAsStream("player/down3.png"));
        up2 = new Image(ClassLoader.getSystemResourceAsStream("player/up2.png"));
        up3 = new Image(ClassLoader.getSystemResourceAsStream("player/up3.png"));
        left2 = new Image(ClassLoader.getSystemResourceAsStream("player/left2.png"));
        left3 = new Image(ClassLoader.getSystemResourceAsStream("player/left3.png"));
        right2 = new Image(ClassLoader.getSystemResourceAsStream("player/right2.png"));
        right3 = new Image(ClassLoader.getSystemResourceAsStream("player/right3.png"));
        upleft = new Image(ClassLoader.getSystemResourceAsStream("player/upleft.png"));
        upleft2 = new Image(ClassLoader.getSystemResourceAsStream("player/upleft2.png"));
        upleft3 = new Image(ClassLoader.getSystemResourceAsStream("player/upleft3.png"));
        upright = new Image(ClassLoader.getSystemResourceAsStream("player/upright.png"));
        upright2 = new Image(ClassLoader.getSystemResourceAsStream("player/upright2.png"));
        upright3 = new Image(ClassLoader.getSystemResourceAsStream("player/upright3.png"));
        downright = new Image(ClassLoader.getSystemResourceAsStream("player/downright.png"));
        downright2 = new Image(ClassLoader.getSystemResourceAsStream("player/downright2.png"));
        downright3 = new Image(ClassLoader.getSystemResourceAsStream("player/downright3.png"));
        downleft = new Image(ClassLoader.getSystemResourceAsStream("player/downleft.png"));
        downleft2 = new Image(ClassLoader.getSystemResourceAsStream("player/downleft2.png"));
        downleft3 = new Image(ClassLoader.getSystemResourceAsStream("player/downleft3.png"));
    }

    public static void slimeLoad() {
        slime_jump_1 = new Image(ClassLoader.getSystemResourceAsStream("monster/slime/slime_jump_1.png"));
        slime_jump_2 = new Image(ClassLoader.getSystemResourceAsStream("monster/slime/slime_jump_2.png"));
        slime_jump_3 = new Image(ClassLoader.getSystemResourceAsStream("monster/slime/slime_jump_3.png"));
    }
}
