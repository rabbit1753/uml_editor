package Editor_shape;

import java.awt.Point;
import java.awt.Graphics;

public abstract class Shape {

    protected int x1, x2, y1, y2;
    protected boolean selected = false;
    protected boolean group_selected = false;

    public abstract void draw(Graphics g);
    // public void draw_points(Graphics g) {};
    public void show(Graphics g) {};
    public abstract boolean inside(Point point);
    public void setObjName(String name) {}
    public void reLocation(int move_X, int move_Y) {}
    public void reLocation() {}
    public Port getPorts(int portIndex) {
        return null;
    }

    public int getx1() {
        return x1;
    }
    public int getx2() {
        return x2;
    }
    public int gety1() {
        return y1;
    }
    public int gety2() {
        return y2;
    }
    public boolean getSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setGroupSelected(boolean GroupSelected) {
        this.group_selected = GroupSelected;
    }
    public boolean getGroupSelected() {
        return group_selected;
    }
}
