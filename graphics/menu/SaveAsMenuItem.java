package graphics.menu;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import graphics.*;
import graphics.model.LabyrinthModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this) {

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("./data"));
            fileChooser.setFileFilter(new FileNameExtensionFilter(".maze files", "maze"));
            int response = fileChooser.showSaveDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                String fileName = fileChooser.getSelectedFile().getAbsolutePath();
                if (!fileName.contains(".maze")) {
                    fileName = fileName.concat(".maze");
                }

                try {
                    model.saveMaze(fileName);
                    model.setCurrFileName(fileName);
                }
                catch (Exception exception){
                    window.error("Error save as menu : " + exception.getMessage());
                }
            }
        }        
    }

   
}