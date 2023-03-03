package graphics.model;


import java.util.LinkedList;
import java.util.List;

import java.awt.Point;
import java.awt.Dimension;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import graph.Vertex;
import maze.Maze;

/**
 * Model of the GUI, this class handles every modification on the data. Every request to modify, update something on the GUI 
 * comes through this object.
 */
public class LabyrinthModel {
    /**
     * @see graphics.model.HexagonList.java
     */
    private HexagonList hexagonList;

    /**
     * List of observers on this model (in the case only 1)
     */
    private List<ChangeListener> listeners = new LinkedList<ChangeListener>();

    /**
     * Current maze
     * @see maze.Maze.java;
     */
    private Maze maze;

    /**
     * Current shortest path
     */
    private List<Vertex> path;

    /**
     * Current file to save to modifications to
     * @see graphics.menu.SaveMenuItem.java
     */
    private String currFileName;

    /**
     * Dimensions of the labyrinth panel (and not the whole window!).
     * Updated every time the window is resized.
     */
    private Dimension windowSize;

    /**
     * @see graphics.content.CursorButton.java
     * @see graphics.content.EditButton.java
     */
    private int cursorType;

    /**
     * CursorType possibles values
     */
    public static int CURSOR = 0;
    public static int TOGGLE_WALL = 1;

    public LabyrinthModel () {
        cursorType = CURSOR;
    }

    /* ----------------- start setters / getters ----------------- */
    /**
     * 
     * @return list of hexagons
     */
    public HexagonList getHexagonList() {
        return hexagonList;
    }


    /**
     * Changing the maze, when loading a maze or creating a new maze.
     * The hexagon list has to be renewed.
     * @param maze new maze value
     */
    public void setMaze(Maze maze) {
        this.maze = maze;
        hexagonList = new HexagonList(this, windowSize);
        stateChanges();
    }

    /**
     * 
     * @return current maze
     */
    public Maze getMaze() {
        return maze;
    } 

    /**
     * Triggers the displaying / removal of the path on the interface
     * @param path new path value
     */
    public void setPath (List<Vertex> path) {
        this.path = path;
        this.hexagonList.updatePath(path);
        stateChanges();
    }

    /**
     * 
     * @return current path value
     */
    public List<Vertex> getPath () {
        return path;
    }
    

    /**
     * 
     * @param newFileName new file name value
     */
    public void setCurrFileName(String newFileName) {
        this.currFileName = newFileName;
    } 

    /**
     * 
     * @return  current file name value
     */
    public String getCurrFileName() {
        return currFileName;
    }

    /**
     * Set to one of the to values possible (see static declarations)
     * @param cursorType new cursor type value
     */
    public void setCursorType(int cursorType) {
        this.cursorType = cursorType;
    }

    /**
     * Triggered when the window is resized, recalculating the dispaly of the hexagons to keep the grid centered
     * @param windowSize new window size value
     */
    public void setWindowSize(Dimension windowSize) {
        this.windowSize = windowSize;
        path = null;
        if (maze != null) {
            hexagonList = new HexagonList(this, windowSize);
            stateChanges();
        }
    }
    /* ----------------- end setters / getters ----------------- */

    /**
     * Saves the current maze to a new file
     * @param fileName name of the file
     * @throws Exception  handled in the window
     */
    public void saveMaze (String fileName) throws Exception {
        maze.saveToTextFile(fileName);
    }

    /**
     * Adds an observer to the model, when the state changes, all the observers are notified
     * @see stateChanges()
     * @param listener new observer
     */
    public void addObserver(ChangeListener listener) {
		listeners.add(listener);
    }

    /**
     * Notifies every observer when the data is changed.
     * Called every time a new information needs to be displayed on the screen
     */
    public void stateChanges() {
		ChangeEvent evt = new ChangeEvent(this);
		
		for (ChangeListener listener : listeners) {
			listener.stateChanged(evt);
		}
	}

    /**
     * Triggered when the user clicks on the screen, if the cursor is not in edit more, nothing happens
     * @see graphics.content.LabyrinthPanel.java
     * 
     * @param p  coordinates (in pixels) of the event (mouse pressed)
     * @return  the hexagon clicked on if any, null otherwise
     */
    public Hexagon clicked(Point p) {
        if(!(cursorType == CURSOR) ) {
            Hexagon answer = hexagonList.clicked(p);
            stateChanges();
            return answer;
        }
        return null;
    }

    /**
     * Used to get the optimal hexagon size given the screen dimensions.
     * The hexagons will occupy the most available space on the screen, 
     * leaving a border proportional to the size of the heaxagons (at least 1 in y and 2 in x)
     * @param windowSize current size of the window
     * @return  the optimal hexagon size
     */
    public int getHexagonSize(Dimension windowSize) {
        int mazeSizeH = maze.getSizeX();
        int mazeSizeW = maze.getSizeY();

        int hexagonSizeH = (int) (windowSize.getHeight() / (2 * mazeSizeH + 2));
        int hexagonSizeW = (int) (windowSize.getWidth() / ((Math.sqrt(3) * (mazeSizeW + 1/2) + 4)));

        return Math.min(hexagonSizeH, hexagonSizeW);
    }

    /**
     * Calculates the optimal offset such that the grid is centered on the screen
     * @param windowSize   current window size value (in pixels)
     * @param labyrinthSize     size of the grid (in pixels)
     * @return  the offset such that the given grid is centered in the window
     */
    public Dimension getOffset(Dimension windowSize, Dimension labyrinthSize) {
        return new Dimension(
            (int) (windowSize.getWidth() - labyrinthSize.getWidth()) / 2,
            (int) (windowSize.getHeight() - labyrinthSize.getHeight()) / 2
        );
    }

    /**
     * Triggered when dropping the start or end hexagon, the state is updated only if a change has 
     * been made in the data
     * @see graphics.model.HexagonList.java
     * @param p point containting the coordinates of the location where the hexagon was dropped 
     * @param isStart   true -> the hexagon dropped it the start of the maze
     *                  false -> the hexagon dropped is the end of the maze 
     */
    public void dropped(Point p, boolean isStart) {
        if(hexagonList.dropped(p, isStart)) {
            stateChanges();
        }
    }

}
