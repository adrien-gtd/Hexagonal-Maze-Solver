package maze;

public final class StartingBox extends EmptyBox {

    public StartingBox (int x, int y, Maze maze) {
        super(x, y, maze);
    }

    @Override
    public final char getType () {
        return 'D';
    }
}
