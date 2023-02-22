package graphics.menu;

import javax.swing.*;
import graphics.*;


@SuppressWarnings("serial")
public class SaveAsMenuItem extends JMenuItem {

    private final LabyrinthWindow window;

    public SaveAsMenuItem(LabyrinthWindow window) {
        super("Save as") ; // Text of menu item

        this.window = window;
   }
}