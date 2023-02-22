package graphics.menu;

import javax.swing.* ;
import graphics.*;

@SuppressWarnings("serial")
public class QuitMenuItem extends JMenuItem {

   private final LabyrinthWindow window ;

   public QuitMenuItem(LabyrinthWindow window) {
      super("Quit") ; // Text of menu item

      this.window = window;
   }
}

