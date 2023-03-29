package Editor;

import java.awt.BorderLayout;
// import java.awt.Dimension;

import javax.swing.JFrame;
// import javax.swing.JPanel;
import javax.swing.JMenuBar;

// import Editor.ToolBar;

public class UMLEditor extends JFrame{

    // private enum Mode {
    //     SELECT, CLASS, ASSOCIATION, GENERALIZATION, COMPOSITION, USERCASE
    // }
    private ToolBar toolbar;
    private UMLCanvas canvas;
    private Menu menuBar;

    // JPanel toolbarPanel = new JPanel(new BorderLayout());

    public UMLEditor() {
        canvas = UMLCanvas.getInstance();
        toolbar = new ToolBar();
        menuBar = new Menu();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(toolbar, BorderLayout.WEST);
        getContentPane().add(canvas, BorderLayout.CENTER);
        getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        UMLEditor mainWindow = new UMLEditor();  
        mainWindow.setTitle("OOAD UML Editor");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(1200, 700);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }

}