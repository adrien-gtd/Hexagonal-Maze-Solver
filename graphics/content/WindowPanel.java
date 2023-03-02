package graphics.content;

import javax.swing.JPanel;
import java.awt.*;
import graphics.*;

@SuppressWarnings("serial")
public class WindowPanel extends JPanel {
    private final LabyrinthPanel labyrinthPanel;

    @SuppressWarnings("unused")
    private final ControlPanel controlPanel;

    public WindowPanel (LabyrinthWindow window) {
        setLayout(new BorderLayout(10,10));
        
        add(labyrinthPanel = new LabyrinthPanel(window), BorderLayout.CENTER);
        add(controlPanel = new ControlPanel(window), BorderLayout.SOUTH);
    }

    public void update() {
        labyrinthPanel.update();

    }

    
}
