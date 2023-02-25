package graphics;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import graphics.menu.*;
import graphics.model.LabyrinthModel;
import graphics.content.*;
import maze.*;

@SuppressWarnings("serial")
public class LabyrinthWindow extends JFrame implements ChangeListener{
    private final LabyrinthMenuBar menuBar;
    private final WindowPanel windowPanel;
    private LabyrinthModel model;


    public LabyrinthWindow (Maze maze) {                        //change the param to void when the application is done
        super ("Labyrinth Window");
        setLabyrinthModel(new LabyrinthModel(maze));
        setJMenuBar(menuBar = new LabyrinthMenuBar(this));
        add(windowPanel = new WindowPanel(this));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public void setLabyrinthModel(LabyrinthModel model) {
        this.model = model;
        model.addObserver(this);
    }

    public LabyrinthModel getLabyrinthModel () {
        return model;
    }

    public void error(String errorMessage) {                    //print the error on the gui
        System.out.println(errorMessage); 
        JOptionPane.showMessageDialog(this, errorMessage, "Error message", JOptionPane.ERROR_MESSAGE);         
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        menuBar.update();
        windowPanel.update();
    }
}
