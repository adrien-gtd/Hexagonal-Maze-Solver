package graphics;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import graphics.menu.*;
import graphics.model.LabyrinthModel;
import graphics.content.*;

/**
 * this is the main frame of the program
 */
@SuppressWarnings("serial")
public class LabyrinthWindow extends JFrame implements ChangeListener{

    @SuppressWarnings("unused")
    private final LabyrinthMenuBar menuBar;
    private final WindowPanel windowPanel;
    private LabyrinthModel model;

    /**
     * initializing a new window
     */
    public LabyrinthWindow () {                        //change the param to void when the application is done
        super ("Labyrinth Window");
        setLabyrinthModel(new LabyrinthModel());
        setJMenuBar(menuBar = new LabyrinthMenuBar(this));
        add(windowPanel = new WindowPanel(this));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        JOptionPane.showMessageDialog(this, "Use the 'File' menu to open or set a new grid", "New maze", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * add itself to the observer list when adding the model to be notified of the changed
     * @see graphics.model.LabyrinthModel.java
     */
    public void setLabyrinthModel(LabyrinthModel model) {
        this.model = model;
        model.addObserver(this);
    }

    /**
     * 
     * @return the current model
     */
    public LabyrinthModel getLabyrinthModel () {
        return model;
    }

    /**
     * display any exception of the program on the screen and also print the error message on the standard output stream
     * note that the error message is customized and contains all the information to identify the source of the error
     * @param errorMessage  error message
     */
    public void error(String errorMessage) {                    //print the error on the gui
        System.out.println(errorMessage); 
        JOptionPane.showMessageDialog(this, errorMessage, "Error message", JOptionPane.ERROR_MESSAGE);         
    }

    /**
     * when the state of the model is changed, the window updates all the composent if necessary using their 
     * update() methode
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        windowPanel.update();
    }
}
