package graph;
import java.util.List;

public interface Graph {
    public List<Vertex> getVertices ();                         //Get the list of vertices of the graph
    public int getDistance (Vertex origineVertex, Vertex finalVertex);  //get the distance between the two vertices; return -1 if not connected
}
