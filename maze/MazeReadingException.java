package maze;

/**
 * Exception used when reading a '.maze' file. Useful to get information on where the error happened.
 */
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
