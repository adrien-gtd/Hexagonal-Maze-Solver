package graph;

import java.util.List;

/**
 * Classes implementing this interface allows to keep track of the 
 * shortest paths created during the execution of the algorithm
 */
public interface ShortestPaths {

    /**
     * update the origin of the vertex 'v' in the shortest path
     * @param v
     * @param origin
     */
    public void updatePrev (Vertex v, Vertex origin);

    /**
     * get the shortest path from the start to the end
     * @param endVertex
     * @param startVertex
     * @return
     */
    public List<Vertex> getShortestPath( Vertex endVertex, Vertex startVertex);

    /**
     * Used to debug the code, print on the standard output stream the content of the data structure
     */
    public void printShotestpath();
}
