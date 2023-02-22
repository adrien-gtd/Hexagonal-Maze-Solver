package graphics.content;

import java.awt.*;

public class Hexagon {
    private final int x;
    private final int y;
    private final int size;
    private final Polygon polygon;

    public Hexagon (int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.polygon = createHexagon();
    }

    private Polygon createHexagon() {
        Polygon polygon = new Polygon();

        for (int i = 0; i < 6; i++) {
            int yval = (int) (y + size
                    * Math.cos(i * 2 * Math.PI / 6D));
            int xval = (int) (x + size
                    * Math.sin(i * 2 * Math.PI / 6D));
            polygon.addPoint(xval, yval);
        }

        return polygon;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }
}

