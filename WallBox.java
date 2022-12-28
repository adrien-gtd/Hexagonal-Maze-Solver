public final class WallBox extends MazeBox {
    public WallBox (int x, int y, Maze maze) {
        super(x,y, maze);
    }

    @Override
    public boolean isEmpty () {
        return false;
    }
}
