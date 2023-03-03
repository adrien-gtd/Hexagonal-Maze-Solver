package graph;
import java.util.List;

/**
 * Interface implemented by classes representing graphs. 
 */
public interface Graph{
    
    /**
     * Get the vertices of the graph
     * @return return a list of all the vertices composing the graph
     */
    public List<Vertex> getVertices ();

    /**
     * Get the distance between two given vertices, return -1 if not connected 
     * @param origineVertex
     * @param finalVertex 
     * @return distance between originVertex and finalVertex
     */
    public int getDistance (Vertex origineVertex, Vertex finalVertex);
}
