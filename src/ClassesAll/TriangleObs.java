package ClassesAll;

import javafx.animation.Animation;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class TriangleObs extends Obstacles {
    private Line l=new Line();
    private Line l1=new Line();
    private Line l2=new Line();

    private Group triangle=new Group(l,l1,l2);
    private double x1=200;
    private double y1=250-300;
    private double x2=350;
    private double y2=250-300;
    private double x3=275;
    private double y3=120.09618943233-300;

    private double thickness=7.5;

    public TriangleObs() {
        l.setStartX(x1);
        l.setStartY(y1);

        l.setEndX(x2);
        l.setEndY(y2);

        l1.setStartX(x2);
        l1.setStartY(y2);

        l1.setEndX(x3);
        l1.setEndY(y3);

        l2.setStartX(x3);
        l2.setStartY(y3);

        l2.setEndX(x1);
        l2.setEndY(y1);

        l.setStroke(yellow);
        l1.setStroke(purple);
        l2.setStroke(light_blue);

        rotate.setAxis(Rotate.Z_AXIS);

        rotate.setByAngle(360*27);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setDuration(Duration.seconds(60));
        rotate.setNode(triangle);


        l.setStrokeWidth(thickness);
        l1.setStrokeWidth(thickness);
        l2.setStrokeWidth(thickness);

    }
    public void move() {
        l.setLayoutY(l.getLayoutY()+4);
        l1.setLayoutY(l1.getLayoutY()+4);
        l2.setLayoutY(l2.getLayoutY()+4);

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
        canvas.getChildren().add(triangle);
    }

    @Override
    public void remove(Pane canvas) {
        // TODO Auto-generated method stub
        canvas.getChildren().remove(triangle);
    }

    public Group getTriangle() {
        return triangle;
    }

    public void setTriangle(Group triangle) {
        this.triangle = triangle;
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



}