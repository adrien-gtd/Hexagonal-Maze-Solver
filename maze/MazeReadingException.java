package maze;

@SuppressWarnings("serial")
public class MazeReadingException extends Exception{
    private final String name;
    private final int line;

    public MazeReadingException (String fileName, int line, String message) {
        super(message);
        this.name = fileName;
        this.line = line;
    }  

    public String getName() {
        return name;
    }

    public int getLine() {
        return line;
    }

}
