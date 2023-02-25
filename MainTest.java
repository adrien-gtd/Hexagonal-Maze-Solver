import maze.*;
import graph.*;
import graphics.*;
import java.util.Iterator;




public class MainTest {
    public static void main(String[] args) {
        Maze maze = new Maze(0, 0);
        //testDisjtrat(maze);
        testGraphics(maze);        

    }

    private static void testGraphics (Maze maze) {
        try {
            maze.initFromTextFile("./data/labyrinthe.maze");
            new LabyrinthWindow(maze);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
    

    @SuppressWarnings("unused")
    private static void testDisjtrat (Maze maze) {
        try {
            maze.initFromTextFile("./data/labyrinthe.maze");            //open the lab file

            //apply the dijkstrat algorithm and print the result on stdout
            ShortestPaths path = Dijkstra.dijkstra(maze, maze.getStartingBox(), maze.getEndBox());
            Iterator<Vertex> iter = path.getShortestPath(maze.getEndBox(), maze.getStartingBox()).listIterator();
            Vertex curr;
            
            while(iter.hasNext()) {
                curr = iter.next();
                System.out.println(curr.getLabel());
            }
        }
    
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

}