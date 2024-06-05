package graphics.content;

import javax.swing.JButton;
import graphics.LabyrinthWindow;
import graphics.model.LabyrinthModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Button changing the cursor type to edit mode: allows the user to modify the maze by clicking on the frame.
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
     * Define the action performed when the menu is clicked on.
     * The value changed controls the edition of the maze.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        model.setCursorType(LabyrinthModel.TOGGLE_WALL);
    }
}

