package graphics.content;

import javax.swing.JPanel;
import java.awt.*;
import graphics.*;

@SuppressWarnings("serial")
public class WindowPanel extends JPanel {
    private final LabyrinthWindow window;
    private final LabyrinthPanel labyrinthPanel;
    private final ControlPanel controlPanel;

    public WindowPanel (LabyrinthWindow window) {
        this.window = window;
        setLayout(new BorderLayout());
        
        add(labyrinthPanel = new LabyrinthPanel(window), BorderLayout.CENTER);
        add(controlPanel = new ControlPanel(window), BorderLayout.SOUTH);
    }

    public void update() {
        labyrinthPanel.update();

    }

    
}
