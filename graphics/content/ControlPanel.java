package graphics.content;

import javax.swing.*;
import graphics.*;
import java.awt.*;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel{
    private final LabyrinthWindow window;
    private final PathButton pathButton;
    private final CursorButton cursorButton;
    private final WallButton wallButton;
    private final EmptyButton emptyButton;

    public ControlPanel (LabyrinthWindow window) {
        this.window = window;
        setLayout(new GridLayout(1,4));
        add(pathButton = new PathButton(window));
        add(cursorButton = new CursorButton(window));
        add(wallButton = new WallButton(window));
        add(emptyButton = new EmptyButton(window));
    }

    public void update() {
        return;
    }
}
