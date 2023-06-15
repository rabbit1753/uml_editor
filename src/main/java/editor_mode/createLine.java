package editor_mode;

import java.awt.event.MouseEvent;
import java.awt.Point;
import java.util.List;

import Editor_shape.Shape;
import Editor_shape.Line;
import Editor_shape.Port;
import Editor_shape.Group;

public class createLine extends Mode {
    private String Line_name = null;
    private Point start_point = null, end_point = null;
    private Line line_obj = null;
    private List<Shape> obj_list = null;
    private Shape privous_obj = null;
    private int port_index_start = -1, port_index_end = -1;
    private ShapeFactory factory = new ShapeFactory();

    public createLine(String Line_Name) {
        this.Line_name = Line_Name;
    }

    public void mousePressed(MouseEvent e) {
        canvas.reset();

        start_point = e.getPoint();
        obj_list = canvas.get_shapeList();
        // Group group = new Group();
        for(int i = 0; i < obj_list.size(); i++) {
            Shape obj = obj_list.get(i);
            if(obj.inside(start_point)) {

                if(obj instanceof Group)
                    canvas.selectedObj = findShapeFromGroupObj((Group)obj, start_point);
                else
                    canvas.selectedObj = obj;
                break;
            }
        }
        if(canvas.selectedObj != null)
            start_point = which_point(start_point, "start");
        // System.out.println(start_point);
        privous_obj = canvas.selectedObj;
        canvas.reset();
    }

    public void mouseDragged(MouseEvent e) {
        if(start_point != null && privous_obj != null) {
            Point mouse_point = e.getPoint();
            Line tempLine = null;

            tempLine = factory.createLine(Line_name, start_point, mouse_point);

            canvas.draggingLine = tempLine;
            canvas.repaint();
        }
    }

    public void mouseReleased(MouseEvent e) {

        if(start_point != null && privous_obj != null) {
            end_point = e.getPoint();
            for(int i = 0; i < obj_list.size(); i++) {
                Shape obj = obj_list.get(i);
                if(obj.inside(end_point) && privous_obj != obj) {

                    if(obj instanceof Group)
                        canvas.selectedObj = findShapeFromGroupObj((Group)obj, end_point);
                    else
                        canvas.selectedObj = obj;

                    if(canvas.selectedObj != null)
                        end_point = which_point(end_point, "end");
                    else
                        break;

                    line_obj = factory.createLine(Line_name, start_point, end_point);

                    line_obj.setPorts(privous_obj.getPorts(port_index_start), canvas.selectedObj.getPorts(port_index_end));
                    privous_obj.getPorts(port_index_start).addLine(line_obj);
                    canvas.selectedObj.getPorts(port_index_end).addLine(line_obj);
                    canvas.addShape(line_obj);
                    break;
                }
            }
            // start_point = null;
            // end_point = null;
            canvas.draggingLine = null;
            canvas.repaint();
            canvas.reset();
        }
    }
    
    public Point which_point(Point origin_point, String which_port) {

        Point new_point = new Point();
        double min_distance = 20000;
        for(int i = 0; i < 4; i++) {
            Port port = canvas.selectedObj.getPorts(i);
            int x_dis = origin_point.x - (int)port.getCenterX();
            int y_dis = origin_point.y - (int)port.getCenterY();
            double  distance = Math.sqrt(Math.pow(x_dis, 2) + Math.pow(y_dis, 2));
            if(distance < min_distance) {
                if(which_port == "start")
                    port_index_start = i;
                else
                    port_index_end = i;
                min_distance = distance;
                new_point.x = (int)port.getCenterX();
                new_point.y = (int)port.getCenterY();
            }
        }
        return new_point;
    }

    public Shape findShapeFromGroupObj(Group group_obj,Point point) {
        List<Shape> shapes = group_obj.get_Shapelist();
        Shape target_shape = null;
        for(int i = 0; i < shapes.size(); i++) {
            Shape obj = shapes.get(i);
            if(obj.inside(point)) {
                if(obj instanceof Group)
                    target_shape = findShapeFromGroupObj((Group)obj, point);
                else
                    target_shape = obj;
                break;
            }
        }
        
        return target_shape;
    }
}           