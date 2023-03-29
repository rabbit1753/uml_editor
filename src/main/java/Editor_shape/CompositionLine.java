package Editor_shape;

import java.awt.Graphics;
import java.awt.Color;

public class CompositionLine extends Line {
    private int retangleW = 8, retangleH = 8;

    public CompositionLine(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);  

        int dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx*dx + dy*dy); // 線段的長度
		double xm = D - retangleW, xn = xm, ym = retangleH, yn = -retangleH, x;
		double sin = dy/D, cos = dx/D;
		
		x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        double xq = (retangleH*2/D)*x1 + ((D-retangleH*2)/D)*x2;
        double yq = (retangleH*2/D)*y1 + ((D-retangleH*2)/D)*y2;

        int[] xpoints = {x2, (int) xm, (int) xq, (int)xn};
        int[] ypoints = {y2, (int) ym, (int) yq, (int)yn};

        g.fillPolygon(xpoints, ypoints, 4);
    }
    
    
}
