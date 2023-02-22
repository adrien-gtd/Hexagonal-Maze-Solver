package graphics.menu;

import javax.swing.*;
import graphics.*;
import maze.Maze;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

@SuppressWarnings("serial")
public class LoadMenuItem extends JMenuItem implements ActionListener {

    private final LabyrinthWindow window;

    public LoadMenuItem(LabyrinthWindow window) {
        super("Load") ; // Text of menu item
        addActionListener(this);
        this.window = window;
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("./data"));
            fileChooser.setFileFilter(new FileNameExtensionFilter(".maze files", "maze"));
            int response = fileChooser.showOpenDialog(null);

            if(response == JFileChooser.APPROVE_OPTION) {
                try {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    window.setMaze(new Maze(filePath));
                    window.setCurrFileName(filePath);
                }
                catch (Exception exception) {
                    window.error("Error loadind your file : " + exception.getMessage());
                }
            }
        }  
    }
}