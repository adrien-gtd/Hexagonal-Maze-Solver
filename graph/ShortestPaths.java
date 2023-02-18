package graph;

import java.util.List;

public interface ShortestPaths {

    public void updatePrev (Vertex v, Vertex origin);               //update the origin of the vertex 'v' in the shortest path 
    public List<Vertex> getShortestPath( Vertex endVertex, Vertex startVertex);               //get the shortest path from the start to the end
    public void printShotestpath();                                 //used to decbug the code
}
