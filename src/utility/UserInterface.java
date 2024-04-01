package utility;

import com.sun.javafx.tk.FontLoader;
import config.Config;
import interfacep.Storable;
import object.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import panel.GamePanel;

public class UserInterface {
    GamePanel gp;
    Font arial_40;
    private Font customFont;

    Player p;
    public static int leftSlotCol = 0;
    public static int leftSlotRow = 0;
    public static int rightSlotCol = 0;
    public static int rightSlotRow = 0;


    public UserInterface(GamePanel gp) {
        this.gp = gp;
        p = Player.getInstance();
        customFont = new Font("Georgia",20);
    }
    public void draw(GraphicsContext gc) {
//        gc.setFont(arial_40);
//        gc.setFill(Color.BLACK);
//        gc.fillText("Key = ",50,50);
        if (InputUtility.isKeyPressed(KeyCode.R)) {
            drawCharacterScreen(gc);
            drawInventory(p,gc,"right");
        }
        drawMoney(gc);
    }

    public void drawCharacterScreen(GraphicsContext gc) {
        final int frameX = Config.tileSize;
        final int frameY = Config.tileSize;
        final int frameWidth = Config.tileSize * 5;
        final int frameHeight = Config.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight, gc);
        int x = frameX;
        int y = frameY;
        int last = x + frameWidth - 50;
        drawStatusText(x, y, last, gc);
    }

    public static void drawSubWindow(int x, int y, int width, int height, GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.setGlobalAlpha(0.5);
        gc.fillRoundRect(x, y, width, height, 35, 35);
        gc.setGlobalAlpha(1);
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        gc.strokeRoundRect(x + 5, y + 5, width - 10, height - 10, 50, 50);
        gc.strokeRoundRect(x + 1, y + 1, width - 1, height - 1, 25, 25);
        gc.setLineWidth(1);
    }

    public void drawStatusText(int x, int y, int last, GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setFont(customFont);
        gc.fillText("Level", x + 20, y + Config.tileSize);
        gc.fillText("STR", x + 20, y + Config.tileSize * 2);
        gc.fillText("DEX", x + 20, y + Config.tileSize * 3);
        gc.fillText("ATK", x + 20, y + Config.tileSize * 4);
        gc.fillText("DEF", x + 20, y + Config.tileSize * 5);
        gc.fillText("EXP", x + 20, y + Config.tileSize * 6);
        gc.fillText("Next LV EXP", x + 20, y + Config.tileSize * 7);
        gc.fillText("Weapon", x + 20, y + Config.tileSize * 8);
        gc.fillText("Shield", x + 20, y + Config.tileSize * 9);

        gc.setFill(Color.WHITE);
        gc.fillText("" + p.getLevel(), last, y + Config.tileSize);
        gc.fillText("" + p.getStrength(), last, y + Config.tileSize * 2);
        gc.fillText("" + p.getDex(), last, y + Config.tileSize * 3);
        gc.fillText("" + p.getAttack(), last, y + Config.tileSize * 4);
        gc.fillText("" + p.getDefense(), last, y + Config.tileSize * 5);
        gc.fillText("" + p.getExp(), last, y + Config.tileSize * 6);
        gc.fillText("" + p.getNextLevelExp(), last, y + Config.tileSize * 7);
        gc.drawImage(p.getCurrentWeapon().getImage(), last - 20, y + Config.tileSize * 7 + 15, Config.tileSize, Config.tileSize);
        gc.drawImage(p.getCurrentShield().getImage(), last - 20, y + Config.tileSize * 8 + 15, Config.tileSize, Config.tileSize);

    }
    public void drawMoney(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setFont(customFont);
        gc.setFont(new Font(40));
        gc.fillText("Money : "+p.getMoney(),Config.tileSize*27,Config.tileSize*17);
        gc.setFont(new Font(20));
    }

    public static String drawInventory(Storable e,GraphicsContext gc,String side) {
        int frameX;
        // overall frame
        if(side=="right") {
            frameX = Config.tileSize * 25;
        } else {
            frameX = Config.tileSize;
        }

        int frameY = Config.tileSize;
        int frameWidth = Config.tileSize * 6;
        int frameHeight = Config.tileSize * 5;
        UserInterface.drawSubWindow(frameX, frameY, frameWidth, frameHeight, gc);
        //slot
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;

        for(int i=0;i<e.getInventory().size();i++) {
            gc.drawImage(e.getInventory().get(i).getImage(),slotX,slotY,Config.tileSize,Config.tileSize);

            slotX+=Config.tileSize;
            if(i==4||i==9||i==14) {
                slotX=slotXStart;
                slotY+=Config.tileSize;
            }
        }
        int cursorX,cursorY;
        //cursor
        if (side=="left"){
            cursorX = slotXStart + (Config.tileSize * UserInterface.leftSlotCol);
            cursorY = slotYStart + (Config.tileSize * UserInterface.leftSlotRow);
        } else {
            cursorX = slotXStart + (Config.tileSize * UserInterface.rightSlotCol);
            cursorY = slotYStart + (Config.tileSize * UserInterface.rightSlotRow);
        }
        int cursorWidth = Config.tileSize;
        int cursorHeight = Config.tileSize;
        // Draw cursor
        gc.setFill(Color.WHITE);
        gc.setLineWidth(3);
        gc.strokeRoundRect(cursorX,cursorY,cursorWidth,cursorHeight,10,10);
        gc.setLineWidth(1);

        int dFrameX = frameX;
        int dFrameY = frameY +frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = Config.tileSize*3;
        //description
        int textX = dFrameX +20;
        int textY = dFrameY+Config.tileSize;

        int itemIndex = getItemIndexOnSlot(side);
        if(itemIndex<e.getInventory().size()) {
            drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight,gc);
            gc.setFill(Color.WHITE);
            gc.fillText(e.getInventory().get(itemIndex).getDescription(),textX,textY);
            gc.setFill(Color.BLACK);
        }
        return side;
    }
    public static int getItemIndexOnSlot(String side) {
        int itemIndex;
        if (side=="left") {
            itemIndex = UserInterface.leftSlotCol+(UserInterface.leftSlotRow*5);
        } else {
            itemIndex = UserInterface.rightSlotCol+(UserInterface.rightSlotRow*5);
        }
        return itemIndex;
    }
}
