package graphics.menu;

import javax.swing.*;
import graphics.*;
import graphics.model.LabyrinthModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
