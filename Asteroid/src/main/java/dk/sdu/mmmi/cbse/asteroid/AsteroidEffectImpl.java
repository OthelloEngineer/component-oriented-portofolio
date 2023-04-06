package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidEffect;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

   
public class AsteroidEffectImpl implements IAsteroidEffect {

    @Override
    public void createSplitAsteroid(Entity e, World world) {
        PositionPart otherPos = e.getPart(PositionPart.class);
        LifePart otherLife = e.getPart(LifePart.class);
        MovingPart otherMove = e.getPart(MovingPart.class);

        double radians = otherPos.getRadians();
        if (otherLife.getLife() <= 0){
            world.removeEntity(e);
            return;
        }

        Entity asteroid1 = new Asteroid();

        double newRadius = e.getRadius()/2;
        PositionPart positionPart = newAsteroidPositions(otherPos, newRadius);


        e.setRadius(newRadius);
        asteroid1.setRadius(newRadius);
        double speed = otherMove.getMaxSpeed()/2;
        asteroid1.setRadius(newRadius);
        otherMove.setMaxSpeed(speed);

        asteroid1.add(new MovingPart(0, 5000, speed, 0));
        asteroid1.add(positionPart);
        asteroid1.add(new LifePart(otherLife.getLife(), 5));

        world.addEntity(asteroid1);
    }

    private PositionPart newAsteroidPositions(PositionPart oldPosition, double newRadius){
        double newRadians = oldPosition.getRadians()-0.5;
        double by1 = sin(newRadians) * newRadius;
        double bx1 = cos(newRadians) * newRadius;
        return new PositionPart(oldPosition.getX() + bx1,
                oldPosition.getY() + by1,
                newRadians);
    }



}
