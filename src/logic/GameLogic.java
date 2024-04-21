package logic;

import config.Config;
import config.GameState;
import object.*;
import object.OBJ;
import object.monster.Slime;
import render.RenderableHolder;
import utility.CollisionChecker;
import utility.ObjectSetter;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    //container for entity and object
    private List<OBJ> gameObjectContainer;
    public ObjectSetter objectSetter = new ObjectSetter(this);
    public CollisionChecker collisionChecker = new CollisionChecker(null,this);
    //each
    private Player player;
    public static ArrayList<Slime> slimeList = new ArrayList<>(20);
    private Chest chest1;
    private RenderableHolder renderableHolder = RenderableHolder.getInstance();

    //constructor(setup for all entity)
    public GameLogic() {
        gameObjectContainer = new ArrayList<>();
        player = Player.getInstance();
        chest1 = new Chest(23+ Config.fixedPosition,7+ Config.fixedPosition);
        addNewObject(player);
        addNewObject(chest1);
        setupGame();
    }
    private void setupGame() {
        objectSetter.setObject();
    }


    //function for add object to container
    public void addNewObject(OBJ object) {
        gameObjectContainer.add(object);
        renderableHolder.add(object);
    }

    //fetch
    public void logicUpdate() {
        GameState.update();
        for(OBJ e:gameObjectContainer) {
            if (e instanceof Entity ee) {
                ee.update();
            } else if(e instanceof Item ee) {
                ee.update();
            }else {
                System.out.println("dunno this instance");
            }
        }
        int objIndex = collisionChecker.checkObject(player,true);
        pickUpObject(objIndex);
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            if(!((Item) getGameObjectContainer().get(i)).isCollision()){
                player.getInventory().add((Item) getGameObjectContainer().get(i));
                renderableHolder.getObjects().remove(getGameObjectContainer().get(i));
                getGameObjectContainer().remove(i);
            }
        }
    }

    //getter
    public Player getPlayer() {
        return player;
    }

    public List<OBJ> getGameObjectContainer() {
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

    public ArrayList<Slime> getSlimeList() {
        return slimeList;
    }

    //
    public void addSlime() {
        Slime slime1 = new Slime(20, 23);
        Slime slime2 = new Slime(30, 37);
        slimeList.add(slime1);
        slimeList.add(slime2);
        addNewObject(slime1);
        addNewObject(slime2);
    }
}

