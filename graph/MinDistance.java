package graph;

/**
 * Used in path finding algorithms, is updated during the exectution to contain the minimal distance between the starting 
 * vertex of the algorithm and the other at a given state.
 */
public interface MinDistance {

    /**
     * Get the minimal distance between the origin vertex of the algorithm and a vertex
     * passed as a parameter.
     */
    public int getMinDist (Vertex finalVertex);

    /**
     * set the minimal distance between the origin and the vertex passed as a parameter
     * @param finalVertex vertex we want to assign the new miniaml value
     * @param minDist value of the new minimal distance
     */
    public void updateMinDist (Vertex finalVertex, int minDist);

    /**
     * Get the next pivot for the dijsktrat algorithm (used to remove some code from the dijkstra class)
     * @param pivot current pivot
     * @param processedVertexes objext containing the processed vertices at the current state of the algorithm
     * @param graph graph the algorithm is used on
     * @return  the next pivot for the algorithm
     */
    public Vertex getNextPivot (Vertex pivot, ProcessedVertexes processedVertexes, Graph graph);
}
