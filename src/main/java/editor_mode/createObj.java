package editor_mode;

import java.awt.event.MouseEvent;

import Editor_shape.Basic_Obj;

public class createObj extends Mode {

    private String objType_name = null;
    private ShapeFactory Factory = new ShapeFactory();

    public createObj(String objType_name) {
        this.objType_name = objType_name;

    }

    public void mousePressed(MouseEvent e) {
        Basic_Obj new_obj = null;

        new_obj = Factory.createObject(this.objType_name, e);

        canvas.addShape(new_obj); // Mode has canvas variable, so here can use
        canvas.repaint();
    }

}