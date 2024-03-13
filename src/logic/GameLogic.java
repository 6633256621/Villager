package logic;

import object.Chest;
import object.Object;
import object.Player;
import render.RenderableHolder;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    //container for entity and object
    private List<Object> gameObjectContainer;
    //each
    private Player player;
    private Chest chest1;

    //constructor(setup for all entity)
    public GameLogic() {
        gameObjectContainer = new ArrayList<>();
        player = Player.getInstance();
        chest1 = new Chest(23,7);
        addNewObject(player);
        addNewObject(chest1);
    }

    //function for add object to container
    private void addNewObject(Object object) {
        gameObjectContainer.add(object);
        RenderableHolder.getInstance().add(object);
    }

    //fetch
    public void logicUpdate() {
        player.update();
    }
    //getter
    public Player getPlayer() {
        return player;
    }

    public List<Object> getGameObjectContainer() {
        return gameObjectContainer;
    }

    public Chest getChest1() {
        return chest1;
    }
}

