package ClassesAll;

import javafx.animation.RotateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class   Obstacles extends Components{
    protected Position p=null;
    protected Color yellow=Color.YELLOW;
    protected Color deep_pink=Color.web("0xff0080");
    protected Color purple=Color.web("0x8c13fb");
    protected Color light_blue=Color.web("0x35e2f2");
    protected RotateTransition rotate = new RotateTransition();

    public RotateTransition getRotate(){
        return rotate;
    }
    public abstract void  play();
    public abstract void  pause();
    public abstract void  add(Pane canvas);
    public abstract void  remove(Pane canvas);
    public abstract void  move();
    public abstract double getlevel();
    public abstract boolean checkCollision(Circle ball);
}