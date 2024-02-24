package render;

import interfacepackage.Renderable;

import java.util.*;

public class RenderableHolder {
    private ArrayList<Renderable> entities;
    private Comparator<Renderable> comparator;

    //singleton method
    private static final RenderableHolder instance = new RenderableHolder();

    //constructor
    public RenderableHolder() {
        //init
        entities = new ArrayList<Renderable>();
        //adjust comparator
        comparator = (Renderable o1, Renderable o2) -> {
            if (o1.getZ() > o2.getZ()) {
                return 1;
            }
            return -1;
        };
    }

    //add entity to array and sort with z
    public void add(Renderable entity) {
        entities.add(entity);
        Collections.sort(entities, comparator);
    }

    //singleton method
    public static RenderableHolder getInstance() {
        return instance;
    }


    //getter
    public ArrayList<Renderable> getEntities() {
        return entities;
    }


    //fetch
    public void update() {
        for (int i = entities.size() - 1; i >= 0; i--) {
            if (entities.get(i).isDestroyed())
                entities.remove(i);
        }
    }
}
