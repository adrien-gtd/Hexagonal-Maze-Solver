package graph;

import java.util.HashMap;
import java.util.ListIterator;

public class MinDistanceImpl implements MinDistance {
    private HashMap<Vertex, Integer> minDist;

    public MinDistanceImpl () {
        this.minDist = new HashMap<Vertex, Integer>();
    }

    @Override
    public int getMinDist (Vertex finalVertex) {
        return minDist.get(finalVertex);
    } 


    @Override
    public void updateMinDist (Vertex finalVertex, int minDist) {
        this.minDist.put(finalVertex, minDist);
    }

    @Override
    public Vertex getNextPivot (Vertex pivot, ProcessedVertexes processedVertexes, Graph graph) {
        ListIterator<Vertex> iter = graph.getVertices().listIterator();
        int minDistNext = graph.getVertices().size() + 1;
        Vertex nextPivot = null;
        Vertex vertex;

        while(iter.hasNext()) {
            vertex = iter.next();
            if (!vertex.isEqualTo(pivot) && !processedVertexes.isProcessed(vertex) && this.getMinDist(vertex) < minDistNext && this.getMinDist(vertex) != -1) {
                minDistNext = this.getMinDist(vertex);
                nextPivot = vertex;
            }
        }

        return nextPivot;
    }

}
