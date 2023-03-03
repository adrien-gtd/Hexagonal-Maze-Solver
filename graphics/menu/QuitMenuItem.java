package graphics.menu;

import javax.swing.* ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Quit menu item, offers the user an other way to quit the application.
 */
@SuppressWarnings("serial")
public class QuitMenuItem extends JMenuItem implements ActionListener {

   public QuitMenuItem() {
      super("Quit") ; // Text of menu item
      this.addActionListener(this);
   }

   /**
    * Defines the acction performed when the menu is clicked on.
    * Quits the program.
    */
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == this) {
         System.exit(0);
      }
      
   }
}

