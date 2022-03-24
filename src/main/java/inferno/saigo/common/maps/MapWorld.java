package inferno.saigo.common.maps;

import inferno.saigo.common.entities.EntityData;
import inferno.saigo.common.tiles.TileData;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MapWorld {
    private Map map;
    private final ConcurrentHashMap<UUID,EntityData> entityList = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Point, ArrayList<UUID>> entityListLocation = new ConcurrentHashMap<>();

    public synchronized void update(){
        entityListLocation.clear();

        entityList.values().forEach(current -> {
            Point  locationOfCurrent = new Point((int) current.x, (int) current.y);
            entityListLocation.computeIfAbsent(locationOfCurrent, k -> new ArrayList<>());
            entityListLocation.get(locationOfCurrent).add(current.uuid);
        });

        entityList.values().forEach(current -> current.update(this));

        map.tiles.forEach(data -> data.update(this));
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public synchronized void removeEntity(EntityData entityData){
        entityList.remove(entityData.uuid);
    }

    public synchronized ConcurrentHashMap<UUID, EntityData> getEntities() {
        return entityList;
    }

    public synchronized EntityData getEntity(UUID uuid){
        return entityList.get(uuid);
    }

    public synchronized ArrayList<UUID> getEntities(Point point){
        return entityListLocation.get(point);
    }

    public synchronized void addEntity(EntityData entityData) {
        entityList.put(entityData.uuid, entityData);
    }
}
