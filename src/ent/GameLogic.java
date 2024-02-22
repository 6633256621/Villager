package ent;
import render.RenderableHolder;
import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private List<Entity> gameObjectContainer;//container for all object
    private Player player;

    //constructor(setup for all entity)
    public GameLogic() {
        gameObjectContainer =new ArrayList<>();
        player = new Player();
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
}
