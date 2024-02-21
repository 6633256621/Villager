package render;

import java.util.*;

public class RenderableHolder {
    private ArrayList<Renderable> entities;
    private Comparator<Renderable> comparator;
    private static final RenderableHolder instance = new RenderableHolder();

    public RenderableHolder() {
        entities = new ArrayList<Renderable>();
        comparator = (Renderable o1, Renderable o2) -> {
            if (o1.getZ() > o2.getZ()) {
                return 1;
            } return -1;
        };
    }

    public void add(Renderable entity) {
        entities.add(entity);
        Collections.sort(entities,comparator);
    }

    public static RenderableHolder getInstance() {
        return instance;
    }
}
