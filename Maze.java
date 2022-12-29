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

    public Maze (int sizeX, int sizeY) {            //Constructor
        this.sizeX = sizeX;
        this.sizeY = sizeY;
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
        if (x < sizeX && y < sizeY)
            return true;
        else
            return false;
    }

    public MazeBox getBox (int x, int y) {
        return grid[x][y];
    }
    
    @Override
    public List<Vertex> getVertices () {                //return all the vertices of the graph
        ArrayList<Vertex> VerticesList = new ArrayList<Vertex>();
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; i++) {
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
            Files.write(Paths.get(fileName), textToSave, StandardCharsets.UTF_8, StandardOpenOption.CREATE);        //copying all the lines directly into the file 'fileName' creating a new file even if 'fileName' already exists
        }

        catch (IOException ex){                 //opening error 
            throw new Exception ("Erreur lors de la l'ouverture du ficher: " + fileName);
        }
    }


}

