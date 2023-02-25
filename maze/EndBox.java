package maze;

import java.awt.Color;

public final class EndBox extends EmptyBox {
    public static final Color color = Color.RED;

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
        return color;
    }
}
