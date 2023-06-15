package Editor;

import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

        mi = new JMenuItem("UnGroup");
        menu.add(mi);
        mi.addActionListener(new UnGroup());

        mi = new JMenuItem("Rename");
        menu.add(mi);
        mi.addActionListener(new Rename());
    }

    private void ReNameFrame() {
        System.out.println("Change Name");
        JFrame inputTextFrame = new JFrame("Rename Object");
        inputTextFrame.setSize(400, 100);
        inputTextFrame.getContentPane().setLayout(new GridLayout(0, 1));    //新增一個新的視窗

        JPanel panel = null;
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));    //新增一個 panel

        JTextField Text = new JTextField("Obj name");   //在 panel 中新增一個文字框
        panel.add(Text);
        inputTextFrame.getContentPane().add(panel);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));    //在 panel 中新增一個 panel，包含兩個按鈕

        JButton confirm = new JButton("OK");
        panel.add(confirm);
        JButton cancel = new JButton("Cancel");
        panel.add(cancel);

        inputTextFrame.getContentPane().add(panel);     //在 panel 中新增有兩個按鈕的一個 panel

        inputTextFrame.setLocationRelativeTo(null);
        inputTextFrame.setVisible(true);

        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.Rename(Text.getText());
                inputTextFrame.dispose();
            }
        });

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputTextFrame.dispose();
            }
        });

    }

    public class GraoupObj implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            canvas.GroupShape();
        }
    }

    public class UnGroup implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            canvas.UnGroup();
        }
    }

    public class Rename implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ReNameFrame();
        }
    }
}
