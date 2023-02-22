package graphics.menu;

import javax.swing.* ;
import graphics.*;

@SuppressWarnings("serial")
public class LabyrinthMenuBar extends JMenuBar {

   private final FileMenu fileMenu ;
	
   public LabyrinthMenuBar(LabyrinthWindow window) {
      super() ;
		

      add(fileMenu = new FileMenu(window));
   }

   public void update() {
      return;
   }
}
