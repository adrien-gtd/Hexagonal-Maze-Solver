package graph;

import java.util.HashSet;

public class ProcessedVertexesImpl implements ProcessedVertexes {
    private HashSet<Vertex> vertexSet;

    public ProcessedVertexesImpl () {
        this.vertexSet = new HashSet<Vertex>();
    }


    @Override
    public boolean isProcessed (Vertex v) {
        return vertexSet.contains(v);
    }


    @Override
    public void markProcessed (Vertex v) {
        vertexSet.add(v);
    }
}
