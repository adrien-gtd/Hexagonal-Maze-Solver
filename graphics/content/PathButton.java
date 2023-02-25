package graphics.content;

import javax.swing.JButton;
import graphics.LabyrinthWindow;
import graphics.model.LabyrinthModel;
import maze.Maze;
import graph.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@SuppressWarnings("serial")
public class PathButton extends JButton implements ActionListener {
    private final LabyrinthWindow window;
    private Boolean togglePath;
    private final LabyrinthModel model;


    public PathButton (LabyrinthWindow window) {
        super("Generate Path"); // Change button text
        togglePath = false;
        this.addActionListener(this);
        model = window.getLabyrinthModel();
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Maze maze = model.getMaze();
        List<Vertex> path; 
        if (e.getSource() == this) {                        //if path not displayed and the button is pressed, we display it
            if(!togglePath) {
                try {
                    path = Dijkstra.dijkstra(maze,maze.getStartingBox(), maze.getEndBox()).getShortestPath(maze.getEndBox(), maze.getStartingBox());
                    model.setPath(path);
                    togglePath = true;
                }
                catch (Exception ex){
                    window.error("Error generating path (PathButton.java) : " + ex.getMessage());
                }
            }
            else {                                              //if not we remove it
                model.setPath(null);
                togglePath = false;
            }
        }
    }
}
