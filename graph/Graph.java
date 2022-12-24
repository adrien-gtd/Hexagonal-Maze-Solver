package graph;
import java.util.List;

public interface Graph {
    public List<Vertex> nextVertices(Vertex v);                 //Get the list of vertices next to a vertex 'v' 
    public List<Vertex> getVertices ();                         //Get the list of vertices in of the graph
}
