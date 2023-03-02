package graphics.content;

import javax.swing.JButton;
import graphics.LabyrinthWindow;
import graphics.model.LabyrinthModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class CursorButton extends JButton implements ActionListener{
    private final LabyrinthModel model;


    public CursorButton (LabyrinthWindow window) {
        super("Cursor"); // Change button text
        this.addActionListener(this);
        model = window.getLabyrinthModel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.setCurrsorType(LabyrinthModel.CURRSOR);
    }
}