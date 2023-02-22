package graphics.menu;
import javax.swing.*;
import graphics.*;

@SuppressWarnings("serial")
public class SaveMenuItem extends JMenuItem {

    private final LabyrinthWindow window;

    public SaveMenuItem(LabyrinthWindow window) {
        super("Save") ; // Text of menu item

        this.window = window;
   }
}
