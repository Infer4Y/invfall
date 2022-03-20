package inferno.saigo.common.init;

import inferno.saigo.common.entities.Entity;
import inferno.saigo.common.entities.EntityProjectile;

import java.util.HashMap;

public class Entities {
    public static final HashMap<String, Entity> ENTITY_MAP = new HashMap<>();
    public static final Entity PLAYER = new Entity("player");
    public static final EntityProjectile RAY = new EntityProjectile("ray");

    public static void init(){
        PLAYER.setMaxAge(Integer.MAX_VALUE);
        RAY.setMaxAge(600);
        register(PLAYER, RAY);
    }

    public static void register(Entity... entities){
        for (Entity entity: entities) {
            register(entity);
        }
    }

    public static void register(Entity entity){
        ENTITY_MAP.put(entity.toString(), entity);
    }

    public static HashMap<String, Entity> getEntities(){
        return ENTITY_MAP;
    }
}
