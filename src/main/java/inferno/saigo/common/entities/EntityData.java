package inferno.saigo.common.entities;

import inferno.saigo.common.maps.MapWorld;

import java.awt.*;
import java.util.UUID;

public class EntityData {
    public Entity entity;
    public UUID uuid;
    public float x, y, age;
    public Shape bounds;

    public void update(MapWorld world){
        if (shouldDie()){
            onDeath(world);
        }
        age++;
    }

    @Override
    public String toString() {
        return "EntityData[" +
                "entity=" + entity +
                ", uuid="+ uuid +
                ", x=" + x +
                ", y=" + y +
                ", age=" + age +
                ']';
    }

    public Entity getEntity() {
        return entity;
    }

    public EntityData setEntity(Entity entity) {
        this.entity = entity;
        return this;
    }

    public UUID getUuid() {
        return uuid;
    }

    public EntityData setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public float getX() {
        return x;
    }

    public EntityData setX(float x) {
        this.x = x;
        return this;
    }

    public float getY() {
        return y;
    }

    public EntityData setY(float y) {
        this.y = y;
        return this;
    }

    public float getAge() {
        return age;
    }

    public EntityData setAge(float age) {
        this.age = age;
        return this;
    }

    public Shape getBounds() {
        return bounds;
    }

    public EntityData setBounds(Shape bounds) {
        this.bounds = bounds;

        return this;
    }

    public void onCollision(EntityData data){
        entity.onCollision(data);
    }

    public boolean shouldDie(){
        return age > entity.getMaxAge();
    }

    public void onDeath(MapWorld world){
        world.removeEntity(this);
    }
}
