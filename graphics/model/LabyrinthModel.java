package graphics.model;


import java.util.LinkedList;
import java.util.List;

import java.awt.Point;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import graph.Vertex;
import maze.Maze;

public class LabyrinthModel {
    private HexagonList hexagonList;
    private List<ChangeListener> listeners = new LinkedList<ChangeListener>();
    private Maze maze;
    private double aspectRatio;
    private List<Vertex> path;
    private String currFileName;

    public LabyrinthModel (Maze maze) {
        setMaze(maze);
    }

    // Setters getters
    public HexagonList getHexagonList() {
        return hexagonList;
    }

    public double getAspectRatio() {
        return aspectRatio;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
        hexagonList = new HexagonList(maze);
        updateRatio();
        stateChanges();
    }

    public Maze getMaze() {
        return maze;
    } 

    public void setPath (List<Vertex> path) {
        this.path = path;
        this.hexagonList.updatePath(path, maze.getSizeY());
        stateChanges();
    }

    public List<Vertex> getPath () {
        return path;
    }

    public void setCurrFileName(String newFileName) {
        this.currFileName = newFileName;
    } 

    public String getCurrFileName() {
        return currFileName;
    }
    //fin des getters setters

    public void saveMaze (String s) throws Exception {
        maze.saveToTextFile(s);
    }
    
    public void addObserver(ChangeListener listener) {
		listeners.add(listener);
    }
    
    public void stateChanges() {
		ChangeEvent evt = new ChangeEvent(this);
		
		for (ChangeListener listener : listeners) {
			listener.stateChanged(evt);
		}
	}
    
    public void clicked(Point p) {
        hexagonList.clicked(p);
        stateChanges();
    }

    private void updateRatio() {
        int gridSizeX = maze.getSizeX();
        int gridSizeY = maze.getSizeY();

        double height = 2 * gridSizeX + 4;
        double width = Math.sqrt(3) * (gridSizeY + 1) + 4;
        aspectRatio = height / width;
    }
}
