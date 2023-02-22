package graphics.content;

import javax.swing.JButton;
import graphics.LabyrinthWindow;

@SuppressWarnings("serial")
public class WallButton extends JButton{
    private final LabyrinthWindow window;

    public WallButton (LabyrinthWindow window) {
        super("Add Walls"); // Change button text

        this.window = window;
    }
}

