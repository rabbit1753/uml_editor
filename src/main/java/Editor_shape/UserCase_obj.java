package Editor_shape;

import java.awt.Graphics;
import java.awt.Color;

public class UserCase_obj extends Basic_Obj {
    

    public UserCase_obj(int x1, int y1, int depth) {
        this.width = 120;
        this.height = 80;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1 + width;
        this.y2 = y1 + height;
        createPort();
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawOval(x1, y1, width, height);
        // System.out.println("Draw UserCase");

        int stringWidth = g.getFontMetrics(font).stringWidth(obj_name);
        double empty = (Math.abs(x1-x2) - stringWidth) / 2; 
        g.setFont(font);
        g.drawString(obj_name, x1 + (int)empty, y1 + 45);
    }

}
