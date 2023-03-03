package graphics.content;

import javax.swing.JButton;
import graphics.LabyrinthWindow;
import graphics.model.LabyrinthModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Default mode, the user can not edit the maze.
 * @see graphics.content.EditButton.java
 */
@SuppressWarnings("serial")
public class CursorButton extends JButton implements ActionListener{
    private final LabyrinthModel model;


    public CursorButton (LabyrinthWindow window) {
        super("Cursor"); // Change button text
        this.addActionListener(this);
        model = window.getLabyrinthModel();
    }

    /**
     * Define the acction performed when the menu is clicked on.
     * The value changed controls the eddition of the maze.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        model.setCurrsorType(LabyrinthModel.CURRSOR);
    }
}