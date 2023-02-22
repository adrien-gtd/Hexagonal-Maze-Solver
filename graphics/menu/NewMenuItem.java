package graphics.menu;

import javax.swing.*;
import graphics.*;
import maze.Maze;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class NewMenuItem extends JMenuItem implements ActionListener {

    private final LabyrinthWindow window;

    public NewMenuItem(LabyrinthWindow window) {
        super("New") ; // Text of menu item
        addActionListener(this);
        this.window = window;
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this) {
            int sizeY = askSizeInt("width");
            int sizeX = askSizeInt("height");
            window.setMaze(new Maze(sizeX, sizeY));
            window.setCurrFileName(null);
        }
    }

    private int askSizeInt(String variableName) {
        String answer; 
        int res = 0;
        Boolean stop = false;
        while(!stop) {
            answer = JOptionPane.showInputDialog(window, "Enter " + variableName + " value : ", "Set " + variableName + " - New labyrinth", JOptionPane.QUESTION_MESSAGE);
            try {
                res = Integer.parseInt(answer);
                stop = true;
            }
            catch (Exception exception) {
                JOptionPane.showMessageDialog(window, "Error : the value entered is not an integer!", "Error set " + variableName, JOptionPane.ERROR_MESSAGE);
            }

        }
        return res;
        
    }
   
}