package graphics.menu;

import javax.swing.* ;
import graphics.*;

@SuppressWarnings("serial")
public class FileMenu extends JMenu {
   private final QuitMenuItem quitMenuItem;
   private final LoadMenuItem loadMenuItem;
   private final SaveAsMenuItem saveAsMenuItem;
   private final SaveMenuItem saveMenuItem;
   private final NewMenuItem newMenuItem;

   public FileMenu(LabyrinthWindow window) {
      super("File") ; // Text of the menu

      add(newMenuItem = new NewMenuItem(window));
      add(loadMenuItem = new LoadMenuItem(window));
      add(saveMenuItem = new SaveMenuItem(window));
      add(saveAsMenuItem = new SaveAsMenuItem(window));
      add(quitMenuItem = new QuitMenuItem(window));
   }
}
