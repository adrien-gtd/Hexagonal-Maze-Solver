package graphics.menu;

import javax.swing.* ;
import graphics.*;

/**
 * Menu bar, contains the different menus. In this case there is only one menu 'file menu'.
 * Not the most useful class
 */
@SuppressWarnings("serial")
public class LabyrinthMenuBar extends JMenuBar {
   private final FileMenu fileMenu ;
	
   public LabyrinthMenuBar(LabyrinthWindow window) {
      super() ;
      add(fileMenu = new FileMenu(window));
   }

   public FileMenu getFileMenu() {
       return fileMenu;
   }
}
