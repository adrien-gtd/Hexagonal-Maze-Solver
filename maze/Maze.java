package maze;

import graph.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.nio.file.*;
import java.nio.charset.StandardCharsets;

import java.awt.Color;
import java.awt.Point;

/**
 * An instance of this class represents a maze, one of the central concept of the project.
 * The goal of the project is to perform the dijkstra algorith described in the graph package.
 * Therefore, an instance of this class is also regarded as an oriented graph.
 */
public final class Maze implements Graph, Distance{
    /**
     * Size x and y of the maze (could have used a Point or a Dimension object to make the code more compact)
     * x is the number of lines in the grid and y is the number of columns!
     */
    private int sizeX;                        
    private int sizeY;                        

    /**
     * Contains the grid with all the boxes of the maze
     */
    private MazeBox[][] grid;


    /**
     * This construcor instantiate a new maze with the starting box on the top 
     * left corner and the end box at the bottom right corner of the grid
     * @param sizeX desired number of rows
     * @param sizeY desired number of columns
     */
    public Maze (int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        newEmptyMaze ();
    }

    /**
     * Private method, used to create the empty grid of the constructor above 
     */
    private void newEmptyMaze () {
        grid = new MazeBox[sizeX][sizeY];
        for (int line = 0; line < sizeX; line++) {
            for(int column = 0; column < sizeY; column++) {
                if (line == 0 && column == 0) 
                    grid[line][column] = new StartingBox(line, column, this);

                else if (line == sizeX - 1 && column == sizeY -1)
                    grid[line][column] = new EndBox(line, column, this);

                else {
                    grid[line][column] = new EmptyBox(line, column, this);
                }
            }
        }
    }


    /**
     * This constructor creates the maze object from an existing file containing a saved maze.
     * The exception is handeled at a higher level of the program!
     * @param fileName name of the file containing the data 
     * @throws Exception
     */
    public Maze (String fileName) throws Exception {
        initFromTextFile(fileName);
    }


    /* ------------- begining getters / setters -------------*/

    /**
     * get the endBox of the maze (note that the application is designed such that the user 
     * can only create a new maze or import an existing maze created originaly with the 
     * 'empty' constructor above. Moreover in the edit mode of the gui, the end box
     * can only be dragged and dropped but it is not possible to create an other one,
     * thus there is only one end box in a maze object. If not, there would be the need to throw an exception
     * in the case that there is two or more end boxes)
     * @return the unique end box of the maze object 
     * @throws Exception
     */
    public MazeBox getEndBox() throws Exception{
        for (MazeBox[] line : grid) {
            for (MazeBox box : line) {
                if (box.isEndBox())
                return box;
            }
        }
        throw new Exception("No arrival in the maze!");
    }

    /**
     * @see getEndbox()
     * Similar to getEndBox but returns the strating box instead
     */
    public MazeBox getStartingBox() throws Exception{
        for (MazeBox[] line : grid) {
            for (MazeBox box : line) {
                if (box.isStartBox())
                return box;
            }
        }
        throw new Exception("No departure in the maze!");
    }

    /**
     * 
     * @return the grid of the maze
     */
    public MazeBox[][] getGrid () {
        return grid;
    }

    /**
     * 
     * @return  the x size of the maze 
     */
    public int getSizeX () {
        return sizeX;
    }
    
    /**
     * 
     * @return the y size of the maze
     */
    public int getSizeY () {
        return sizeY;
    }

    /**
     * get the box of the maze at the coordinates x and y
     * @param x x coordinate of the desired box of the maze
     * @param y y coordinate of the desired box of the maze
     * @return null if the coordinates are not valid, the deisred box otherwise
     */
    public MazeBox getBox (int x, int y) {
        if(x > sizeX || x < 0)
            return null;
        if(y > sizeY || y < 0)
            return null;
        return grid[x][y];
    }

    /**
     * get the box of the maze identified by the identificator id
     * (note: overloading the getBox method!)
     * @param id identificator of the desired box of the maze
     * @return  null if id is not valid, the desired box otherwise
     */
    public MazeBox getBox (int id) {
        if(id > sizeY * sizeX) 
            return null;
        int x = id / sizeY;
        int y = id % sizeY;
        return grid[x][y];
    }

    /**
     * get the distance between to vertex in the graph
     * In this case, the distance between two vertices is always 1 if they are neighbors
     * @return 1 if the two vertices are connected, -1 otherwise
     */
    @Override
    public int getDistance (Vertex origineVertex, Vertex finalVertex) {
        if(origineVertex.getNextVertices().contains(finalVertex)) 
            return 1;
        return -1;
    }
    
    /**
     * @see graph.Graph.java
     * get all the vertices of the graph
     * @reutrn a list of all the vertices of thr graph
     */
    @Override
    public List<Vertex> getVertices () {
        ArrayList<Vertex> VerticesList = new ArrayList<Vertex>();
        for (int line = 0; line < sizeX; line++) {
            for (int column = 0; column < sizeY; column++) {
                VerticesList.add(grid[line][column]);
            }
        }
        return VerticesList;
    }

    /* ------------- end getters / setters -------------*/


    /**
     * check if there exists a box identified by the coordinates x and y
     * @param x x coordinate to check
     * @param y y coordiante to check
     * @return  true if x, y is a box in the maze, false otherwise
     */
    public boolean isInMaze(int x, int y) {
        if (x < sizeX && y < sizeY && x >= 0 && y >= 0)
            return true;
        else
            return false;
    }

    /**
     * Initialize a maze object with the file name passed as a parameter.
     * Check if the file exists, if there is no error opening it, reading it,
     * as well as the encoding of the maze 
     * @param fileName  name of the file containing the encoded maze
     * @throws Exception    two different exceptions can occure during the execution of the method
     *                      they or both handled by throwing an exception of type Exception with a 
     *                      cutsome message that will be handled higher in the program.
     *                      (It is not the optimal way to handle exceptions and on a bigger scale project
     *                      it would be a problem. In this case it works just fine and it makes the exceptions 
     *                      very simple to handle!)
     */
    public final void initFromTextFile(String fileName) throws Exception {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);   //read all the lines of the file 'fileName'
            sizeX = lines.size();
            sizeY = lines.get(0).length();
            grid = new MazeBox[sizeX][sizeY];
            for (int line = 0; line < sizeX; line++) {                     //double for loop on the chars of the file to later create the corresponding boxes
                if(lines.get(line).length() != sizeY)                      //if the Ylenght of the grid isn't the same for the whole grid then the grid isn't squared
                    throw new MazeReadingException(fileName, line, "The grid rows are not the same length");
                for (int column = 0; column < sizeY; column++) {
                    grid[line][column] = createBox(lines.get(line).charAt(column), fileName, line, column);
                }
            }
        }
        catch (IOException ex) {                //Opening/reading error
            throw new Exception ("Error oppening the file (Maze): " + fileName);
        }
        catch (MazeReadingException ex) {       //Maze encoding error
            throw new Exception (
                "Wrong encoding of the maze file (Maze) " + ex.getName() + 
                " line: " + Integer.toString(ex.getLine()) + 
                ". " + ex.getMessage()
                );
        }
    }

    /**
     * Private method used in the method above, creating the box with the correct type according to the character read
     * @param c character read in the file, corresponding to a box type (or not)
     * @param fileName name of the file read, used for the exception
     * @param line  line number of the character read
     * @param column    column of the character read     
     * @return  return a box with the correct type
     * @throws MazeReadingException if the character is not corresponding to any box type, an exception is raised
     */
    private MazeBox createBox(char c, String fileName, int line, int column) throws MazeReadingException{
        if (c == 'E')
            return new EmptyBox(line, column, this);
        if (c == 'W')
            return new WallBox(line, column, this);
        if (c == 'A')
            return new EndBox(line, column, this);
        if (c == 'D')
            return new StartingBox(line, column, this);
        throw new MazeReadingException(fileName, line, "Invalid character!");
    }

    /**
     * Save the current maze object to a file
     * @param fileName  name of the file the maze should be saved at 
     * @throws Exception    an error can occure when opening / writing on the file
     *                      the eroor is handeled the same way as in initFromTextFile()
     */
    public final void saveToTextFile(String fileName) throws Exception {
        try {
            ArrayList<String> textToSave = new ArrayList<String>();
            StringBuffer stringBuffer;                                                 
            for (int line = 0; line < sizeX; line++) {                                   //reading each line and adding it to the arraylist above
                stringBuffer = new StringBuffer();
                for(int column = 0; column < sizeY; column++) {
                    stringBuffer.append(grid[line][column].getType());
                }
                textToSave.add(stringBuffer.toString());
            }
            Files.write(
                Paths.get(fileName), 
                textToSave, StandardCharsets.UTF_8, 
                StandardOpenOption.CREATE, 
                StandardOpenOption.TRUNCATE_EXISTING);        //copying all the lines directly into the file 'fileName' creating a new file even if 'fileName' already exists
        }
        catch (IOException ex){                 //opening error 
            throw new Exception ("Error opening the file (Maze): " + fileName);
        }
    }


    /**
     * private function used in the two methods below transform an id into a point
     * object containting the corresponding x, y coordiantes
     * @param id id coordinate to transform
     * @return point containing the x, y coordinates
     */
    private Point idToXY(int id) {
        return new Point(
            id / sizeY,
            id % sizeY
        );
    }


    /**
     * Change a given box. If the box is a wall, 
     * it is changed to an empty box and same for the opposite case.
     * This method is used to modify the maze and update the gui.
     * @param id    id of the box to change
     * @param isEmpty   contains true id the given box is empty and false other wise
     *                  (same as getBox(id).isEmpty())
     * @return  new color of the box. Used to update the gui
     */
    public Color toggleWallBox(int id, boolean isEmpty) {
        Point coord = idToXY(id);
        if (isEmpty) {
            this.grid[coord.x][coord.y] = new WallBox(coord.x, coord.y, this);
            return WallBox.COLOR;
        } else {
            this.grid[coord.x][coord.y] = new EmptyBox(coord.x, coord.y, this);
            return EmptyBox.COLOR;
        }
    }   

    /**
     * This method swap the type of two boxes identified by the ids id1 and id2.
     * It is used for the drag and drop of the boxes start box and end box in th gui.
     * @param id1 id of the first box
     * @param id2 id of the second box
     */
    public void swapBoxes(int id1, int id2) {
        Point coord1 = idToXY(id1);
        Point coord2 = idToXY(id2);
        MazeBox temp = grid[coord1.x][coord1.y];
        createSameBox(grid[coord2.x][coord2.y], coord1);
        if(temp.isWallBox())                                    //swapping with a wall box creates an empty box!!
            grid[coord2.x][coord2.y] = new EmptyBox(coord2.x,coord2.y,this);
        else 
            createSameBox(temp, coord2);
        return;
    }


    /**
     * private method used for the method swapBoxes. Creates a box of the same type as the box 
     * passed as the first parameter at the coordinates of the second one
     * @param toCopy   box of the type needed for the new one
     * @param newBoxCoord   coordinates of the box to replace
     */
    private void createSameBox(MazeBox toCopy, Point newBoxCoord) {
        if(toCopy.isEmptyBox()) {
            grid[newBoxCoord.x][newBoxCoord.y] = new EmptyBox(newBoxCoord.x, newBoxCoord.y, this);
            return;
        }
        if(toCopy.isEndBox()){
            grid[newBoxCoord.x][newBoxCoord.y] = new EndBox(newBoxCoord.x, newBoxCoord.y, this);
            return;
        }
        if(toCopy.isWallBox()){
            grid[newBoxCoord.x][newBoxCoord.y] = new WallBox(newBoxCoord.x, newBoxCoord.y, this);
            return;
        }
        if(toCopy.isStartBox()){
            grid[newBoxCoord.x][newBoxCoord.y] = new StartingBox(newBoxCoord.x, newBoxCoord.y, this);
            return;
        }
    }

}

