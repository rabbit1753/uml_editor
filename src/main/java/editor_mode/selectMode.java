package editor_mode;

import java.util.List;
// import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Point;

import Editor_shape.Shape;

public class selectMode extends Mode {

    private List<Shape> shape_list;
    private Point mouse_start;

    public void mousePressed(MouseEvent e) {
        mouse_start = e.getPoint();
        shape_list = canvas.get_shapeList();
        canvas.reset();

        for(int i = 0; i < shape_list.size(); i++){
            Shape shape = shape_list.get(i);
            shape.setSelected(false);
            if(shape.inside(mouse_start)){
                canvas.selectedObj = shape;
                break;
            }
        }
        canvas.repaint();
    }

    public void mouseDragged(MouseEvent e) {
        int move_X = e.getX() - mouse_start.x;
        int move_Y = e.getY() - mouse_start.y;

        if(canvas.selectedObj != null) {
            canvas.selectedObj.reLocation(move_X, move_Y);
            mouse_start = e.getPoint();
        }
        else{
            if(e.getX() > mouse_start.x) {
                if(e.getY() > mouse_start.y) 
                    canvas.selectArea.setBounds(mouse_start.x, mouse_start.y, Math.abs(move_X), Math.abs(move_Y));
                else
                    canvas.selectArea.setBounds(mouse_start.x, e.getY(), Math.abs(move_X), Math.abs(move_Y));
            }
            else{
                if(e.getY() > mouse_start.y) 
                    canvas.selectArea.setBounds(e.getX(), mouse_start.y, Math.abs(move_X), Math.abs(move_Y));
                else
                    canvas.selectArea.setBounds(e.getX(), e.getY(), Math.abs(move_X), Math.abs(move_Y));
            }
        }

        canvas.repaint();
    }

    public void mouseReleased(MouseEvent e) {
        canvas.selectArea.setBounds(0, 0, 0, 0);
        canvas.repaint();
    }
}
