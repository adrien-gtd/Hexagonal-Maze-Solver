package graph;
import java.util.ListIterator;

/**
 * Description: implementation of the dijkstra algorithm
 */
public class Dijkstra {

    /**
     * This algorithm is used to obtain the shortest path in a graph between two given vertices
     * @param graph type Graph, represents an oriented graph
     * @param startVertex starting vertex of the algorithm
     * @param endVertex vertex on which the algorithm stops
     * @param processedVertexes list containing the vertices on which the algorithm already calculated the shortest path
     * @param minDistance updated through the entire algorithm. At a given state, contains the minimal distance between two given vertices
     * @param distance  used to get the distance in the graph between two vertices
     * @param shortestPaths returned at the end. Contains the shortest path between the startVertex and any already processed vertex of the graph
     * @return the object of type shortestpath containing the path from startVertex to endVertex
     */
    public static ShortestPaths dijkstra (
        Graph graph, 
        Vertex startVertex, 
        Vertex endVertex, 
        ProcessedVertexes processedVertexes, 
        MinDistance minDistance, 
        Distance distance,
        ShortestPaths shortestPaths
        ) 
    {

        ListIterator<Vertex> iter;              //used to iter on the lists
        Vertex pivotVertex;                     //pivot in the algorithm
        Vertex current;                         //variable used for loops on vertices

        processedVertexes.markProcessed(startVertex);
        pivotVertex = startVertex;
        iter = graph.getVertices().listIterator();
        minDistance.updateMinDist(startVertex, 0);

        while (iter.hasNext()) {
            current = iter.next();

            if (!current.isEqualTo(startVertex))
                minDistance.updateMinDist(current, -1);
        }


        Vertex pivotSuccessor;                  //used for a loop on the vertices next to 'pivot'
        int pivotMinDistance;                   //min distance of 'pivot'
        int successorMinDistance;               //min distance of 'pivotSuccessor'
        int pivotSuccessorDistance;             //ditance between 'pivot' and 'pivotSuccessor'
        int nextPivotDistance;                  //distance 'pivot' + 'pivot' - 'pivotSuccessor'


        while (!processedVertexes.isProcessed(endVertex)) {
            iter = pivotVertex.getNextVertices().listIterator();

            while (iter.hasNext()) {    
                pivotSuccessor = iter.next();
                pivotMinDistance = minDistance.getMinDist(pivotVertex);

                if (!processedVertexes.isProcessed(pivotSuccessor)) {
                    successorMinDistance = minDistance.getMinDist(pivotSuccessor);
                    pivotSuccessorDistance = distance.getDistance(pivotVertex, pivotSuccessor);
                    nextPivotDistance = pivotMinDistance +  pivotSuccessorDistance;

                    if (successorMinDistance == -1 || nextPivotDistance < successorMinDistance) {
                        shortestPaths.updatePrev(pivotSuccessor, pivotVertex);
                        minDistance.updateMinDist(pivotSuccessor, nextPivotDistance);
                    }
                }
                
            }
            

            pivotVertex = minDistance.getNextPivot(pivotVertex, processedVertexes, graph);
            processedVertexes.markProcessed(pivotVertex);
        }


        return shortestPaths;
    }


    /**
     * This algorithm applies the algorithm above with less parameters
     * @param graph             *see above*
     * @param startVertex       *see above*
     * @param endVertex         *see above*
     * @return                  *see above*
     */
    public static ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex) {
        ProcessedVertexes processedVertexes = new ProcessedVertexesImpl();
        MinDistance minDistance = new MinDistanceImpl();
        ShortestPaths shortestPaths = new ShortestPathsImpl();
        Distance distance = new DistanceImpl(graph);

        return dijkstra(graph, startVertex, endVertex, processedVertexes, minDistance, distance, shortestPaths);
    }   


}