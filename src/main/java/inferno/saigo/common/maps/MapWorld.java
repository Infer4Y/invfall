package inferno.saigo.common.maps;

import inferno.saigo.common.entities.EntityData;

import java.util.Arrays;
import java.util.LinkedList;

public class MapWorld {
    private Map map;
    private final LinkedList<EntityData> entityList = new LinkedList<>();
    private LinkedList<EntityData> entityDataList = new LinkedList<>();

    public void update(){
        entityList.forEach(entity -> entity.update(this));
        entityList.forEach(entity -> {
            entityDataList = new LinkedList<>(entityList);
            entityList.stream().filter(targetEntity -> {
                entityDataList.remove(entity);
                return entity.getBounds().intersects(targetEntity.getBounds().getBounds());
            }).forEach( collided -> collided.onCollision(entity));
        });
    }

    public Map getMap() {
        return map;
    }

    public MapWorld setMap(Map map) {
        this.map = map;
        return this;
    }

    public void removeEntity(EntityData entityData){
        entityList.remove(entityData);
    }

    public LinkedList<EntityData> getEntities() {
        return entityList;
    }
}
