package graphics.content;

import javax.swing.JPanel;
import java.awt.*;
import graphics.*;

/**
 * JPanel containging the main body of the window. It contains the button and the maze grid.
 */
@SuppressWarnings("serial")
public class WindowPanel extends JPanel {
    private final LabyrinthPanel labyrinthPanel;

    @SuppressWarnings("unused")
    private final ControlPanel controlPanel;

    public WindowPanel (LabyrinthWindow window) {
        setLayout(new BorderLayout());
        add(labyrinthPanel = new LabyrinthPanel(window), BorderLayout.CENTER);
        add(controlPanel = new ControlPanel(window), BorderLayout.SOUTH);
    }

    /**
     * Transmit the update signal to the other panel.
     */
    public void update() {
        labyrinthPanel.update();
    }

    
}
