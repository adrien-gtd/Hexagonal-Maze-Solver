package graph;
import java.util.List;

public interface Vertex {

    public int getId();                                 //return the id of the vertex
    public boolean isEqualTo (Vertex v);                //return True if the vertex 'v' is equal to the current vertex
    public List<Vertex> getNextVertices ();             //get the next vertices in the graph
    public String getLabel();                           //String identifying the vertex

}
