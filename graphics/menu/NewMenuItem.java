package graphics.menu;

import javax.swing.*;
import graphics.*;

@SuppressWarnings("serial")
public class NewMenuItem extends JMenuItem {

    private final LabyrinthWindow window;

    public NewMenuItem(LabyrinthWindow window) {
        super("New") ; // Text of menu item

        this.window = window;
   }
}