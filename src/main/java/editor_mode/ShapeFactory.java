package editor_mode;

import java.awt.event.MouseEvent;
import java.awt.Point;

import Editor_shape.Line;
import Editor_shape.Class_obj;
import Editor_shape.UseCase_obj;
import Editor_shape.AssociationLine;
import Editor_shape.Basic_Obj;
import Editor_shape.CompositionLine;
import Editor_shape.GenerationLine;


public class ShapeFactory {
    public Basic_Obj createObject(String objectType, MouseEvent e) {
        Basic_Obj newObject = null;

        if(objectType.equals("class")) {
            newObject = new Class_obj(e.getX(), e.getY(), 0);
            System.out.println("create class");
        }
        else if(objectType.equals("UserCase")) {
            newObject = new UseCase_obj(e.getX(), e.getY(), 0);
            System.out.println("create UserCase");
        }

        return newObject;
    }

    public Line createLine(String lineType, Point start, Point end) {
        Line newLine = null;

        if(lineType.equals("association")) {
            newLine = new AssociationLine(start.x, start.y, end.x, end.y);
            System.out.println("create association line");
        }
        else if(lineType == "generation") {
            newLine = new GenerationLine(start.x, start.y, end.x, end.y);
            System.out.println("create generation line");
        }
        else if(lineType == "composition") {
            newLine = new CompositionLine(start.x, start.y, end.x, end.y);
            System.out.println("create composition line");
        }

        return newLine;
    }
}
