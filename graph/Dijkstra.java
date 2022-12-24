package graph;
import java.util.ListIterator;

public class Dijkstra {

    public static ShortestPaths disjkstra (
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

            if (current.isEqualTo(startVertex))
                minDistance.updateMinDist(current, -1);
        }


        Vertex pivotSuccessor;                  //used for a loop on the vertices next to 'pivot'
        int pivotMinDistance;                   //min distance of 'pivot'
        int successorMinDistance;               //min distance of 'pivotSucessor'
        int pivotSuccessorDistance;             //ditance between 'pivot' and 'pivotSucessor'
        Vertex nextPivot = startVertex;         //used to find the next pivot at the end of the loop
        int nextMinDistance = -1;               //used to find the next pivot at the end of the loop

        while (!processedVertexes.isProcessed(endVertex)) {

            iter = pivotVertex.getNextVertices().listIterator();
            while (iter.hasNext()) {
                pivotSuccessor = iter.next();
                pivotMinDistance = minDistance.getMinDist(pivotVertex);

                if (!processedVertexes.isProcessed(pivotSuccessor)) {
                    successorMinDistance = minDistance.getMinDist(pivotSuccessor);
                    pivotSuccessorDistance = distance.getDistance(pivotVertex, pivotSuccessor);

                    if (successorMinDistance == -1 || pivotMinDistance +  pivotSuccessorDistance < successorMinDistance) {
                        shortestPaths.updatePrev(pivotSuccessor, pivotVertex);
                        minDistance.updateMinDist(pivotSuccessor, pivotMinDistance +  pivotSuccessorDistance);
                    }
                }
            }
            
            iter = pivotVertex.getNextVertices().listIterator();
            while (iter.hasNext()) {
                pivotSuccessor = iter.next();
                successorMinDistance = minDistance.getMinDist(pivotSuccessor);

                if (!processedVertexes.isProcessed(pivotSuccessor)) {
                    if (nextMinDistance == -1 || successorMinDistance < nextMinDistance) {
                        nextMinDistance = successorMinDistance;
                        nextPivot = pivotSuccessor;
                    }
                }
            }
            pivotVertex = nextPivot;
            processedVertexes.markProcessed(pivotVertex);
        }




        return shortestPaths;
    }



}