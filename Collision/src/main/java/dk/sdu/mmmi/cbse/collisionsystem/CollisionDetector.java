package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetector implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            for (Entity collisionDetection : world.getEntities()) {
                LifePart entityLife = entity.getPart(LifePart.class);
                if (entity.getID().equals(collisionDetection.getID())) {
                    continue;
                }
                if (this.collides(entity, collisionDetection)) {
                    if (entityLife.getLife() > 0 && entityLife.getIFrames() <= 0) {
                        entityLife.setIsHit(true);
                        //Lisp programming be like:
                        ((LifePart)(collisionDetection.getPart(LifePart.class))).setIsHit(true);
                    }
                }
            }
        }
    }

    public Boolean collides(Entity entity, Entity entity2) {
        PositionPart entMov = entity.getPart(PositionPart.class);
        PositionPart entMov2 = entity2.getPart(PositionPart.class);
        double dx = entMov.getX() - entMov2.getX();
        double dy = entMov.getY() - entMov2.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance < (entity.getRadius() + entity2.getRadius());
    }

}
