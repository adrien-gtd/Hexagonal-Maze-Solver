import graph.*;

import java.util.ArrayList;
import java.util.List;

public final class Maze implements Graph{
    private final int sizeX;                        //size X of the maze
    private final int sizeY;                        //size Y of the maze

    private MazeBox[][] grid;                       //contains the grid with all the boxes of the maze

    public Maze (int sizeX, int sizeY) {            //Constructor
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public int getSizeX () {            //return the size X of the maze
        return sizeX;
    }

    public int getSizeY () {            //return the size Y of the maze
        return sizeY;
    }
    
    public boolean isInMaze(int x, int y) {            //check if the coordonates of the vertex v are in the maze
        if (x < sizeX && y < sizeY)
            return true;
        else
            return false;
    }

    public MazeBox getBox (int x, int y) {
        return grid[x][y];
    }
    
    @Override
    public List<Vertex> getVertices () {                //return all the vertices of the graph
        ArrayList<Vertex> VerticesList = new ArrayList<Vertex>();
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; i++) {
                VerticesList.add(grid[i][j]);
            }
        }
        return VerticesList;
    }
}