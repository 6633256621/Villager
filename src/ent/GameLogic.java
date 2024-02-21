package ent;
import render.RenderableHolder;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private List<Entity> gameObjectContainer;
    private Player player;
    public GameLogic() {
        gameObjectContainer =new ArrayList<>();
        player = new Player();
        addNewObject(player);
    }
    protected void addNewObject(Entity entity) {
        gameObjectContainer.add(entity);
        RenderableHolder.getInstance().add(entity);
    }
    public void logicUpdate() {
        player.update();
    }
}
