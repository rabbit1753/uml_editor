package Editor;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import javax.swing.JPanel;

import editor_mode.Mode;
import Editor_shape.Shape;
import Editor_shape.Group;


public class UMLCanvas extends JPanel {

    private static UMLCanvas instance = null;
    private EventListener listener = null;
    private List<Shape> shape_list = new ArrayList<Shape>();

    protected Mode currentMode = null;

    public Shape selectedObj = null;    // 點擊到的那個 obj
    public Shape draggingLine = null;   //滑鼠拖曳時的線
    public Rectangle selectArea = new Rectangle(); //選取框的範圍

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
        Group group_obj = new Group();
        for(int i = 0; i < shape_list.size(); ) {
            Shape shape = shape_list.get(i);
            if(shape.getGroupSelected()) {
                group_obj.addShape(shape);
                shape_list.remove(shape);
                continue;
            }
            i++;
        }
        group_obj.setBounds();
        shape_list.add(group_obj);
    }

    public void UnGroup() {
        if(selectedObj != null && selectedObj instanceof Group) {
            Group group_obj = (Group)selectedObj;
            List<Shape> shapes = group_obj.get_Shapelist();
            for(int i = 0; i < shapes.size(); i++) {
                shape_list.add(shapes.get(i));
            }
            shape_list.remove(group_obj);
            System.out.println("Ungroup");
        }
    }

    public void Rename(String name) {
        if(selectedObj != null && selectedObj instanceof Group) {
            Group group_obj = (Group)selectedObj;
            List<Shape> shapes = group_obj.get_Shapelist();
            for(int i = 0; i < shapes.size(); i++) {
                shapes.get(i).setObjName(name);
            }
        }
        else if(selectedObj != null) {
            selectedObj.setObjName(name);
        }
        repaint();
    }

    public void paint(Graphics g) {

        // System.out.println(g);
        Dimension dim = getSize();
        g.setColor(new Color(200, 200, 200));
        g.fillRect(0, 0, dim.width, dim.height);

        for(int i = shape_list.size() - 1; i >= 0 ;i--) {
            Shape shape = shape_list.get(i);
            shape.draw(g);
            shape.setGroupSelected(false);
            if((!selectArea.isEmpty() && shape_in_selectArea(shape)) || shape.getSelected()) {
                shape.setSelected(true);
                shape.show(g);
                shape.setGroupSelected(true);
            }
        }
        
        if(selectedObj != null) {
            selectedObj.show(g);
        }

        if(draggingLine != null) {
            draggingLine.draw(g);
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
