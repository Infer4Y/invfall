package inferno.saigo.common.entities;

public class EntityProjectile extends Entity{
    public EntityProjectile(String registryName) {
        super(registryName);
    }

    @Override
    public void onCollision(EntityData target) {
        super.onCollision(target);

        target.age += 100;
    }
}
