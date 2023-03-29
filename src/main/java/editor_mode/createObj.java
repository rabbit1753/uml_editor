package editor_mode;

import java.awt.event.MouseEvent;

import Editor_shape.Class_obj;
import Editor_shape.Shape;
import Editor_shape.UserCase_obj;

public class createObj extends Mode {

    private String objType_name = null;
    public createObj(String objType_name) {
        this.objType_name = objType_name;
    }
    public void mousePressed(MouseEvent e) {
        Shape new_obj = null;

        if(objType_name == "class") {
            new_obj = new Class_obj(e.getX(), e.getY(), 0);
            System.out.println("create class");
        }
        else if(objType_name == "UserCase") {
            new_obj = new UserCase_obj(e.getX(), e.getY(), 0);
            System.out.println("creeate UserCase");
        }
        canvas.addShape(new_obj); // 從 Mode 那邊繼承了 canvas 變數過來;
        canvas.repaint();
    }

}