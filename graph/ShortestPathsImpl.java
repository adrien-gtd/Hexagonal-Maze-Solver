package graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ShortestPathsImpl implements ShortestPaths {
    private HashMap<Vertex, Vertex> shortest;

    public ShortestPathsImpl () {
        this.shortest = new HashMap<Vertex, Vertex>();
    }

    @Override
    public void updatePrev (Vertex v, Vertex origin) {
        shortest.put(v, origin);
    }


    @Override
    public void printShotestpath () {                       //used to debug the code
        Iterator <Vertex> setIter = shortest.keySet().iterator();
        Vertex curr;
        while(setIter.hasNext()) {
            curr = setIter.next();
            System.out.print("Key : ");
            System.out.println(curr.getLabel());
            System.out.print("Value : ");
            System.out.println(shortest.get(curr).getLabel());
            System.out.println();
        }
        return;
    }



    @Override
    public List<Vertex> getShortestPath( Vertex endVertex, Vertex startVertex) {
        ArrayList<Vertex> result = new ArrayList<Vertex>();
        for (Vertex v = endVertex; this.shortest.containsKey(v); v = shortest.get(v)) {                      
            result.add(v);
        }
        result.add(startVertex);
        Collections.reverse(result);
        return result;

    }
}   
