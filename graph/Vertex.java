package graph;
import java.util.List;

public interface Vertex {
    
    public boolean isEqualTo (Vertex v);        //return True if the vertex 'v' is equal to the current vertex
    public Vertex getVertex();                  //return the current vertex
    public void setPrevVertex (Vertex v);                    //set the previous vertex in the graph
    public Vertex getPrevVertex ();                    //get the previous vertex in the graph 
    public void setNextVertex (Vertex v);                    //set the next vertex in the graph
    public List<Vertex> getNextVertices ();                    //get the next vertices in the graph

}
