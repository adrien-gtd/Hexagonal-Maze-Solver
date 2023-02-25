package graphics.content;

import javax.swing.*;
import graphics.*;
import java.awt.*;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel{
    private final LabyrinthWindow window;
    private final PathButton pathButton;
    private final CursorButton cursorButton;
    private final ToggleWallButton toggleWallButton;

    public ControlPanel (LabyrinthWindow window) {
        this.window = window;
        setLayout(new GridLayout(1,4));
        add(pathButton = new PathButton(window));
        add(cursorButton = new CursorButton(window));
        add(toggleWallButton = new ToggleWallButton(window));
    }


}
