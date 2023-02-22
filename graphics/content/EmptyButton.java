package graphics.content;

import javax.swing.JButton;
import graphics.LabyrinthWindow;

@SuppressWarnings("serial")
public class EmptyButton extends JButton{
    private final LabyrinthWindow window;

    public EmptyButton (LabyrinthWindow window) {
        super("Remove Walls"); // Change button text

        this.window = window;
    }
}