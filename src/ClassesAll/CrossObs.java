package ClassesAll;

import javafx.animation.Animation;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class CrossObs extends Obstacles{
    private Line l=new Line();
    private Line l1=new Line();
    private Line l2=new Line();
    private Line l3=new Line();

    private double x1=300;
    private double y1=200-300;
    private double x2=200;
    private double y2=200-400;
    private double x3=100;
    private double y3=200-300;
    private double x4=200;
    private double y4=0;
    private double x5=200;
    private double y5=200-300;

    double thickness=7.5;

    private Group cross=new Group(l,l1,l2,l3);
    public CrossObs() {
        l.setStartX(x1);
        l.setStartY(y1);

        l.setEndX(x5);
        l.setEndY(y5);

        l1.setStartX(x2);
        l1.setStartY(y2);

        l1.setEndX(x5);
        l1.setEndY(y5);

        l2.setStartX(x3);
        l2.setStartY(y3);

        l2.setEndX(x5);
        l2.setEndY(y5);

        l3.setStartX(x4);
        l3.setStartY(y4);

        l3.setEndX(x5);
        l3.setEndY(y5);

        l.setStroke(yellow);
        l1.setStroke(deep_pink);
        l2.setStroke(purple);
        l3.setStroke(light_blue);

        l.setStrokeWidth(thickness);
        l1.setStrokeWidth(thickness);
        l2.setStrokeWidth(thickness);
        l3.setStrokeWidth(thickness);

        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360*27);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setDuration(Duration.seconds(60));
        rotate.setNode(cross);
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
        canvas.getChildren().add(cross);
    }

    @Override
    public void remove(Pane canvas) {
        // TODO Auto-generated method stub
        canvas.getChildren().remove(cross);
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        l.setLayoutY(l.getLayoutY()+4);
        l1.setLayoutY(l1.getLayoutY()+4);
        l2.setLayoutY(l2.getLayoutY()+4);
        l3.setLayoutY(l3.getLayoutY()+4);
    }
    @Override
    public double getlevel() {
        // TODO Auto-generated method stub
        return l.getLayoutY();
    }
    public boolean checkCollision(Circle ball) {
        Shape s=Shape.intersect(ball,l);
        Shape s1=Shape.intersect(ball,l1);
        Shape s2=Shape.intersect(ball,l2);
        Shape s3=Shape.intersect(ball,l3);
        //Point2D p=new Point2D(ball.getCenterX(),ball.getCenterY());

        if(!s.getBoundsInLocal().isEmpty() && l.getStroke()!=ball.getFill()) {
            return true;

        }
        else if(!s1.getBoundsInLocal().isEmpty() && l1.getStroke()!=ball.getFill()) {
            return true;

        }
        else if(!s2.getBoundsInLocal().isEmpty() && l2.getStroke()!=ball.getFill()){
            return true;

        }
        else if(!s3.getBoundsInLocal().isEmpty() && l3.getStroke()!=ball.getFill()) {
            return true;

        }
        return false;
    }

    public Line getline() {
        return l;
    }
    public Line getline1() {
        return l1;
    }
    public Line getline2() {
        return l2;
    }
    public Line getline3() {
        return l3;
    }

}