package graphics.content;

import javax.swing.JButton;
import graphics.LabyrinthWindow;
import graphics.model.LabyrinthModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Allow the user to modify the maze on the screen.
 */
@SuppressWarnings("serial")
public class EditButton extends JButton implements ActionListener {
    private final LabyrinthModel model;

    public EditButton (LabyrinthWindow window) {
        super("Edit"); // Change button text

        this.addActionListener(this);
        model = window.getLabyrinthModel();
    }

    /**
     * Define the acction performed when the menu is clicked on.
     * The value changed controls the eddition of the maze.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        model.setCurrsorType(LabyrinthModel.TOGGLE_WALL);
    }
}

