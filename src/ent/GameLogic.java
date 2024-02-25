package ent;
import object.Chest;
import object.SuperObject;
import panel.GamePanel;
import render.RenderableHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameLogic {
    private List<Entity> gameEntityContainer;//container for all entity
    private List<SuperObject> gameObjectContainer;//container for all object
    private Player player;
    private Chest chest1;
    public static GameLogic instance;

    //constructor(setup for all entity)
    public GameLogic() {
        gameEntityContainer =new ArrayList<>();
        gameObjectContainer = new ArrayList<>();
        player = Player.getInstance();
        chest1 = new Chest(23,7);
        addNewEntity(player);
        addNewObject(chest1);
    }

    //function for add object to container
    private void addNewEntity(Entity entity) {
        gameEntityContainer.add(entity);
        RenderableHolder.getInstance().add(entity);
    }
    private void addNewObject(SuperObject object) {
        gameObjectContainer.add(object);
        RenderableHolder.getInstance().add(object);
    }

    //fetch
    public void logicUpdate() {
        player.update();
    }
    public static GameLogic getInstance() {
        if (instance==null) {
            instance= new GameLogic();
        }
        return instance;
    }

    public Player getPlayer() {
        return player;
    }
}

