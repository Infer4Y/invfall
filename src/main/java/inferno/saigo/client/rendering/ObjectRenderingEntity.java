package inferno.saigo.client.rendering;

import inferno.saigo.client.assets.objects.Texture;
import inferno.saigo.client.assets.collections.Textures;
import inferno.saigo.client.utils.display.DisplayReference;
import inferno.saigo.common.entities.EntityData;
import inferno.saigo.common.entities.EntityProjectile;

import java.awt.*;

public class ObjectRenderingEntity extends ObjectRenderingCoord {
    protected final Texture texture;
    protected EntityData data;

    public ObjectRenderingEntity(EntityData data) {
        super(data.x, data.y);
        this.data = data;
        this.texture = Textures.getTexture(data.entity.toString());
    }

    @Override
    public void render(Graphics2D graphics, int tileSize) {
        EntityProjectile.EntityProjectileData temp = (EntityProjectile.EntityProjectileData) DisplayReference.world.getEntity(data.uuid);
        if (temp != null) {
            this.data = temp;
        } else {
            this.remove = true;
        }

        graphics.translate((int)(getY()) * tileSize - (tileSize >> 1), (int)(getX()) * tileSize - (tileSize >> 1));

        //graphics.drawImage(texture.getImage(),(int)(getX()) * tileSize - (tileSize >> 1), (int)(getY()) * tileSize - (tileSize >> 1), tileSize, tileSize,null);
        graphics.drawImage(texture.getImage(),0, 0, tileSize, tileSize,null);

        graphics.translate(-(int)(getY()) * tileSize + (tileSize >> 1), -(int)(getX()) * tileSize + (tileSize >> 1));
    }

    @Override
    public float getX() {
        return data.getX();
    }

    @Override
    public float getY() {
        return data.getY();
    }

    public Texture getTexture() {
        return texture;
    }
}
