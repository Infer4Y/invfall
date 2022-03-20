package inferno.saigo.client.rendering;

import inferno.saigo.client.utils.display.DisplayReference;
import inferno.saigo.common.entities.EntityData;
import inferno.saigo.common.entities.EntityProjectile;

import java.awt.*;

public class ObjectRenderingEntityProjectile extends ObjectRenderingEntity {
    public ObjectRenderingEntityProjectile(EntityData data) {
        super(data);
    }

    @Override
    public void render(Graphics2D graphics, int tileSize) {
        try {
            EntityProjectile.EntityProjectileData temp = (EntityProjectile.EntityProjectileData) DisplayReference.world.getEntity(data.uuid);
            if (temp != null) {
                this.data = temp;
            } else {
                this.remove = true;
            }
        } catch (Exception ignored){}

        float age = this.data.age, newX = (this.getX() * tileSize - (tileSize >> 1)), newY = (this.getY() * tileSize - (tileSize >> 1));

        graphics.translate((int)newX, (int)newY);

        graphics.rotate(Math.toRadians(age), tileSize>>1, tileSize>>1);

        //graphics.drawImage(texture.getImage(),(int)(getX()) * tileSize - (tileSize >> 1), (int)(getY()) * tileSize - (tileSize >> 1), tileSize, tileSize,null);
        graphics.drawImage(super.getTexture().getImage(),0, 0, tileSize, tileSize,null);

        graphics.rotate(-Math.toRadians(age), tileSize>>1, tileSize>>1);

        graphics.translate(-(int)newX, -(int)newY);

    }


}
