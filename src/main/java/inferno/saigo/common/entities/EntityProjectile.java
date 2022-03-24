package inferno.saigo.common.entities;

import inferno.saigo.client.utils.display.DisplayReference;
import inferno.saigo.common.maps.MapWorld;

import java.awt.*;
import java.util.Iterator;
import java.util.UUID;

public class EntityProjectile extends Entity{

    public EntityProjectile(String registryName) {
        super(registryName);
    }

    @Override
    public void onUpdate(MapWorld world, EntityData self) {
        super.onUpdate(world, self);

        self.x += ((EntityProjectileData)self).motionX;
        self.y += ((EntityProjectileData)self).motionY;

    }

    @Override
    public void onCollision(MapWorld world, EntityData target) {
        super.onCollision(world, target);

        target.age += 1000;
    }

    public EntityProjectileData createProjectile(float x, float y, float motionX, float motionY, Entity entity){
        return new EntityProjectileData(x, y, motionX, motionY, entity);
    }

    public static class EntityProjectileData extends EntityData {
        private float motionX, motionY;

        public EntityProjectileData(float x, float y, float motionX, float motionY, Entity entity){
            this.uuid = UUID.randomUUID();
            this.setEntity(entity);
            this.setX(x);
            this.setY(y);
            this.motionX = motionX;
            this.motionY = motionY;
            super.bounds = new Rectangle((int) x, (int) y, DisplayReference.renderer.tileSize, DisplayReference.renderer.tileSize);
        }

        @Override
        public void update(MapWorld world) {
            entity.onUpdate(world, this);
            if (shouldDie()){
                onDeath(world);
            }
            age++;
        }

        public float getMotionX() {
            return motionX;
        }

        public void setMotionX(float motionX) {
            this.motionX = motionX;
        }

        public float getMotionY() {
            return motionY;
        }

        public void setMotionY(float motionY) {
            this.motionY = motionY;
        }
    }
}
