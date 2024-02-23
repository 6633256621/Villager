package panel;

import ent.GameLogic;
import ent.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import utility.InputUtility;

public class UserInterface {
    GamePanel gp;
    Font arial_40;
    Font arial_custom=new Font("Arial",25);

    Player p = Player.getInstance();


    public UserInterface(GamePanel gp) {
        this.gp=gp;
        arial_40 =new Font("Arial",40);
    }

    public void draw(GraphicsContext gc) {
//        gc.setFont(arial_40);
//        gc.setFill(Color.BLACK);
//        gc.fillText("Key = ",50,50);
        if (InputUtility.isKeyPressed(KeyCode.R)) {
            drawCharacterScreen(gc);
        }
    }
    public void drawCharacterScreen(GraphicsContext gc) {
        final int frameX = gp.getTileSize();
        final int frameY = gp.getTileSize();
        final int frameWidth = gp.getTileSize()*5;
        final int frameHeight = gp.getTileSize()*10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight,gc);
        int x = frameX;
        int y =frameY;

        gc.setFill(Color.WHITE);
        gc.setFont(arial_custom);
        gc.fillText("Level",x+20,y+ gp.getTileSize());
        gc.fillText("STR",x+20,y+gp.getTileSize()*2);
        gc.fillText("DEX",x+20,y+gp.getTileSize()*3);
        gc.fillText("ATK",x+20,y+gp.getTileSize()*4);
        gc.fillText("DEF",x+20,y+gp.getTileSize()*5);
        gc.fillText("EXP",x+20,y+gp.getTileSize()*6);
        gc.fillText("Next LV EXP",x+20,y+gp.getTileSize()*7);
        gc.fillText("Weapon",x+20,y+ gp.getTileSize()*8);

        int last = x+frameWidth- 50;
        gc.setFill(Color.WHITE);
        gc.fillText(""+p.getLevel(),last,y+ gp.getTileSize());
        gc.fillText(""+p.getStrength(),last,y+ gp.getTileSize()*2);
        gc.fillText(""+p.getDex(),last,y+ gp.getTileSize()*3);
        gc.fillText(""+p.getAttack(),last,y+ gp.getTileSize()*4);
        gc.fillText(""+p.getDefense(),last,y+ gp.getTileSize()*5);
        gc.fillText(""+p.getExp(),last,y+ gp.getTileSize()*6);
        gc.fillText(""+p.getNextLevelExp(),last,y+ gp.getTileSize()*7);
        gc.drawImage(new Image("sword.png"),last-15,y+gp.getTileSize()*7+15,gp.getTileSize(), gp.getTileSize());

    }
    public void drawSubWindow(int x,int y,int width,int height,GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.setGlobalAlpha(0.5);
        gc.fillRoundRect(x,y,width,height,35,35);
        gc.setGlobalAlpha(1);
        gc.setStroke(Color.WHITE);
        gc.strokeRoundRect(x+5,y+5,width-10,height-10,25,25);
        gc.strokeRoundRect(x+1,y+1,width-1,height-1,25,25);
    }
    public void drawStatusText(int x,int y,GraphicsContext gc) {

    }
}
