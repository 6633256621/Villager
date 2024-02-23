package ent;
import panel.GamePanel;
import render.RenderableHolder;
import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private List<Entity> gameObjectContainer;//container for all object
    private Player player;
    public static GameLogic instance;

    //constructor(setup for all entity)
    public GameLogic() {
        gameObjectContainer =new ArrayList<>();
        player = Player.getInstance();
        addNewObject(player);
    }

    //function for add object to container
    private void addNewObject(Entity entity) {
        gameObjectContainer.add(entity);
        RenderableHolder.getInstance().add(entity);
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

