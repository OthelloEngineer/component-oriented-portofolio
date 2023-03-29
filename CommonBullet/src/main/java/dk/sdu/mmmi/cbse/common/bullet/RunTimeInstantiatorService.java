package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;

public interface RunTimeInstantiatorService {
    Entity spawn(PositionPart e, GameData gameData);
}
