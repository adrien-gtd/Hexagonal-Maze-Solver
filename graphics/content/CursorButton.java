package graphics.content;

import javax.swing.JButton;
import graphics.LabyrinthWindow;

@SuppressWarnings("serial")
public class CursorButton extends JButton{
    private final LabyrinthWindow window;

    public CursorButton (LabyrinthWindow window) {
        super("Cursor"); // Change button text

        this.window = window;
    }
}