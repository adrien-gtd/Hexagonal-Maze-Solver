package graphics.menu;

import javax.swing.*;
import graphics.*;
import graphics.model.LabyrinthModel;
import maze.Maze;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * 'Load' menu item, allows the user to import a maze saved as a .maze file to the app
 */
@SuppressWarnings("serial")
public class LoadMenuItem extends JMenuItem implements ActionListener {

    private final LabyrinthWindow window;
    private final LabyrinthModel model;


    public LoadMenuItem(LabyrinthWindow window) {
        super("Load") ; // Text of menu item
        addActionListener(this);
        model = window.getLabyrinthModel();
        this.window = window;
    }

    /**
     * Defines the acction performed when the menu is clicked on.
     * A basic file chooser object pops and allows users to choose the 
     * .maze file they want to import.
     * The current file is updated. 
     * @see graphics.menu.SaveMenuItem.java
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this) {
            JFileChooser fileChooser = new JFileChooser();                  //the pop up window to choose the file
            fileChooser.setCurrentDirectory(new File("./data"));            //set up the window to display the files in the ./data repertory
            fileChooser.setFileFilter(new FileNameExtensionFilter(".maze files", "maze"));  //the user can filter the files using a .maze filter
            int response = fileChooser.showOpenDialog(null);

            if(response == JFileChooser.APPROVE_OPTION) {
                try {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    model.setMaze(new Maze(filePath));
                    model.setCurrFileName(filePath);
                }
                catch (Exception exception) {
                    window.error("Error loadind your file : " + exception.getMessage());
                }
            }
        }  
    }
}