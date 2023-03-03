package graphics.model;

import java.util.ArrayList;
import java.util.List;

import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;

import maze.*;
import maze.MazeBox;
import graph.Vertex;

/**
 * This class is designed as a data structure containing all the informations on the grid required to display it. 
 * It also contains the method required to manipulate the the list and the elements of the list.
 */
public class HexagonList {
    /**
     * Size of the hexagon
     */
    private int hexagonSize;

    /**
     * list of he hexagons representing the current maze
     */
    private List<Hexagon> hexagonList = new ArrayList<Hexagon>();

    /**
     * maze associated with this list of hexagons
     */
    private final Maze maze;

    /**
     * exact size of the maze in pixel
     */
    private Dimension labyrinthSize;

    /**
     * model of the gui
     */
    private LabyrinthModel model;

    /**
     * offset applied to the hexagons of the list (important to center the grid !!)
     */
    private Dimension offset;


    /**
     * the order in which the elements of the class are inizilised is very important:
     * -first we get the best sized for the hexagons according to the scrren size
     * -create the list to get the exact size of the maze on the screen
     * -calculate and apply the offset to make the maze perfectly centered!!
     * @param model model of the gui
     * @param windowSize    current size of the window (updated at each resize event)
     */
    public HexagonList (LabyrinthModel model, Dimension windowSize) {
        this.model = model;
        hexagonSize = model.getHexagonSize(windowSize);
        this.maze = model.getMaze();
        creatHexagonList(maze);
        offset = model.getOffset(windowSize, labyrinthSize, hexagonSize);
        applyOffset(offset);
    }

    /**
     * 
     * @return list of hexagons
     */
    public List<Hexagon> getList() {
        return hexagonList;
    }


    /**
     * private method used to creat the list of hexagons.
     * get the value of the labyrinth size (labyrinthSize)
     * @param maze  current maze
     */
    private void creatHexagonList(Maze maze) {
        int sideHexagonSize = (int) ((Math.sqrt(3) / 2) * hexagonSize);
        int xIndex = 0;
        int currX;
        int currY = hexagonSize;
        for (MazeBox[] line : maze.getGrid()) {
            if(xIndex % 2 == 0) 
                currX = sideHexagonSize;
            else 
                currX = sideHexagonSize * 2;
            for (MazeBox box : line) {
                hexagonList.add(new Hexagon(currX,currY, hexagonSize, box.getColor()));
                if(xIndex % 2 == 0)                                         //get the exact size of the maze on the screen 
                    labyrinthSize = new Dimension(currX + 2 * sideHexagonSize, currY + hexagonSize);
                else
                    labyrinthSize = new Dimension(currX + sideHexagonSize, currY + hexagonSize);
                currX += sideHexagonSize * 2;            //leave no gap between the hexagons
            }
            currY += 1.5 * hexagonSize;
            xIndex ++;
        }
    }


    /**
     * add or remove the path from the list of hexagons
     */
    public void updatePath (List<Vertex> path) {
        if(path == null) {
            for (Hexagon hexagon : hexagonList) {
                hexagon.resetPathColor();
            }
        }
        else {
            for (Vertex vertex : path) {
                hexagonList.get(vertex.getId()).setPathColor();
            }
        }
    }


    /**
     * handle the modifications of the hexagon list in case an hexagon is clicked
     * @param p contains the coordinats of the clicked point
     * @return  the hexagon clicked on
     */
    public Hexagon clicked(Point p) {
        for(Hexagon hexagon : hexagonList) {
            if(hexagon.getPolygon().contains(p)) {
                int id = hexagonList.indexOf(hexagon);
                MazeBox box = maze.getBox(id);
                if(box.isEmptyBox() || box.isWallBox()) {
                    hexagon.setColor(maze.toggleWallBox(id, box.isEmptyBox()));
                    model.setPath(null);
                    return hexagon;
                }
                if(box.isEndBox() || box.isStartBox()) {
                    return hexagon;
                }
            }
        } 
        return null;
    }


    /**
     * handle the modifications of the hexagon list in case the starting or ending box
     * of the maze is dropped:
     * -dropped on a empty / wall box -> swap the two and replace the old starting / ending with an empty box
     * -dropped outside the maze -> nothing
     * -dropped on another box type -> just swap both
     * This method needs to swap the boxes in the maze (data) and in the hexagon list (diaplay)
     * @param p point where the box was realesed
     * @param isStart   true -> the box dropped is the starting box
     *                  false -> the box dropped is the ending box
     * @return  true if the box got dropped onto an other box (different), false otherwise
     */
    public boolean dropped(Point p, boolean isStart) {
        for(Hexagon hexagon : hexagonList) {
            if(hexagon.getPolygon().contains(p)) {
                try {
                    if(isStart) 
                        swapHexagon(hexagonList.indexOf(hexagon), maze.getStartingBox().getId());
                    else
                        swapHexagon(hexagonList.indexOf(hexagon), maze.getEndBox().getId());
                    model.setPath(null);
                    return true;
                }
                catch (Exception ex) {}
            }
        }
        return false;
    }

    /**
     * private method used in the method dropped()
     * swap the hexagons in the hexagon list and swap the boxes in the maze
     * @param id1  box / hexagon to swap 1
     * @param id2  box / hexagon to swap 2
     */
    private void swapHexagon(int id1, int id2) {
        Color temp = hexagonList.get(id1).getColor();
        if (temp == WallBox.COLOR)
            temp = EmptyBox.COLOR;
        hexagonList.get(id1).setColor(hexagonList.get(id2).getColor());
        hexagonList.get(id2).setColor(temp);
        maze.swapBoxes(id1, id2);
    }

    /**
     * apply the offset on each hexagon of the hexagon list
     * @see graphics.model.Hexagon.java
     * @param offset offset to apply on each hexagon of the list
     */
    private void applyOffset(Dimension offset) {
        for (Hexagon hexagon : hexagonList) {
            hexagon.applyOffset(offset);
        }
    }
}
