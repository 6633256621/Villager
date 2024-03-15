package logic;

import object.Chest;
import object.Object;
import object.Player;
import object.potion.HealthPotion;
import render.RenderableHolder;
import utility.CollisionChecker;
import utility.ObjectSetter;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    //container for entity and object
    private List<Object> gameObjectContainer;
    public ObjectSetter objectSetter = new ObjectSetter(this);
    public CollisionChecker collisionChecker = new CollisionChecker(null,this);
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
        setupGame();
    }
    private void setupGame() {
        objectSetter.setObject();
    }

    //function for add object to container
    public void addNewObject(Object object) {
        gameObjectContainer.add(object);
        RenderableHolder.getInstance().add(object);
    }

    //fetch
    public void logicUpdate() {
        player.update();
        collisionChecker.checkObject(player,true);
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
    public static GameLogic instance;
    public static GameLogic getInstance() {
        instance = new GameLogic();
        return instance;
    }
}

