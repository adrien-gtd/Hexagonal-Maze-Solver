package graphics.model;

import java.util.ArrayList;
import java.util.List;

import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;

import maze.*;
import maze.MazeBox;
import graph.Vertex;

public class HexagonList {
    private int hexagonSize = 20;
    private List<Hexagon> hexagonList = new ArrayList<Hexagon>();
    private final Maze maze;
    private Dimension labyrinthSize;
    private LabyrinthModel model;
    private Dimension offset;

    public HexagonList (LabyrinthModel model, Dimension windowSize) {
        this.model = model;
        hexagonSize = model.getHexagonSize(windowSize);
        this.maze = model.getMaze();
        creatHexagonList(maze);
        offset = model.getOffset(windowSize, labyrinthSize, hexagonSize);
        applyOffset(offset);
    }

    public List<Hexagon> getList() {
        return hexagonList;
    }

    public Dimension getLabyrinthSize() {
        return labyrinthSize;
    }

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
                if(xIndex % 2 == 0)
                    labyrinthSize = new Dimension(currX + 2 * sideHexagonSize, currY + hexagonSize);
                else
                    labyrinthSize = new Dimension(currX + sideHexagonSize, currY + hexagonSize);
                currX += sideHexagonSize * 2;            //leave no gap between the hexagons
            }
            currY += 1.5 * hexagonSize;
            xIndex ++;
        }
    }

    public void updatePath (List<Vertex> path) {
        if(path == null) 
            removePath();
        else {
            for (Vertex vertex : path) {
                hexagonList.get(vertex.getId()).setPathColor();
            }
        }
    }

    private void removePath() {
        for (Hexagon hexagon : hexagonList) {
            hexagon.resetPathColor();
        }
    }

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

    private void applyOffset(Dimension offset) {
        for (Hexagon hexagon : hexagonList) {
            hexagon.applyOffset(offset);
        }
    }

    private void swapHexagon(int id1, int id2) {
        Color temp = hexagonList.get(id1).getColor();
        if (temp == WallBox.color)
            temp = EmptyBox.color;
        hexagonList.get(id1).setColor(hexagonList.get(id2).getColor());
        hexagonList.get(id2).setColor(temp);
        maze.swapBoxes(id1, id2);
    }


}
