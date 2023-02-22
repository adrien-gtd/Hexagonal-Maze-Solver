package graphics;

import javax.swing.*;
import graphics.menu.*;
import graphics.content.*;
import maze.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class LabyrinthWindow extends JFrame {
    private final LabyrinthMenuBar menuBar;
    private final WindowPanel windowPanel;
    private Maze maze;


    public LabyrinthWindow (Maze maze) {                        //change the param to void when the application is done
        super ("Labyrinth Window");
        this.maze = maze;
        this.getRootPane().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                update();
            }
        });

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
}
