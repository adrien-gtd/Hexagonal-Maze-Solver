package graphics.content;

import javax.swing.JButton;
import graphics.LabyrinthWindow;
import graphics.model.LabyrinthModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class EditButton extends JButton implements ActionListener {
    private final LabyrinthWindow window;
    private final LabyrinthModel model;

    public EditButton (LabyrinthWindow window) {
        super("Edit"); // Change button text

        this.addActionListener(this);
        this.window = window;
        model = window.getLabyrinthModel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.setCurrsorType(LabyrinthModel.TOGGLE_WALL);
    }
}

