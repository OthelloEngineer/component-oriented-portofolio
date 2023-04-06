package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidEffect;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class AsteroidProcessor implements IEntityProcessingService {

    private IAsteroidEffect asteroidSplitter = new AsteroidEffectImpl();

    @Override
    public void process(GameData gameData, World world) {

        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);
            LifePart lifePart = asteroid.getPart(LifePart.class);
            int numPoints = 12;
            float baseSpeed = 30;
            double speed = baseSpeed*(6-lifePart.getLife());
            movingPart.setSpeed(speed);
            movingPart.setUp(true);

            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);
            lifePart.process(gameData, asteroid);
            // Split event
            if (lifePart.isHit()) {
                asteroidSplitter.createSplitAsteroid(asteroid, world);
            }
            lifePart.setIsHit(false);
            setShape(asteroid, numPoints);
        }
    }
    public void setAsteroidSplitter(IAsteroidEffect asteroidSplitter) {
        this.asteroidSplitter = asteroidSplitter;
    }

    public void removeAsteroidSplitter(IAsteroidEffect asteroidSplitter) {
        this.asteroidSplitter = null;
    }

    private void setShape(Entity entity, int numPoints) {
        PositionPart position = entity.getPart(PositionPart.class);
        double[] shapex = new double[numPoints];
        double[] shapey = new double[numPoints];
        double radians = position.getRadians();
        double x = position.getX();
        double y = position.getY();
        double radius = entity.getRadius();

        float angle = 0;

        for (int i = 0; i < numPoints; i++) {
            shapex[i] = x + Math.cos(angle + radians) * radius;
            shapey[i] = y + Math.sin(angle + radians) * radius;
            angle += 2 * 3.1415f / numPoints;
        }

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
