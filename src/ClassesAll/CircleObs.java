package ClassesAll;

import javafx.animation.Animation;
//import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class CircleObs extends Obstacles {
    double CenterX=250;
    double CenterY=-100;
    double Radius=80;
    double arc_length=90;
    double angle=90;

    Arc arc=new Arc(CenterX,CenterY,Radius,Radius,angle*0,arc_length);
    Arc arc1=new Arc(CenterX,CenterY,Radius,Radius,angle*1,arc_length);
    Arc arc2=new Arc(CenterX,CenterY,Radius,Radius,angle*2,arc_length);
    Arc arc3=new Arc(CenterX,CenterY,Radius,Radius,angle*3,arc_length);

    Group circle=new Group(arc,arc1,arc2,arc3);

    double thickness=10;

    public CircleObs() {
        arc.setFill(Color.TRANSPARENT);
        arc.setStrokeWidth(thickness);
        arc.setStroke(yellow);

        arc1.setFill(Color.TRANSPARENT);
        arc1.setStrokeWidth(thickness);
        arc1.setStroke(deep_pink);

        arc2.setFill(Color.TRANSPARENT);
        arc2.setStrokeWidth(thickness);
        arc2.setStroke(purple);

        arc3.setFill(Color.TRANSPARENT);
        arc3.setStrokeWidth(thickness);
        arc3.setStroke(light_blue);

        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360*27);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setDuration(Duration.seconds(60));
        rotate.setNode(circle);
    }

    @Override
    public void play() {
        // TODO Auto-generated method stub
        rotate.play();
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        rotate.pause();
    }

    @Override
    public void add(Pane canvas) {
        // TODO Auto-generated method stub
        canvas.getChildren().add(circle);
    }

    @Override
    public void remove(Pane canvas) {
        // TODO Auto-generated method stub
        canvas.getChildren().remove(circle);
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        arc.setLayoutY(arc.getLayoutY()+4);
        arc1.setLayoutY(arc1.getLayoutY()+4);
        arc2.setLayoutY(arc2.getLayoutY()+4);
        arc3.setLayoutY(arc3.getLayoutY()+4);

    }
    public void intialize() {
        arc.setCenterX(250.0);
        arc.setCenterY(300.0);

        arc1.setCenterX(250.0);
        arc1.setCenterY(300.0);

        arc2.setCenterX(250.0);
        arc2.setCenterY(300.0);

        arc3.setCenterX(250.0);
        arc3.setCenterY(300.0);
    }

    @Override
    public double getlevel() {
        // TODO Auto-generated method stub
        return arc.getLayoutY();
    }
    public boolean checkCollision(Circle ball) {
        Shape s=Shape.intersect(ball,arc);
        Shape s1=Shape.intersect(ball,arc1);
        Shape s2=Shape.intersect(ball,arc2);
        Shape s3=Shape.intersect(ball,arc3);
        //Point2D p=new Point2D(ball.getCenterX(),ball.getCenterY());

        if(!s.getBoundsInLocal().isEmpty() && arc.getStroke()!=ball.getFill()) {
            return true;

        }
        else if(!s1.getBoundsInLocal().isEmpty() && arc1.getStroke()!=ball.getFill()) {
            return true;

        }
        else if(!s2.getBoundsInLocal().isEmpty() && arc2.getStroke()!=ball.getFill()){
            return true;

        }
        else if(!s3.getBoundsInLocal().isEmpty() && arc3.getStroke()!=ball.getFill()) {
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