package maze;

import java.awt.Color;

public final class StartingBox extends EmptyBox {
    private final Color color = Color.GREEN;

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
        return color;
    }
}
