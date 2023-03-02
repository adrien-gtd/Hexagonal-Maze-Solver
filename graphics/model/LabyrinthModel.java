package graphics.model;


import java.util.LinkedList;
import java.util.List;

import java.awt.Point;
import java.awt.Dimension;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import graph.Vertex;
import maze.Maze;

public class LabyrinthModel {
    private HexagonList hexagonList;
    private List<ChangeListener> listeners = new LinkedList<ChangeListener>();
    private Maze maze;

    private List<Vertex> path;
    private String currFileName;
    private Dimension windowSize;
    private int currsorType;


    public static int CURRSOR = 0;
    public static int TOGGLE_WALL = 1;

    public LabyrinthModel () {
        currsorType = CURRSOR;
    }

    // Setters getters
    public HexagonList getHexagonList() {
        return hexagonList;
    }


    public void setMaze(Maze maze) {
        this.maze = maze;
        hexagonList = new HexagonList(this, windowSize);
        stateChanges();
    }

    public Maze getMaze() {
        return maze;
    } 

    public void setPath (List<Vertex> path) {
        this.path = path;
        this.hexagonList.updatePath(path);
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

    public void setCurrsorType(int currsorType) {
        this.currsorType = currsorType;
    }

    public void setWindowSize(Dimension windowSize) {
        this.windowSize = windowSize;
        if (maze != null) {
            hexagonList = new HexagonList(this, windowSize);
            stateChanges();
        }
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
    
    public Hexagon clicked(Point p) {
        if(!(currsorType == CURRSOR) ) {
            Hexagon answer = hexagonList.clicked(p);
            stateChanges();
            return answer;
        }
        return null;
    }

    public int getHexagonSize(Dimension windowSize) {        //unused
        int mazeSizeH = maze.getSizeX();
        int mazeSizeW = maze.getSizeY();

        int hexagonSizeH = (int) (windowSize.getHeight() / (2 * mazeSizeH + 2));
        int hexagonSizeW = (int) (windowSize.getWidth() / ((Math.sqrt(3) * (mazeSizeW + 1/2) + 4)));

        return Math.min(hexagonSizeH, hexagonSizeW);
    }

    public Dimension getOffset(Dimension windowSize, Dimension labyrinthSize,  int hexagonSize) {
        
        return new Dimension(
            (int) (windowSize.getWidth() - labyrinthSize.getWidth()) / 2,
            (int) (windowSize.getHeight() - labyrinthSize.getHeight()) / 2
        );
    }


    public void dropped(Point p, boolean isStart) {
        if(hexagonList.dropped(p, isStart)) {
            stateChanges();
        }
    }

}
