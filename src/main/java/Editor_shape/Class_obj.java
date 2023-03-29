package Editor_shape;

import java.awt.Color;
import java.awt.Graphics;


public class Class_obj extends Basic_Obj {

    public Class_obj(int x1, int y1, int depth) {
        this.width = 100;
        this.height = 120;
        this.depth = depth;
        this.x1 = x1;
        this.x2 = x1 + width;
        this.y1 = y1;
        this.y2 = y1 + height;
        createPort();
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x1, y1, width, height);
        for(int i = 1; i < 3; i++) {
            g.drawLine(x1, y1 + (height/3)*i, x2, y1 + (height/3)*i);
        }

        int stringWidth = g.getFontMetrics(font).stringWidth(obj_name);
        double empty = (Math.abs(x1-x2) - stringWidth) / 2; 
        g.setFont(font);
        g.drawString(obj_name, x1 + (int)empty, y1 + 25);
        // System.out.println("Draw Class");
    }
}
