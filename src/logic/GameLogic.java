package logic;

import config.Config;
import config.GameState;
import javafx.scene.canvas.GraphicsContext;
import object.*;
import object.OBJ;
import object.monster.BlueSlime;
import object.monster.PinkSlime;
import object.monster.Slime;
import object.monster.YellowSlime;
import panel.GamePanel;
import render.RenderableHolder;
import utility.CollisionChecker;
import utility.ObjectSetter;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class GameLogic {
    private int spawnCounter = 0;
    private int slimeCounter = 0;

    //container for entity and object
    private List<OBJ> gameObjectContainer;
    public ObjectSetter objectSetter = new ObjectSetter(this);
    public CollisionChecker collisionChecker = new CollisionChecker(null,this);
    //each
    private Player player;
    public static ArrayList<Entity> slimeList = new ArrayList<>(20);
    private Chest chest1;
    private RenderableHolder renderableHolder = RenderableHolder.getInstance();
    private House house = House.getInstance();
    GamePanel gp = GamePanel.getInstance();
    public GraphicsContext gc = gp.getGraphicsContext2D();


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
    public void removeObject(OBJ object) {
        gameObjectContainer.remove(object);
        renderableHolder.remove(object);
    }

    //fetch
    public void logicUpdate() {
        GameState.update();
        setSpawnCounter(getSpawnCounter() + 1);
        if (GameState.nightState) {
            if (getSlimeCounter() < Config.day * 2 && getSpawnCounter() > 120) {
                setSpawnCounter(0);
                addSlime();
            }
        }
        if (!GameState.nightState) {
            clearSlime();
        }
        System.out.println("number of slime : " + getSlimeCounter());
        System.out.println("slimeList : " + slimeList.size());
        for(OBJ e:gameObjectContainer) {
            if (e instanceof Entity ee) {
                if (((Entity) e).isAlive()) {
                    ee.update();
                }
            } else if(e instanceof Item ee) {
                ee.update();
            }else {
                System.out.println("dunno this instance");
            }
        }
        int objIndex = collisionChecker.checkObject(player,true);
        pickUpObject(objIndex);
        slimeCheck();
        house.update();
        boolean contactPlayer, contactHouse;
        for (Entity e : slimeList) {
            collisionChecker.checkSlime(e, slimeList);
            contactPlayer = collisionChecker.checkPlayer(e);
            contactHouse = collisionChecker.checkHouse(e, house);
            if (contactPlayer && !player.isInvincible()) {
                player.setLife(player.getLife() - e.getAttack());
                player.setInvincible(true);
            }
            if (contactHouse && !house.isInvincible()) {
                house.setLife(house.getLife() - e.getAttack());
                house.setInvincible(true);
            }
        }
    }
    public void slimeCheck() {
        try {
            for (Entity e : slimeList) {
                if (e.getLife() <= 0) {
                    e.setDying(true);
                }
                if (!e.isAlive()) {
                    removeObject(e);
                    slimeList.remove(e);
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println(e);
        }
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

    public ArrayList<Entity> getSlimeList() {
        return slimeList;
    }

    //
    public void addSlime() {
        if (Config.day < 5) {
            if (getSlimeCounter()%4 == 0) {
                slimeList.add(new BlueSlime(25, 10));
                addNewObject(slimeList.get(getSlimeCounter()));
            } else if (getSlimeCounter()%4 == 1) {
                slimeList.add(new BlueSlime(50, 10));
                addNewObject(slimeList.get(getSlimeCounter()));
            } else if (getSlimeCounter()%4 == 2) {
                slimeList.add(new BlueSlime(25, 50));
                addNewObject(slimeList.get(getSlimeCounter()));
            } else if (getSlimeCounter()%4 == 3) {
                slimeList.add(new BlueSlime(10, 25));
                addNewObject(slimeList.get(getSlimeCounter()));
            }
        } else if (Config.day >= 5 && Config.day < 10) {
            if (getSlimeCounter() % 4 == 0) {
                slimeList.add(new YellowSlime(25, 10));
                addNewObject(slimeList.get(getSlimeCounter()));
            } else if (getSlimeCounter() % 4 == 1) {
                slimeList.add(new YellowSlime(50, 10));
                addNewObject(slimeList.get(getSlimeCounter()));
            } else if (getSlimeCounter() % 4 == 2) {
                slimeList.add(new YellowSlime(25, 50));
                addNewObject(slimeList.get(getSlimeCounter()));
            } else if (getSlimeCounter() % 4 == 3) {
                slimeList.add(new YellowSlime(10, 25));
                addNewObject(slimeList.get(getSlimeCounter()));
            }
        } else if (Config.day >= 10) {
            if (getSlimeCounter() % 4 == 0) {
                slimeList.add(new PinkSlime(25, 10));
                addNewObject(slimeList.get(getSlimeCounter()));
            } else if (getSlimeCounter() % 4 == 1) {
                slimeList.add(new PinkSlime(50, 10));
                addNewObject(slimeList.get(getSlimeCounter()));
            } else if (getSlimeCounter() % 4 == 2) {
                slimeList.add(new PinkSlime(25, 50));
                addNewObject(slimeList.get(getSlimeCounter()));
            } else if (getSlimeCounter() % 4 == 3) {
                slimeList.add(new PinkSlime(10, 25));
                addNewObject(slimeList.get(getSlimeCounter()));
            }
        }
        setSlimeCounter(getSlimeCounter() + 1);
    }

    public void clearSlime() {
        if (slimeList.size() > 0) {
            for (int i = 0; i < slimeList.size(); i++) {
                removeObject(slimeList.get(i));
            }
            slimeList.clear();
            setSlimeCounter(0);
        }
    }

    public int getSpawnCounter() {
        return spawnCounter;
    }

    public void setSpawnCounter(int spawnCounter) {
        this.spawnCounter = spawnCounter;
    }

    public int getSlimeCounter() {
        return slimeCounter;
    }

    public void setSlimeCounter(int slimeCounter) {
        this.slimeCounter = slimeCounter;
    }
}

