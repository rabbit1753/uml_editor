package Editor;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
// import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import javax.swing.JPanel;

// import Editor_shape.Basic_Obj;
import editor_mode.Mode;
import Editor_shape.Shape;


public class UMLCanvas extends JPanel {

    private static UMLCanvas instance = null;
    private EventListener listener = null;
    private List<Shape> shape_list = new ArrayList<Shape>();

    protected Mode currentMode = null;

    public Shape selectedObj = null;
    public Shape draggingLine = null;
    public Rectangle selectArea = new Rectangle();

    private UMLCanvas() {

    }

    public static UMLCanvas getInstance() {
		if (instance == null) {
			instance = new UMLCanvas();
		}
		return instance;
	}

    public void setCurrentMode() {
        removeMouseListener((MouseListener) listener);
        removeMouseMotionListener((MouseMotionListener) listener);
        listener = currentMode;
        addMouseListener((MouseListener) listener);
        addMouseMotionListener((MouseMotionListener) listener);
    }

    public void addShape(Shape new_shape) {
        shape_list.add(new_shape);
    }

    public List<Shape> get_shapeList() {
        return shape_list;
    }

	public void reset() {
        if(selectedObj != null)
            selectedObj = null;

        selectArea.setBounds(0, 0, 0, 0);

        for(int i = 0; i < shape_list.size(); i++)
            shape_list.get(i).setSelected(false);

	}

    public boolean shape_in_selectArea(Shape shape) {
        Point left_upper = new Point(shape.getx1(), shape.gety1());
        Point right_lower = new Point(shape.getx2(), shape.gety2());

        if(selectArea.contains(left_upper) && selectArea.contains(right_lower))
            return true;
        return false;
    }

    public void GroupShape() {
        
    }

    public void paint(Graphics g) {

        // System.out.println(g);
        Dimension dim = getSize();
        g.setColor(new Color(200, 200, 200));
        g.fillRect(0, 0, dim.width, dim.height);

        for(int i = shape_list.size() - 1; i >= 0 ;i--) {
            Shape shape = shape_list.get(i);
            shape.draw(g);
            if((!selectArea.isEmpty() && shape_in_selectArea(shape)) || shape.getSelected()) {
                shape.setSelected(true);
                shape.show(g);
                System.out.println("check selectArea");
            }
        }
        
        if(selectedObj != null) {
            selectedObj.show(g);
            System.out.println("selectedObj");
        }

        if(draggingLine != null) {
            draggingLine.draw(g);
            System.out.println("draggingLine");
        }

        if(!selectArea.isEmpty()) {
            int alpha = 80;
            
            g.setColor(new Color(30,30,100, alpha));
            g.fillRect(selectArea.x, selectArea.y, selectArea.width, selectArea.height);
            g.setColor(new Color(30,30,100));
            g.drawRect(selectArea.x, selectArea.y, selectArea.width, selectArea.height);
            
        }
    }
}
