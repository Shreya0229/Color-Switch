package ClassesAll;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class ColorSwitchBall extends Components{
    double CenterX=250;
    double CenterY=-25;
    double Radius=17;
    double arc_length=90;
    double angle=90;

    Arc arc=new Arc(CenterX,CenterY,Radius,Radius,angle*0,arc_length);
    Arc arc1=new Arc(CenterX,CenterY,Radius,Radius,angle*1,arc_length);
    Arc arc2=new Arc(CenterX,CenterY,Radius,Radius,angle*2,arc_length);
    Arc arc3=new Arc(CenterX,CenterY,Radius,Radius,angle*3,arc_length);

    Group circle_color=new Group(arc,arc1,arc2,arc3);



    public ColorSwitchBall() {
        arc.setType(ArcType.ROUND);
        arc.setFill(yellow);

        arc1.setType(ArcType.ROUND);
        arc1.setFill(deep_pink);

        arc2.setType(ArcType.ROUND);
        arc2.setFill(purple);

        arc3.setType(ArcType.ROUND);
        arc3.setFill(light_blue);
    }
    public void move() {
        // TODO Auto-generated method stub
        arc.setLayoutY(arc.getLayoutY()+4);
        arc1.setLayoutY(arc1.getLayoutY()+4);
        arc2.setLayoutY(arc2.getLayoutY()+4);
        arc3.setLayoutY(arc3.getLayoutY()+4);

    }
    public double getlevel() {
        return arc.getLayoutY();
    }
    public void add(Pane canvas) {
        canvas.getChildren().add(circle_color);
    }
    public void remove(Pane canvas) {
        canvas.getChildren().remove(circle_color);
    }
    public boolean checkCollision(Circle ball) {
        Shape s=Shape.intersect(ball,arc);
        Shape s1=Shape.intersect(ball,arc1);
        Shape s2=Shape.intersect(ball,arc2);
        Shape s3=Shape.intersect(ball,arc3);
        //Point2D p=new Point2D(ball.getCenterX(),ball.getCenterY());

        if(!s.getBoundsInLocal().isEmpty()) {
            return true;

        }
        else if(!s1.getBoundsInLocal().isEmpty()) {
            return true;

        }
        else if(!s2.getBoundsInLocal().isEmpty()){
            return true;

        }
        else if(!s3.getBoundsInLocal().isEmpty()) {
            return true;

        }
        return false;
    }

    public Arc getarc() {
        return arc;
    }
    public Arc getarc1() {
        return arc1;
    }
    public Arc getarc2() {
        return arc2;
    }
    public Arc getarc3() {
        return arc3;
    }
}