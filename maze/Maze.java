package maze;

import graph.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public final class Maze implements Graph{
    private int sizeX;                        //size X of the maze
    private int sizeY;                        //size Y of the maze

    private MazeBox[][] grid;                       //contains the grid with all the boxes of the maze

    public Maze (int sizeX, int sizeY) {            //Constructor empty maze
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        newEmptyMaze ();
    }

    private void newEmptyMaze () {
        grid = new MazeBox[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for(int j = 0; j < sizeY; j++) {
                if (i == 0 && j == 0) 
                    grid[i][j] = new StartingBox(i, j, this);

                else if (i == sizeX - 1 && j == sizeY -1)
                    grid[i][j] = new EndBox(i, j, this);

                else {
                    grid[i][j] = new EmptyBox(i, j, this);
                }
            }
        }
    }

    public Maze (String fileName) throws Exception {        //contructor maze from a file
        initFromTextFile(fileName);
    }

    public List<String> toMazeString() {
        ArrayList<String> mazeString = new ArrayList<String>();
        String curr = "";
        for (MazeBox[] line : grid) {
            curr = "";
            for (MazeBox box : line) {
                if(box.isWallBox())
                    curr += ".";
                if(box.isEndBox())
                    curr += "A";
                if(box.isStartBox())
                    curr += "D";
                if(box.isEmptyBox())
                    curr += " ";
            }
            mazeString.add(curr);
        }
        return mazeString;
    }


    public MazeBox getEndBox() throws Exception{
        for (MazeBox[] line : grid) {
            for (MazeBox box : line) {
                if (box.isEndBox())
                return box;
            }
        }
        throw new Exception("Pas d'arrivé dans le labyronthe!");
    }


    public MazeBox getStartingBox() throws Exception{
        for (MazeBox[] line : grid) {
            for (MazeBox box : line) {
                if (box.isStartBox())
                return box;
            }
        }
        throw new Exception("Pas de départ dans le labyronthe!");
    }

    public MazeBox[][] getGrid () {         //return the grid
        return grid;
    }

    public int getSizeX () {            //return the size X of the maze
        return sizeX;
    }

    public int getSizeY () {            //return the size Y of the maze
        return sizeY;
    }
    
    public boolean isInMaze(int x, int y) {            //check if the coordonates of the vertex v are in the maze
        if (x < sizeX && y < sizeY && x >= 0 && y >= 0)
            return true;
        else
            return false;
    }

    public MazeBox getBox (int x, int y) {
        return grid[x][y];
    }

    public MazeBox getBox (int id) {
        int x = id / sizeY;
        int y = id % sizeY;
        return grid[x][y];
    }

    @Override
    public int getDistance (Vertex origineVertex, Vertex finalVertex) {
        if(origineVertex.getNextVertices().contains(finalVertex)) 
            return 1;
        return -1;
    }
    
    @Override
    public List<Vertex> getVertices () {                //return all the vertices of the graph
        ArrayList<Vertex> VerticesList = new ArrayList<Vertex>();
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                VerticesList.add(grid[i][j]);
            }
        }
        return VerticesList;
    }


    //Initialize a labyrinth with the file given in parameter, check if the encoding of the labyrinth is correct aswell

    public final void initFromTextFile(String fileName) throws Exception {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);   //read all the lines of the file 'fileName'
            sizeX = lines.size();
            sizeY = lines.get(0).length();
            grid = new MazeBox[sizeX][sizeY];
            for (int i = 0; i < sizeX; i++) {                           //double for loop on the chars of the file to later create the corresponding boxes
                if(lines.get(i).length() != sizeY)                      //if the Ylenght of the grid isn't the same for the whole grid then the grid isn't squared
                    throw new MazeReadingException(fileName, i, "La grille n'est pas carrée!");
                for (int j = 0; j < sizeY; j++) {
                    char curr = lines.get(i).charAt(j);
                    switch (curr) {                     //Creating the right box type for each char of the init file
                        case 'E':
                            grid[i][j] = new EmptyBox(i, j, this);
                            break;
                        case 'W':
                            grid[i][j] = new WallBox(i, j, this);
                            break;
                        case 'A':
                            grid[i][j] = new EndBox(i, j, this);
                            break;
                        case 'D':
                            grid[i][j] = new StartingBox(i, j, this);
                            break;
                        default:                                            //if the caracter is different than the 4 above, there is an error
                            throw new MazeReadingException(fileName, i, "Caractère invalide!");
                    } 
                }
            }
        }
        catch (IOException ex) {                //Opening error
            throw new Exception ("Erreur lors de la l'ouverture du ficher: " + fileName);
        }
        catch (MazeReadingException ex) {       //Maze encoding error
            throw new Exception (
                "Erreur lors de la lecture du fichier " + 
                ex.getName() + 
                " ligne: " + 
                Integer.toString(ex.getLine()) + 
                ". " + 
                ex.getMessage()
                );
        }
    }


    //save the current maze to the text file "fileName"
    public final void saveToTextFile(String fileName) throws Exception {
        try {
            ArrayList<String> textToSave = new ArrayList<String>();
            StringBuffer sb;                                                 
            for (int i = 0; i < sizeX; i++) {                                   //reading each line and adding it to the arraylist above
                sb = new StringBuffer();
                for(int j = 0; j < sizeY; j++) {
                    sb.append(grid[i][j].getType());
                }
                textToSave.add(sb.toString());
            }
            Files.write(Paths.get(fileName), textToSave, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);        //copying all the lines directly into the file 'fileName' creating a new file even if 'fileName' already exists
        }

        catch (IOException ex){                 //opening error 
            throw new Exception ("Erreur lors de la l'ouverture du ficher: " + fileName);
        }
    }

    public void setEmptyBox(int id) {
        int x = id / sizeY;
        int y = id % sizeY;
        this.grid[x][y] = new EmptyBox(x, y, this);
        return;
    }

    public void setWallBox(int id){
        int x = id / sizeY;
        int y = id % sizeY;
        this.grid[x][y] = new WallBox(x, y, this);
        return;
    }

    public void swapBoxes(int id1, int id2) {
        int x1 = id1 / sizeY;
        int y1 = id1 % sizeY;
        int x2 = id2 / sizeY;
        int y2 = id2 % sizeY;
        MazeBox temp = grid[x1][y1];
        grid[x1][y1] = grid[x2][y2];;
        grid[x1][y1] = temp;
        return;
    }

}

