package graphics.content;

import javax.swing.JButton;
import graphics.LabyrinthWindow;
import graphics.model.LabyrinthModel;
import maze.Maze;
import graph.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Triggers the calculation and the display of the shortest path
 */
@SuppressWarnings("serial")
public class PathButton extends JButton implements ActionListener {
    private final LabyrinthWindow window;
    private final LabyrinthModel model;


    public PathButton (LabyrinthWindow window) {
        super("Generate Path"); // Change button text
        this.addActionListener(this);
        model = window.getLabyrinthModel();
        this.window = window;
    }

    /**
     * Define the acction performed when the button is clicked on.
     * Triggers the calculation of the shortest path in the maze 
     * if the path is not already displayed. If it is, a click will
     * hide the path.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Maze maze = model.getMaze();
        List<Vertex> path; 
        if (e.getSource() == this) {                        //if path not displayed and the button is pressed, we display it
            boolean togglePath = !(model.getPath() == null);
            if(!togglePath) {
                try {
                    path = Dijkstra.dijkstra(maze,maze.getStartingBox(), maze.getEndBox()).getShortestPath(maze.getEndBox(), maze.getStartingBox());
                    model.setPath(path);
                }
                catch (NullPointerException ex) { window.error("Error generating path, NullPointerException (PathButton.java) : no path found!");}
                catch (Exception ex){window.error("Error generating path (PathButton.java) : " + ex.getMessage());}
            }
            else {                                              //if not we remove it
                model.setPath(null);
            }
        }
    }
}
