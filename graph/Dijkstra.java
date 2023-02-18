package graph;
import java.util.ListIterator;

public class Dijkstra {

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

    public static ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex) {
        ProcessedVertexes processedVertexes = new ProcessedVertexesImpl();
        MinDistance minDistance = new MinDistanceImpl();
        ShortestPaths shortestPaths = new ShortestPathsImpl();
        Distance distance = new DistanceImpl(graph);

        return dijkstra(graph, startVertex, endVertex, processedVertexes, minDistance, distance, shortestPaths);
    }   


}