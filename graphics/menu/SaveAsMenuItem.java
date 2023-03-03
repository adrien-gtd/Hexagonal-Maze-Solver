package graphics.menu;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import graphics.*;
import graphics.model.LabyrinthModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Save as menu item. Allow the user to save the current maze in a new file.
 */
@SuppressWarnings("serial")
public class SaveAsMenuItem extends JMenuItem implements ActionListener {

    private final LabyrinthWindow window;
    private final LabyrinthModel model;

    public SaveAsMenuItem(LabyrinthWindow window) {
        super("Save as") ; // Text of menu item
        addActionListener(this);
        model = window.getLabyrinthModel();
        this.window = window;
    }

    /**
     * Define the acction performed when the menu is clicked on.
     * A file chooser pops on the screen and the user can create the 
     * new file. The current file name in the model is updated.
     * @see graphics.menu.SaveMenuItem.java
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("./data"));
            fileChooser.setFileFilter(new FileNameExtensionFilter(".maze files", "maze"));
            int response = fileChooser.showSaveDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                String fileName = fileChooser.getSelectedFile().getAbsolutePath();
                if (!fileName.contains(".maze")) {                  //the file name needs to contains .maze 
                    fileName = fileName.concat(".maze");            //if it doesn't, it is added to the file name
                }
                try {
                    model.saveMaze(fileName);
                    model.setCurrFileName(fileName);
                }
                catch (Exception exception){window.error("Error save as menu : " + exception.getMessage());}
            }
        }        
    }

   
}