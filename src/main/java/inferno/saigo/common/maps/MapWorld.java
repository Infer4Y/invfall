package inferno.saigo.common.maps;

import inferno.saigo.common.entities.EntityData;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MapWorld {
    private Map map;
    private final ConcurrentHashMap<UUID,EntityData> entityList = new ConcurrentHashMap<>();

    public synchronized void update(){
        entityList.values().forEach(current -> current.update(this));
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

    public synchronized void addEntity(EntityData entityData) {
        entityList.put(entityData.uuid, entityData);
    }
}
