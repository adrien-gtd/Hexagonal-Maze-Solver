package graphics.model;

import java.util.ArrayList;
import java.util.List;

import java.awt.Point;

import maze.*;
import maze.MazeBox;
import graph.Vertex;

public class HexagonList {
    private int hexagonSize = 20;
    private List<Hexagon> hexagonList = new ArrayList<Hexagon>();
    private final Maze maze;

    public HexagonList (Maze maze) {
        this.maze = maze;
        creatHexagonList(maze);
    }

    public List<Hexagon> getList() {
        return hexagonList;
    }

    private void creatHexagonList(Maze maze) {
        MazeBox[][] grid = maze.getGrid();
        int sideHexagonSize = (int) ((Math.sqrt(3) / 2) * hexagonSize);
        int xIndex = 0;
        int currX;
        int currY = hexagonSize;

        for (MazeBox[] line : grid) {
            if(xIndex % 2 == 0) {
                currX = 0;
            }
            else { 
                currX = sideHexagonSize;
            }

            for (MazeBox box : line) {
                hexagonList.add(new Hexagon(sideHexagonSize + currX, currY, hexagonSize, box.getColor()));
                currX += sideHexagonSize * 2;            //leave no gap between the hexagons
                }
            currY += 1.5 * hexagonSize;
            xIndex ++;
        }
    }

    public void updatePath (List<Vertex> path, int width) {
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

    public void clicked(Point p, LabyrinthModel model) {
        for(Hexagon hexagon : hexagonList) {
            if(hexagon.getPolygon().contains(p)) {
                System.out.println("ddddddd");
                int id = hexagonList.indexOf(hexagon);
                MazeBox box = maze.getBox(id);
                if(box.isEmptyBox()) {
                    maze.setWallBox(id);
                    hexagon.setColor(WallBox.color);
                    model.setPath(null);
                    return;
                }
                if(box.isWallBox()) {
                    maze.setEmptyBox(id);
                    hexagon.setColor(EmptyBox.color);
                    model.setPath(null);
                    return;
                }
            }
        } 
    }
}
