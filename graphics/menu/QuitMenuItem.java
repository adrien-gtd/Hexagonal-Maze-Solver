package graphics.menu;

import javax.swing.* ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class QuitMenuItem extends JMenuItem implements ActionListener {

   public QuitMenuItem() {
      super("Quit") ; // Text of menu item
      this.addActionListener(this);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == this) {
         System.exit(0);
      }
      
   }
}

