package utility;

import config.Config;
import logic.GameLogic;
import object.items.House;
import object.Trader;
import object.potion.HealthPotion;

public class ObjectSetter {
    GameLogic gl;
    private House house = House.getInstance();

    public ObjectSetter(GameLogic gl) {
        this.gl=gl;
    }
    public void setObject() {
        gl.addNewObject(new HealthPotion());
        gl.getGameObjectContainer().getLast().setWorldX((29+Config.fixedPosition)*Config.tileSize);
        gl.getGameObjectContainer().getLast().setWorldY((13+Config.fixedPosition)*Config.tileSize);
        gl.addNewObject(new Trader());
        gl.addNewObject(house);
    }
}
