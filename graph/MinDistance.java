package graph;

public interface MinDistance {
    public int getMinDist (Vertex finalVertex);                                 //retrun the minimal distance between the origin and the current vertex
    public void updateMinDist (Vertex finalVertex, int minDist);                //set the minimal distance between the origin and the current vertex
}
