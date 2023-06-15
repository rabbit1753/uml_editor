package Editor_shape;

import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Font;

public abstract class Basic_Obj extends Shape {

    // protected List<Line> lines = new ArrayList<Line>(); 
    protected Font font = new Font(Font.DIALOG, Font.BOLD, 14);
    protected int width, height, depth;
    protected String obj_name = "Obj_name";
    protected Point[] obj_points = new Point[4];
    protected Port[] obj_ports = new Port[4];
    protected int pointsize = 8;

    public boolean inside(Point point) {
        if((point.x >= this.x1 && point.x <= this.x2) && (point.y >= this.y1 && point.y<= this.y2))
            return true;
        return false;
    }
    public abstract void draw(Graphics g);

    public void show(Graphics g) {
        for(int i = 0; i < 4; i++){
            g.fillRect(obj_points[i].x, obj_points[i].y, pointsize, pointsize);
        }
    }

    public void reLocation(int move_X, int move_Y) {
        this.x1 = this.x1 + move_X;
        this.y1 = this.y1 + move_Y;
        this.x2 = this.x1 + width;
        this.y2 = this.y1 + height;

        obj_points[0].move((x1 + x2)/2 - pointsize/2, y1 - pointsize/2);
        obj_points[1].move((x1 + x2)/2 - pointsize/2, y2 - pointsize/2);
        obj_points[2].move(x1 - pointsize/2, (y1 + y2)/2 - pointsize/2);
        obj_points[3].move(x2 - pointsize/2, (y1 + y2)/2 - pointsize/2);

        for(int i = 0; i < obj_ports.length; i++) {
            obj_ports[i].setPort(obj_points[i].x, obj_points[i].y, pointsize/2);
            obj_ports[i].resetLines();
        }
    }

    public void createPort() {

        obj_points[0] = new Point((x1 + x2)/2 - pointsize/2, y1 - pointsize/2);
        obj_points[1] = new Point((x1 + x2)/2 - pointsize/2, y2 - pointsize/2);
        obj_points[2] = new Point(x1 - pointsize/2, (y1 + y2)/2 - pointsize/2);
        obj_points[3] = new Point(x2 - pointsize/2, (y1 + y2)/2 - pointsize/2);

        for(int i = 0; i < obj_ports.length; i++) {
            Port port = new Port();
            port.setPort(obj_points[i].x, obj_points[i].y, pointsize/2);
            obj_ports[i] = port;
        }
    }

    public Port getPorts(int portIndex) {
        return obj_ports[portIndex];
    }

    public void setObjName(String name) {
        this.obj_name = name;
    }
}