package graphics.menu;

import javax.swing.*;
import graphics.*;
import graphics.model.LabyrinthModel;
import maze.Maze;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class NewMenuItem extends JMenuItem implements ActionListener {

    private final LabyrinthWindow window;
    private final LabyrinthModel model;

    public NewMenuItem(LabyrinthWindow window) {
        super("New") ; // Text of menu item
        addActionListener(this);
        model = window.getLabyrinthModel();
        this.window = window;
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this) {
            int sizeY = askSizeInt("width");
            if (sizeY == -1)
                return;
            int sizeX = askSizeInt("height");
            if (sizeX == -1) 
                return;
            model.setMaze(new Maze(sizeX, sizeY));
            model.setCurrFileName(null);
        }
    }

    private int askSizeInt(String variableName) {
        String answer; 
        int res = 0;
        Boolean stop = false;
        while(!stop) {
            answer = JOptionPane.showInputDialog(window, "Enter " + variableName + " value : ", "Set " + variableName + " - New labyrinth", JOptionPane.QUESTION_MESSAGE);
            if (answer == null)
                return -1;
            try {
                res = Integer.parseInt(answer);
                if (res > 0)
                    stop = true;
            }
            catch (Exception exception) {
                JOptionPane.showMessageDialog(window, "Error : invalid value entered! Enter a positive integer. ", "Error set " + variableName, JOptionPane.ERROR_MESSAGE);
            }

        }
        return res;
        
    }
   
}