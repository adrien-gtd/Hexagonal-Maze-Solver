package graph;

public interface MinDistance {
    public int getMinDist (Vertex finalVertex);                                 //retrun the minimal distance between the origin and the current vertex
    public void updateMinDist (Vertex finalVertex, int minDist);                //set the minimal distance between the origin and the current vertex
    public Vertex getNextPivot (Vertex pivot, ProcessedVertexes processedVertexes, Graph graph);                                  //return the next pivot for the dijsktrat algorithm
}
