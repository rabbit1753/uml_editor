package Editor_shape;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

public class Group extends Shape {
    private List<Shape> shapes = new ArrayList<Shape>();
    private Rectangle group_rec = new Rectangle();

    public void draw(Graphics g) {
        for(int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            shape.draw(g);
        }
    }

    public boolean inside(Point point) {
        if((point.x >= this.x1 && point.x <= this.x2) && (point.y >= this.y1 && point.y<= this.y2))
            return true;
        return false;
    }

    public void reLocation(int move_X, int move_Y) {
        for(int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            shape.reLocation(move_X, move_Y);
        }
        this.x1 = this.x1 + move_X;
        this.y1 = this.y1 + move_Y;
        this.x2 = this.x1 + group_rec.width;
        this.y2 = this.y1 + group_rec.height;
        group_rec.x = this.x1;
        group_rec.y = this.y1;
    }
    
    public void show(Graphics g) {
        int alpha = 80;

        g.setColor(new Color(30,30,100, alpha));
        g.fillRect(group_rec.x, group_rec.y, group_rec.width, group_rec.height);
        g.setColor(new Color(30,30,100));
        g.drawRect(group_rec.x, group_rec.y, group_rec.width, group_rec.height);
    }

    public void setBounds() {
        Point right_down = new Point(0 ,0);
        Point left_upper = new Point(1200, 700);

        for(int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            if(shape.getx1() < left_upper.x) {
                left_upper.x = shape.getx1();
            }
            if(shape.gety1() < left_upper.y) {
                left_upper.y = shape.gety1();
            }
            if(shape.getx2() > right_down.x) {
                right_down.x = shape.getx2();
            }
            if(shape.gety2() > right_down.y) {
                right_down.y = shape.gety2();
            }
        }
        group_rec.setBounds(left_upper.x, left_upper.y, Math.abs(right_down.x - left_upper.x), Math.abs(right_down.y - left_upper.y));
        this.x1 = group_rec.x;
        this.y1 = group_rec.y;
        this.x2 = group_rec.x + group_rec.width;
        this.y2 = group_rec.y + group_rec.height;
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    public List<Shape> get_Shapelist() {
        return shapes;
    }
}
