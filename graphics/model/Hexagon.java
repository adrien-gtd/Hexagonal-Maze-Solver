package graphics.model;

import java.awt.*;

import maze.EndBox;
import maze.StartingBox;


public class Hexagon {
    private final Color pathColor = Color.CYAN;
    private final Color emptyColor = Color.GRAY;
    private final int x;
    private final int y;
    private final int size;
    private final Polygon polygon;
    private Color color;
    

    public Hexagon (int x, int y, int size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    } 

    public void setPathColor () {
        if (color == emptyColor)
            color = pathColor;
    }

    public void resetPathColor () {
        if(color == pathColor) 
            color = emptyColor;
    }

    public boolean isStart() {
        return color == StartingBox.color;
    }

    public boolean isEnd() {
        return color == EndBox.color;
    }
}

