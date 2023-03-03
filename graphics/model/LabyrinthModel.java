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
 * model of the gui, this class handle all the modifications on the data. Every request to modify, update something on the guy 
 * comes through this object.
 */
public class LabyrinthModel {
    /**
     * @see graphics.model.HexagonList.java
     */
    private HexagonList hexagonList;


    private List<ChangeListener> listeners = new LinkedList<ChangeListener>();

    /**
     * current maze
     * @see maze.Maze.java;
     */
    private Maze maze;

    /**
     * current shortest path
     */
    private List<Vertex> path;

    /**
     * current file to save to modifications to
     * @see graphics.menu.SaveMenuItem.java
     */
    private String currFileName;

    /**
     * Dimensions of the labyrinth panel.
     * updated every time the window is resized 
     */
    private Dimension windowSize;

    /**
     * @see graphics.content.CursorButton.java
     * @see graphics.content.EditButton.java
     */
    private int currsorType;


    public static int CURRSOR = 0;
    public static int TOGGLE_WALL = 1;

    public LabyrinthModel () {
        currsorType = CURRSOR;
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
     * changing the maze, when loading a maze or creating a new maze.
     * The hexagon list has to be renewed
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
     * triggers the displaying / removal of the path
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
     * @param currsorType new currsor type value
     */
    public void setCurrsorType(int currsorType) {
        this.currsorType = currsorType;
    }

    /**
     * triggered when the window is resized, recalculating the dispaly of the hexagons to keep the grid centered
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
     * save the current maze to a new file
     * @param fileName name of the file
     * @throws Exception  handled in the window
     */
    public void saveMaze (String fileName) throws Exception {
        maze.saveToTextFile(fileName);
    }

    /**
     * add the observers to the model, when the state changes, all the observers are notified
     * @see stateChanges()
     * @param listener new observer
     */
    public void addObserver(ChangeListener listener) {
		listeners.add(listener);
    }

    /**
     * notify every observer when the data is changed.
     * called every time an new information needs to be displayed on the screen
     */
    public void stateChanges() {
		ChangeEvent evt = new ChangeEvent(this);
		
		for (ChangeListener listener : listeners) {
			listener.stateChanged(evt);
		}
	}

    /**
     * triggered when the user click on the screen, if the cursor is not in edit more, nothing happens
     * @see graphics.content.LabyrinthPanel.java
     * 
     * @param p  coordinates (in pixel) of the event (mouse pressed)
     * @return  the hexagon clicked on if any, null otherwise
     */
    public Hexagon clicked(Point p) {
        if(!(currsorType == CURRSOR) ) {
            Hexagon answer = hexagonList.clicked(p);
            stateChanges();
            return answer;
        }
        return null;
    }

    /**
     * fromulas are not super important, set to return the optimal hexagon size given a screen size.
     * The hexagons will be the biggest possible, leaving a border proportional to the size of the heaxagons (at least 1 in y and 2 in x)
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
     * calculates the optimal offset such that the grid is centered on the screen
     * @param windowSize   current window size value (in pixel)
     * @param labyrinthSize     size of the grid (in pixel)
     * @param hexagonSize   size of each hexagon on the grid (in pixel)
     * @return  the offset such that the given green in centered in the given window
     */
    public Dimension getOffset(Dimension windowSize, Dimension labyrinthSize,  int hexagonSize) {
        return new Dimension(
            (int) (windowSize.getWidth() - labyrinthSize.getWidth()) / 2,
            (int) (windowSize.getHeight() - labyrinthSize.getHeight()) / 2
        );
    }

    /**
     * triggered when dropping the start or end hexagon, the state is updated only if a change has 
     * been made in the data
     * @see graphics.model.HexagonList.java
     * @param p point containting the coordinates of the dropped hexagon
     * @param isStart   true -> the hexagon dropped it the start of the maze
     *                  false -> the hexagon dropped is the end of the maze 
     */
    public void dropped(Point p, boolean isStart) {
        if(hexagonList.dropped(p, isStart)) {
            stateChanges();
        }
    }

}
