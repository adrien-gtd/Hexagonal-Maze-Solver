package graph;

public interface ProcessedVertexes {
    
    public void markProcessed (Vertex v);                     //Mark a vertex 'v' as processed (add it to the processedVertex list)
    public boolean isProcessed (Vertex v);          //Check is a vertex 'v' is processed (if it is in the list) 

}
