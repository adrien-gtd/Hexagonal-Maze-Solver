package graphics.menu;

import javax.swing.*;
import graphics.*;
import graphics.model.LabyrinthModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Save menu item. Allow the user to save the modifications he made on the maze in one click.
 */
@SuppressWarnings("serial")
public class SaveMenuItem extends JMenuItem implements ActionListener {

    private final LabyrinthWindow window;
    private final SaveAsMenuItem saveAs;
    private final LabyrinthModel model;

    public SaveMenuItem(LabyrinthWindow window, SaveAsMenuItem saveAs) {
        super("Save") ; // Text of menu item
        addActionListener(this);
        this.window = window;
        model = window.getLabyrinthModel();
        this.saveAs = saveAs;
    }

    /**
     * Define the acction performed when the menu is clicked on.
     * The model contains a String named current file. If this string isn't null
     * the maze is saved at this location. Otherwise a click on this menu is redirected to
     * the saveAs menu.
     */
    @Override  
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            if (model.getCurrFileName() != null) {
                try {
                    model.saveMaze(model.getCurrFileName());
                }
                catch (Exception exception){
                    window.error("Error save menu : " + exception.getMessage());
                }   
            }
            else {
                saveAs.doClick();
            }
        }
        
    }
}
