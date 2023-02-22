package graphics.menu;

import javax.swing.*;
import graphics.*;

@SuppressWarnings("serial")
public class LoadMenuItem extends JMenuItem {

    private final LabyrinthWindow window;

    public LoadMenuItem(LabyrinthWindow window) {
        super("Load") ; // Text of menu item

        this.window = window;
   }
}