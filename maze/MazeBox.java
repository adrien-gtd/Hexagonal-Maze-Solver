package maze;

import graph.Vertex;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

/**
 * A MazeBox object is representing a box of the maze. 
 * The maze can be seen as a graph and each box as a vertex of the graph.
 * This abstract class is also used to factorize code shared by the four classes inheriting this class. 
 */
public abstract class MazeBox implements Vertex {
    /**
     * coordinate of the box in the grid. x is the line coordinate and y is the column one.
     */
    protected final int coordinateX;
    protected final int coordinateY;

    /**
     * unique int given to each vertex, is equale to x * sizeOfLines + y
     */
    protected final int id;

    /**
     * graph the vertex represented by this object is part of
     */
    protected final Maze maze;

    /**
     * each box type has a different color, used in the gui!
     */
    public static Color COLOR;

    
    /**
     * Contrustor used in all the sub classes 
     * @param x x coordinate in the graph
     * @param y y coordinate in the graph
     * @param maze  maze this box is part of
     */
    public MazeBox (int x, int y, Maze maze) {
        this.coordinateX = x;
        this.coordinateY = y;
        this.id = maze.getSizeY() * x + y;
        this.maze = maze;
    }

    /**
     * 
     * @return x coordinate
     */
    public int getX () {
        return this.coordinateX;
    }

    /**
     * 
     * @return y coordinate
     */
    public int getY() {
        return this.coordinateY;
    }


    /**
     * @see graph.Vertex.java
     */
    @Override
    public boolean isEqualTo (Vertex v) {
        if (v.getId() == this.id) 
            return true;
        else
            return false;
    }   

    /**
     * @see graph.Vertex.java
     */
    @Override
    public int getId () {
        return this.id;
    }

    /**
     * @see graph.Vertex.java
     */
    @Override
    public String getLabel() {
        return "Coordonnées (X,Y): " + "(" + Integer.toString(coordinateX) + "," + Integer.toString(coordinateY) + ")" + ". Type: " + getType();
    }

    /**
     * Each box type is represented by a character, this method is used to get this character.
     * The character is only used to save the current maze and in the getLabel above (mainly used for debugging purposes).
     * @return character associated with the box type
     */
    public abstract char getType();


    /**
     * @see graph.Vertex.java
     */
    @Override
    public List<Vertex> getNextVertices () {                            //return a list of vertices next to the current vertex in the graph  
        ArrayList<Vertex> neighborList = new ArrayList<Vertex>();
        int parity = 1;                 //deppending if the line of the box is odd or even, the neighboors are not the same
        if (coordinateX % 2 == 0)
            parity = -1;
        //note that this can easly be replaced by a loop but it makes the code unreadable!!! 
        getNeighbor(coordinateX, coordinateY + 1, neighborList);        //function getNeighbor defined bellow
        getNeighbor(coordinateX, coordinateY - 1, neighborList);
        getNeighbor(coordinateX + 1, coordinateY + parity, neighborList);
        getNeighbor(coordinateX + 1, coordinateY, neighborList);
        getNeighbor(coordinateX - 1, coordinateY + parity, neighborList);
        getNeighbor(coordinateX - 1, coordinateY, neighborList);

        return neighborList;
    }

    /**
     * Used in the method getNexVertices, if the box identified by the coordinates x and y is a neighbor (not wall) of the 
     * current object, this box is added to the list of neighbors
     * @param x x coordinate of the checked box
     * @param y y coordinate of the checked box
     * @param list list of neighbors
     */
    private void getNeighbor(int x,int y, ArrayList<Vertex> list) {
        if(isNeighbor(x, y))                //isNeighbor function defined below
            list.add(maze.getBox(x, y));
        return;
    }
    
    /**
     * Check if the box identified by the coordinates x and y is a neighbors of the current object.
     * @param x x coordinate of the checked box
     * @param y y coordinate of the checked box
     * @return  true if the box is a neighbor, false otherwise
     */
    private boolean isNeighbor (int x, int y) {
        if (maze.isInMaze(x, y))
            if (
                maze.getBox(x, y).isEmptyBox() || 
                maze.getBox(x, y).isEndBox() || 
                maze.getBox(x, y).isStartBox()
            )
                return true;

        return false;
    }

    
    /**
     * Methods implemented in all the classes inheritings this abstarct class. 
     * They are used to identify in a unique way each type of box.
     * Therefore, a Start Box or an End Box is retuning false to the method isEmpty().
     * It is a bit contradicting with the inheritance of the classes but it makes sense to define them this way.
     */
    public abstract boolean isEmptyBox();
    public abstract boolean isWallBox();
    public abstract boolean isStartBox();
    public abstract boolean isEndBox();

    /**
     * Get the attribute COLOR of each class
     * @return the COLOR attribute of the current box type
     */
    public abstract Color getColor();
}
