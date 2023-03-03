package graph;

import java.util.List;

/**
 * Classes implementing this interface are representing the shortest paths 
 * between a given vertex and some other vertices of the graph 
 * (different from the minDistance in the way that a path added is THE shortest path, 
 * no path between the two vertices is shorter)
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
