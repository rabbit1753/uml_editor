package Editor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar {
    private UMLCanvas canvas;


    public Menu() {
        canvas = UMLCanvas.getInstance();

        JMenu menu;
        JMenuItem mi;

        menu = new JMenu("Edit");
        add(menu);

        mi = new JMenuItem("Group");
        menu.add(mi);
        mi.addActionListener(new GraoupObj());
    }

    public class GraoupObj implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            canvas.GroupShape();
        }
    }
}
