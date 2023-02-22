package maze;

public final class EndBox extends EmptyBox {
    public EndBox (int x, int y, Maze maze) {
        super (x, y, maze);
    }

    @Override
    public final char getType () {
        return 'A';
    }
}
