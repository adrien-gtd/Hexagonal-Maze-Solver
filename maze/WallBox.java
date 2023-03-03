package maze;

import java.awt.Color;

/**
 * In the maze, a wall box is a box representing a wall in the labyrinth. *gasp*
 * @see maze.MazeBox.java for informations on the methods.
 */
public final class WallBox extends MazeBox {
    public static final Color COLOR = Color.BLACK;

    public WallBox (int x, int y, Maze maze) {
        super(x,y, maze);
    }

    @Override
    public final char getType () {
        return 'W';
    }

    public boolean isEmptyBox() {
        return false;
    }

    @Override
    public boolean isWallBox() {
        return true;
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
