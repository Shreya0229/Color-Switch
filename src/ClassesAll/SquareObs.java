package ClassesAll;

import javafx.animation.Animation;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class SquareObs extends Obstacles {

    private Line l=new Line();
    private Line l1=new Line();
    private Line l2=new Line();
    private Line l3=new Line();

    private Group square=new Group(l,l1,l2,l3);

    private double x1=150;
    private double y1=-250;
    private double x2=350;
    private double y2=-250;
    private double x3=150;
    private double y3=-50;
    private double x4=350;
    private double y4=-50;

    private double thickness=7.5;



    private double centerX=(x1+x3)/2;
    private double centerY=(y1+y3)/2;


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


    public SquareObs() {
        //draw l
        l.setStartX(x1);
        l.setStartY(y1);

        l.setEndX(x2);
        l.setEndY(y2);

        l1.setStartX(x2);
        l1.setStartY(y2);

        l1.setEndX(x4);
        l1.setEndY(y4);

        l2.setStartX(x3);
        l2.setStartY(y3);

        l2.setEndX(x4);
        l2.setEndY(y4);

        l3.setStartX(x3);
        l3.setStartY(y3);

        l3.setEndX(x1);
        l3.setEndY(y1);

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
        rotate.setNode(square);


    }
    public void move() {
        l.setLayoutY(l.getLayoutY()+4);
        l1.setLayoutY(l1.getLayoutY()+4);
        l2.setLayoutY(l2.getLayoutY()+4);
        l3.setLayoutY(l3.getLayoutY()+4);
    }
    public void play() {
        rotate.play();
    }
    public void pause() {
        rotate.pause();
    }
    public void add(Pane canvas) {
        canvas.getChildren().add(square);
    }
    public void remove(Pane canvas) {
        rotate.stop();
        canvas.getChildren().remove(square);
    }
    public double getCenterX() {
        return centerX;
    }
    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }
    public double getCenterY() {
        return centerY;
    }
    public void setCenterY(double centerY) {
        this.centerY = centerY;
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
}