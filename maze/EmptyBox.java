package maze;

import java.awt.Color;

/**
 * In the maze, an empty box represents a box wich is not a wall.
 * @see maze.MazeBox.java for informations on the methods.
 */
public class EmptyBox extends MazeBox{
    public static final Color COLOR = Color.GRAY;

    public EmptyBox (int x, int y, Maze maze) {
        super(x, y, maze);
    }


    @Override
    public char getType () {
        return 'E';
    }

    @Override
    public boolean isEmptyBox() {
        return true;
    }

    @Override
    public boolean isWallBox() {
        return false;
    }

    @Override
    public boolean isStartBox() {
        return false;
    }

    @Override
    public boolean isEndBox() {
        return false;
    }

    @Override
    public Color getColor() {
        return COLOR;
    }
}
