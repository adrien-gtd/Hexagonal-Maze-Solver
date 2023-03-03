package graphics.menu;

import javax.swing.* ;
import graphics.*;

/**
 * File Menu, contains the menu items
 */
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
      add(saveAsMenuItem = new SaveAsMenuItem(window));
      add(saveMenuItem = new SaveMenuItem(window, saveAsMenuItem));
      add(quitMenuItem = new QuitMenuItem());
   }

   public QuitMenuItem getQuitMenuItem() {
       return quitMenuItem;
   }

   public LoadMenuItem getLoadMenuItem() {
       return loadMenuItem;
   }

   public SaveAsMenuItem getSaveAsMenuItem() {
       return saveAsMenuItem;
   }

   public SaveMenuItem getSaveMenuItem() {
       return saveMenuItem;
   }

   public NewMenuItem getNewMenuItem() {
       return newMenuItem;
   }
   
}
