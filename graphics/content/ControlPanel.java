package graphics.content;

import javax.swing.*;
import graphics.*;
import java.awt.*;

/**
 * JPanel containing the buttons at the bottom of the window.
 */
@SuppressWarnings("serial")
public class ControlPanel extends JPanel{
    private final LabyrinthWindow window;
    private final PathButton pathButton;
    private final CursorButton cursorButton;
    private final EditButton editButton;

    public ControlPanel (LabyrinthWindow window) {
        this.window = window;
        setLayout(new GridLayout(1,3));     //grid layout used
        add(pathButton = new PathButton(window));
        add(cursorButton = new CursorButton(window));
        add(editButton = new EditButton(window));
    }

    public LabyrinthWindow getWindow() {
        return window;
    }

    public PathButton getPathButton() {
        return pathButton;
    }

    public CursorButton getCursorButton() {
        return cursorButton;
    }

    public EditButton getEditButton() {
        return editButton;
    }


}
