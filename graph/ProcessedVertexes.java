package graph;

/**
 * Classes implementing this interface represent the set of vertices processed by the algorith at a given state
 */
public interface ProcessedVertexes {
    
    /**
     * Mark a vertex as processed for the algorithm
     * @param v vertex marked
     */
    public void markProcessed (Vertex v);

    /**
     * Check if a vertex is already processed
     * @param v vertex checked
     * @return true if the vertex is already processed, false otherwise 
     */
    public boolean isProcessed (Vertex v);

}
