package ClassesAll;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Components {
    protected Position p=null;
    protected Color yellow=Color.YELLOW;
    protected Color deep_pink=Color.web("0xff0080");
    protected Color purple=Color.web("0x8c13fb");
    protected Color light_blue=Color.web("0x35e2f2");
    public abstract void  add(Pane canvas);
    public abstract void  remove(Pane canvas);
    public abstract void  move();
    public abstract double getlevel();
    public abstract boolean checkCollision(Circle ball);
}