package graph;

public interface Distance {
    /**
     * Method used to get the distance between two given vertices in a graph
     * @param origineVertex
     * @param finalVertex
     * @return distance between two vertices
     */
    public int getDistance (Vertex origineVertex, Vertex finalVertex);
}
