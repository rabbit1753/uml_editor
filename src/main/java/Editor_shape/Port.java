package Editor_shape;

import java.awt.Rectangle;
import java.util.List;
import java.util.ArrayList;

public class Port extends Rectangle {
    private List<Line> lines = new ArrayList<Line>();

    public void setPort(int center_x, int center_y, int pointsize) {
        int x = center_x - pointsize;
        int y = center_y - pointsize;
        int w = pointsize*2;
        int h = pointsize*2;
        setBounds(x, y, w, h);
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public void removeLine(Line line) {
        lines.remove(line);
    }

    public void resetLines() {
        for(int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);
            line.reLocation();
        }
    }
}
