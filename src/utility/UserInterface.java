package utility;

import config.Config;
import object.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import panel.GamePanel;

public class UserInterface {
    GamePanel gp;
    Font arial_40;
    Font arial_custom = new Font("Arial", 25);

    Player p;
    public static int slotCol = 0;
    public static int slotRow = 0;


    public UserInterface(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", 40);
        p = Player.getInstance();
    }
    public void draw(GraphicsContext gc) {
//        gc.setFont(arial_40);
//        gc.setFill(Color.BLACK);
//        gc.fillText("Key = ",50,50);
        if (InputUtility.isKeyPressed(KeyCode.R)) {
            drawCharacterScreen(gc);
            drawInventory(gc);

        }
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

    public void drawSubWindow(int x, int y, int width, int height, GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.setGlobalAlpha(0.5);
        gc.fillRoundRect(x, y, width, height, 35, 35);
        gc.setGlobalAlpha(1);
        gc.setStroke(Color.WHITE);
        gc.strokeRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
        gc.strokeRoundRect(x + 1, y + 1, width - 1, height - 1, 25, 25);
    }

    public void drawStatusText(int x, int y, int last, GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setFont(arial_custom);
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

    public void drawInventory(GraphicsContext gc) {
        // overall frame
        int frameX = Config.tileSize * 25;
        int frameY = Config.tileSize;
        int frameWidth = Config.tileSize * 6;
        int frameHeight = Config.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight, gc);
        //slot
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;

        for(int i=0;i<p.getInventory().size();i++) {
            gc.drawImage(p.getInventory().get(i).getImage(),slotX,slotY,Config.tileSize,Config.tileSize);

            slotX+=Config.tileSize;
            if(i==4||i==9||i==14) {
                slotX=slotXStart;
                slotY+=Config.tileSize;
            }
        }

        //cursor
        int cursorX = slotXStart + (Config.tileSize * slotCol);
        int cursorY = slotYStart + (Config.tileSize * slotRow);
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
        gc.setFont(arial_custom);

        int itemIndex = getItemIndexOnSlot();
        if(itemIndex<p.getInventory().size()) {
            drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight,gc);
            gc.setFill(Color.WHITE);
            gc.fillText(p.getInventory().get(itemIndex).getDescription(),textX,textY);
            gc.setFill(Color.BLACK);
        }
    }
    private int getItemIndexOnSlot() {
        int itemIndex = slotCol+(slotRow*5);
        return itemIndex;
    }
}
