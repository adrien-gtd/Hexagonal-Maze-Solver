package graphics.model;

import java.awt.*;

import maze.EndBox;
import maze.StartingBox;
import maze.EmptyBox;

/**
 * This class is disigned to contain all the data necessary to draw the hexagons on the screen.
 */
public class Hexagon {

    /**
     * Colors used to display the path or remove the path
     */
    private final Color pathColor = Color.CYAN;
    private final Color emptyColor = EmptyBox.COLOR;

    /**
     * Coordinate (in pixels) of the center of the hexagon
     */
    private int x;
    private int y;

    /**
     * size of a side of the hexagon (or of a diagonal)
     */
    private final int size;

    /**
     * class containing the coordonates of each vertices of the hexagon (in pixel)
     */
    private final Polygon polygon;

    /**
     * current color of the hexagon
     */
    private Color color;

    
    /**
     * 
     * @param x x coordinate of the center of the hexagon
     * @param y y coordinate of the center of the hexagon
     * @param size desired size for the hexagon
     * @param color default color of the hexagon
     */
    public Hexagon (int x, int y, int size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
        this.polygon = createHexagon();
    }

    /**
     * private method used for the contructor above. 
     * @return  the polygon containing the coordinates of the vertices
     * of the hexagon (in pixel).
     */
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

    /* ----------------- start setters / getters ----------------- */
    /**
     * 
     * @return the polygon
     */
    public Polygon getPolygon() {
        return polygon;
    }

    /**
     * 
     * @return x coordinate of the center (in pixel)
     */
    public int getX(){
        return x;
    }

    /**
     * 
     * @return y coordinate of the center (in pixel)
     */
    public int getY() {
        return y;
    }

    /**
     * 
     * @return size of the hexagon
     */
    public int getSize() {
        return size;
    }

    /**
     * 
     * @return current color of the hexagon
     */
    public Color getColor() {
        return color;
    }

    /**
     * Change the current color of the hexagon
     * @param color new color
     */
    public void setColor(Color color) {
        this.color = color;
    } 
    /* ----------------- end setters / getters ----------------- */


    /**
     * used to add the path to the maze
     */
    public void setPathColor () {
        if (color == emptyColor)
            color = pathColor;
    }

    /**
     * used to remove the path from the maze
     */
    public void resetPathColor () {
        if(color == pathColor) 
            color = emptyColor;
    }

    /**
     * Uesed to determine if an hexagon is representing the starting box of the maze
     * @return  true if this hexagon is a representing the starting box
     *          false otherwise
     */
    public boolean isStart() {
        return color == StartingBox.COLOR;
    }

    /**
     * Uesed to determine if an hexagon is representing the ending box of the maze
     * @return  true if this hexagon is a representing the ending box
     *          false otherwise
     */
    public boolean isEnd() {
        return color == EndBox.COLOR;
    }

    /**
     * Used for generating the hexagon list 
     * @see graphics.model.HexagonList.java
     * Offset the current hexagon by the dimension passed as a parameter
     * @param offset offset to apply on the hexagon
     */
    public void applyOffset(Dimension offset) {
        x += (int) offset.getWidth();
        y += (int) offset.getHeight();
        polygon.translate((int) offset.getWidth(), (int) offset.getHeight());
    }
}

