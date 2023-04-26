package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.data.entityparts.EntityPart;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();

    public Color baseColor = Color.AZURE;

    public Color currentColor = this.baseColor;

    public Color iFrameColor = Color.GOLD;

    private double[] shapeX = new double[4];
    private double[] shapeY = new double[4];
    private double radius;
    private Map<Class, EntityPart> parts;

    public Entity() {
        parts = new ConcurrentHashMap<>();
    }

    public void add(EntityPart part) {
        parts.put(part.getClass(), part);

    }

    public void remove(Class partClass) {
        parts.remove(partClass);
    }

    public <E extends EntityPart> E getPart(Class partClass) {
        return (E) parts.get(partClass);
    }

    public void setRadius(double r) {
        this.radius = r;
    }

    public double getRadius() {
        return radius;
    }

    public String getID() {
        return ID.toString();
    }

    public double[] getShapeX() {
        return shapeX;
    }

    public void setShapeX(double[] shapeX) {
        this.shapeX = shapeX;
    }

    public double[] getShapeY() {
        return shapeY;
    }

    public void setShapeY(double[] shapeY) {
        this.shapeY = shapeY;
    }

}
