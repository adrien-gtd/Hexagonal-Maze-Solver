package maze;

import java.awt.Color;
public class EmptyBox extends MazeBox{
    private final Color color = Color.GRAY;

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
        return color;
    }
}
