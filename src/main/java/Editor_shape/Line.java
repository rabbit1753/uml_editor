package Editor_shape;

import java.awt.Point;
import java.awt.Graphics;

public abstract class Line extends Shape {

    protected Port[] line_ports = new Port[2];

    // public void draw_points(Graphics g) {
    //     int pointsize = 8;
    //     g.fillRect(x1, y1, pointsize, pointsize);
    //     g.fillRect(y2, y2, pointsize, pointsize);
    // }

    public void setPorts(Port port1, Port port2) {
        this.line_ports[0] = port1;
        this.line_ports[1] = port2;
    }

    public void show(Graphics g) {
        int pointsize = 8;
        g.fillRect(this.x1, this.y1, pointsize, pointsize);
        g.fillRect(this.x2, this.y2, pointsize, pointsize);
    }

    public abstract void draw(Graphics g);

    public void reLocation() {
        this.x1 = (int)line_ports[0].getCenterX();
        this.y1 = (int)line_ports[0].getCenterY();
        this.x2 = (int)line_ports[1].getCenterX();
        this.y2 = (int)line_ports[1].getCenterY();
    }

    public boolean inside(Point point) {
        if((point.x >= this.x1 && point.x <= this.x2) && (point.y >= this.y1 && point.y<= this.y2))
            return true;
        return false;
    }

    public Port getPorts(int portIndex) {
        return line_ports[portIndex];
    }
}
