package graph;
import java.util.List;

public interface ShortestPaths {
    public void addShortest (Vertex v);                             //add a vertex to the shortest path between two vertices
    public List<Vertex> getShortest ();                             //get the shortest path between two vertices
    public Vertex getPrev (Vertex v);                                       //get the origin of the vertex 'v' in the shortest path
    public void updatePrev (Vertex v, Vertex origin);               //update the origin of the vertex 'v' in the shortest path 
}
