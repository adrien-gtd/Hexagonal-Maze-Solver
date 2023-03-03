package graph;
import java.util.List;

/**
 * Classes implementing this interface represent vertices of an oriented graph
 */
public interface Vertex {

    /**
     * Each vertex in the graph is given a unique int called id, this method is used to get this id
     * @return id of the current vertex
     */
    public int getId();

    /**
     * Is used to compare two vertices
     * @param v vertex we compare the current object to
     * @return true if both vertices are equal, false otherwise
     */
    public boolean isEqualTo (Vertex v);

    /**
     * Is used to get all the vertices of the oriented graph where there exists an edge from the current vertex to the other
     * @return the list of all the neighbors of the current vertex
     */
    public List<Vertex> getNextVertices ();

    /**
     * Each vertex is identified by a unique String called label, this method is used to get this string. 
     * This method is very useful to debug by printing the label.
     * @return the string value of the label
     */
    public String getLabel();

}
