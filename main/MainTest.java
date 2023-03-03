package main;

import maze.*;
import graph.*;
import graphics.*;
import java.util.Iterator;



/**
 * @deprecated
 * Class test of the program. Changed a lot during the course of the project.
 */

@Deprecated
public class MainTest {
    public static void main(String[] args) {
        //Maze maze = new Maze(0, 0);
        //testDisjtrat(maze);
        new LabyrinthWindow();       

    }

    @SuppressWarnings("unused")
    private static void testGraphics (Maze maze) {
        try {
            maze.initFromTextFile("./data/labyrinthe.maze");
            new LabyrinthWindow();
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