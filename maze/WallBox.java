package maze;

import java.awt.Color;

public final class WallBox extends MazeBox {
    private final Color color = Color.BLACK;

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
        return color;
    }
}
