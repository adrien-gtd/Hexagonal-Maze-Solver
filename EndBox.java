public final class EndBox extends EmptyBox {
    public EndBox (int x, int y, Maze maze) {
        super (x, y, maze);
    }

    @Override
    protected final char getType () {
        return 'A';
    }
}
