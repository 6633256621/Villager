package utility;

import config.Config;
import logic.GameLogic;
import object.Trader;
import object.monster.Slime;
import object.potion.HealthPotion;
import panel.GamePanel;

public class ObjectSetter {
    GameLogic gl;

    public ObjectSetter(GameLogic gl) {
        this.gl=gl;
    }
    public void setObject() {
        gl.addNewObject(new HealthPotion());
        gl.getGameObjectContainer().getLast().setWorldX((29+Config.fixedPosition)*Config.tileSize);
        gl.getGameObjectContainer().getLast().setWorldY((13+Config.fixedPosition)*Config.tileSize);
        gl.addNewObject(new Trader());

        gl.addSlime();
    }
}
