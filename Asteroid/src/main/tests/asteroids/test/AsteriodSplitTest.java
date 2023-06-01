package asteroids.test;

import static org.mockito.BDDMockito.*;


import dk.sdu.mmmi.cbse.asteroid.AsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import org.junit.Test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class AsteriodSplitTest {
    @Mock
    private World world;
    @Mock
    private static Entity entity = new Entity();
    private AsteroidSplitter asteroidSplitter = new AsteroidSplitter();
    @Test
    public void asteroidShouldSplit(){
        doReturn(mock(PositionPart.class)).when(entity).getPart(PositionPart.class);
        doReturn(mock(MovingPart.class)).when(entity).getPart(MovingPart.class);

        doReturn(new LifePart(1)).when(entity).getPart(LifePart.class);

        asteroidSplitter.createSplitAsteroid(entity,world);

        verify(world).addEntity(any(Entity.class));
    }
    @Test
    public void asteroidShouldNotSplit(){
        doReturn(mock(PositionPart.class)).when(entity).getPart(PositionPart.class);
        doReturn(mock(MovingPart.class)).when(entity).getPart(MovingPart.class);

        doReturn(new LifePart(0)).when(entity).getPart(LifePart.class);

        asteroidSplitter.createSplitAsteroid(entity,world);

        verify(world, never()).addEntity(any(Entity.class));
    }
}