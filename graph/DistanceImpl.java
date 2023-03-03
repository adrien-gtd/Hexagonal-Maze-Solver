package graph;

/**
 * @see graph.Distance.java
 */
public class DistanceImpl implements Distance{
    private final Graph graph;

    public DistanceImpl (Graph graph) {
        this.graph = graph;
    }


    @Override
    public int getDistance (Vertex origineVertex, Vertex finalVertex) {
        return graph.getDistance(origineVertex, finalVertex);
    }
}
