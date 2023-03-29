package Editor;

import javax.swing.JButton;
import javax.swing.JToolBar;

// import Editor.UMLCanvas;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import editor_mode.Mode;
import editor_mode.createLine;
import editor_mode.selectMode;
import editor_mode.createObj;

public class ToolBar extends JToolBar {

    private int ToolNum = 6;
    private UMLCanvas canvas;
    private JButton clickBtn = null;

    public ToolBar() {
        canvas = UMLCanvas.getInstance();
		setLayout(new GridLayout(ToolNum * 2, 1, 2, 2));
        setFloatable(false);
        setPreferredSize(new Dimension(40, 30));
		this.setBackground(new Color(83, 85, 87));

        ImageIcon selectIcon = new ImageIcon("img/SelectIcon.png");
        ToolBtn selectBtn = new ToolBtn("select", selectIcon, new selectMode());
        add(selectBtn);

        ImageIcon associateIcon = new ImageIcon("img/AssociationLineIcon.png");
        ToolBtn associateBtn = new ToolBtn("association", associateIcon, new createLine("association"));
        add(associateBtn);

        ImageIcon generalIcon = new ImageIcon("img/GenerationLineIcon.png");
        ToolBtn generalBtn = new ToolBtn("generation", generalIcon, new createLine("generation"));
        add(generalBtn);

        ImageIcon compositionIcon = new ImageIcon("img/CompositionLineIcon.png");
        ToolBtn compositionBtn = new ToolBtn("composition", compositionIcon, new createLine("composition"));
        add(compositionBtn);

        ImageIcon classIcon = new ImageIcon("img/ClassIcon.png");
        ToolBtn classBtn = new ToolBtn("class", classIcon, new createObj("class"));
        add(classBtn);

        ImageIcon UserCaseIcon = new ImageIcon("img/UseCaseIcon.png");
        ToolBtn UserCaseBtn = new ToolBtn("UserCase", UserCaseIcon, new createObj("UserCase"));
        add(UserCaseBtn);
    }

    // String[] mode_list = {"Select", "Class", "Association", "Generalization", 
    // "Composition", "UserCase"};

    public class ToolBtn extends JButton {
        Mode ToolMode;
        public ToolBtn(String ToolName, ImageIcon Icon, Mode ToolMode){
            this.ToolMode = ToolMode;
            setToolTipText(ToolName);
            setIcon(Icon);
            setBackground(new Color(0 , 0, 0));
            setBorderPainted(false);
            // setRolloverEnabled(true);
            addActionListener(new toolListener());
        }
        class toolListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if(clickBtn != null)
                    clickBtn.setBackground(new Color(0, 0, 0));
                clickBtn = (JButton) e.getSource();
                // System.out.println(clickBtn);
                clickBtn.setBackground(new Color(50, 100, 100));
                canvas.currentMode = ToolMode;
                canvas.setCurrentMode();
                canvas.reset();
                // canvas.resetSelected();
                canvas.repaint();
            }
        }
    }
    
}
