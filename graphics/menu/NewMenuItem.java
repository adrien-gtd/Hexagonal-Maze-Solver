package graphics.menu;

import javax.swing.*;
import graphics.*;
import graphics.model.LabyrinthModel;
import maze.Maze;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** 
 * 'New' menu item, allow the user to create a new maze.
*/
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

    /**
     * Define the acction performed when the menu is clicked on.
     * The user needs to input the size of the maze desired.
     * If the user enters a valid input, a new maze is set in the model.
     * The 'current file' used for the save menu is set to null.
     * If the user press the save menu, he will be redirected to the save as menu
     * @see graphics.menu.SaveMenuItem.java
     */
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

    /**
     * Creates a JOptionPane asking the user for an int as an input. 
     * The method loops until the user entered a correct value or pressed the cancel button.
     * @param variableName  name of the value to ask for
     * @return  return -1 if the cancel button is pressed or a positive integer entered by the user
     */
    private int askSizeInt(String variableName) {
        String answer;
        while(true) {
            answer = JOptionPane.showInputDialog(
                window, 
                "Enter " + variableName + " value : ", 
                "Set " + variableName + " - New labyrinth", 
                JOptionPane.QUESTION_MESSAGE
            );
            if (answer == null)
                return -1;
            if (answer.matches("\\d+"))
                return Integer.parseInt(answer);
            else
                JOptionPane.showMessageDialog(
                    window, 
                    "Error : invalid value entered! Enter a positive integer. ", 
                    "Error set " + variableName, 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}