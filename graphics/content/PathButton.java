package graphics.content;

import javax.swing.JButton;
import graphics.LabyrinthWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class PathButton extends JButton implements ActionListener {
    private final LabyrinthWindow window;

    public PathButton (LabyrinthWindow window) {
        super("Generate Path"); // Change button text

        this.addActionListener(this);
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this)
            System.out.println("ok");
    }
}
