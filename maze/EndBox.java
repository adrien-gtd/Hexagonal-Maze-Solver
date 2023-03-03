package maze;

import java.awt.Color;

/**
 * In the maze, the end box represents an empty box. 
 * The prgram is used to find the shortest path between the starting box and this one.
 * @see maze.MazeBox.java for informations on the methods.
 */
public final class EndBox extends EmptyBox {
    public static final Color COLOR = Color.RED;

    public EndBox (int x, int y, Maze maze) {
        super (x, y, maze);
    }

    @Override
    public final char getType () {
        return 'A';
    }

    @Override
    public boolean isStartBox() {
        return false;
    }

    @Override
    public boolean isEndBox() {
        return true;
    }    

    @Override
    public boolean isEmptyBox() {
        return false;
    }

    @Override
    public Color getColor() {
        return COLOR;
    }
}
