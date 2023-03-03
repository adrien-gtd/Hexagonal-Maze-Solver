package maze;

import java.awt.Color;

/**
 * In the maze, the starting box represents an empty box. 
 * The prgram is used to find the shortest path between this box and the end box.
 * @see maze.MazeBox.java for informations on the methods.
 */
public final class StartingBox extends EmptyBox {
    public static final Color COLOR = Color.GREEN;

    public StartingBox (int x, int y, Maze maze) {
        super(x, y, maze);
    }

    @Override
    public final char getType () {
        return 'D';
    }

    @Override
    public boolean isStartBox() {
        return true;
    }

    @Override
    public boolean isEndBox() {
        return false;
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
