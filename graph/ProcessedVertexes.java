package graph;
import java.util.List;

public interface ProcessedVertexes {
    
    public void markProcessed (Vertex v);                     //Mark a vertex 'v' as processed (add it to the processedVertex list)
    public Vertex unmarkProcessed (Vertex v);                //Unmake a vertex 'v' (remove it from the lsit)
    public List<Vertex> getProcessedList ();                     //Get the list of processed vertices 
    public Vertex getProcessedLast ();                       //Get the last marked as processed vertex
    public boolean isProcessed (Vertex v);          //Check is a vertex 'v' is processed (if it is in the list) 

}
