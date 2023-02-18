package maze;

public class EmptyBox extends MazeBox{
    public EmptyBox (int x, int y, Maze maze) {
        super(x, y, maze);
    }

    @Override
    public boolean isEmpty () {
        return true;
    }

    @Override
    protected char getType () {
        return 'E';
    }
}
