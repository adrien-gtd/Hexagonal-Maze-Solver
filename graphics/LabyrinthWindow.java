package graphics;

import javax.swing.*;

import graph.Vertex;
import graphics.menu.*;
import graphics.content.*;
import maze.*;
import java.awt.event.*;
import java.util.List;

@SuppressWarnings("serial")
public class LabyrinthWindow extends JFrame {
    private final LabyrinthMenuBar menuBar;
    private final WindowPanel windowPanel;
    private Maze maze;
    private List<Vertex> path;
    private String currFileName;


    public LabyrinthWindow (Maze maze) {                        //change the param to void when the application is done
        super ("Labyrinth Window");
        this.maze = maze;
        this.getRootPane().addComponentListener(new ComponentAdapter() {            //update every resize
            public void componentResized(ComponentEvent e) {
                update();
            }
        });
        this.currFileName = null;
        this.path = null;
        setJMenuBar(menuBar = new LabyrinthMenuBar(this));
        add(windowPanel = new WindowPanel(this));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
        update();
    }

    public Maze getMaze() {
        return maze;
    } 

    public void update(){
        menuBar.update();
        windowPanel.update();
    }

    public void setPath (List<Vertex> path) {
        this.path = path;
        update();
    }

    public List<Vertex> getPath () {
        return path;
    }

    public void error(String errorMessage) {                    //print the error on the gui TODO
        System.out.println(errorMessage);           
    }

    public String getCurrFileName() {
        return currFileName;
    }

    public void setCurrFileName(String newFileName) {
        this.currFileName = newFileName;
    } 
}
