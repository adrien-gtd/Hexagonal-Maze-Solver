package graphics.content;

import javax.swing.JButton;
import graphics.LabyrinthWindow;
import maze.Maze;
import graph.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@SuppressWarnings("serial")
public class PathButton extends JButton implements ActionListener {
    private final LabyrinthWindow window;
    private Boolean togglePath;

    public PathButton (LabyrinthWindow window) {
        super("Generate Path"); // Change button text
        togglePath = false;
        this.addActionListener(this);
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Maze maze = window.getMaze();
        List<Vertex> path; 
        if (e.getSource() == this) {                        //if path not displayed and the button is pressed, we display it
            if(!togglePath) {
                try {
                    path = Dijkstra.dijkstra(maze,maze.getStartingBox(), maze.getEndBox()).getShortestPath(maze.getEndBox(), maze.getStartingBox());
                    window.setPath(path);
                    togglePath = true;
                }
                catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
            else {                                              //if not we remove it
                window.setPath(null);
                togglePath = false;
            }
        }
    }
}
