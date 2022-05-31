package ClassesAll;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Star extends Components {
    Polygon polygon = new Polygon();
    double x=51;
    double y=-150;
    Group star=new Group(polygon);
    public Star() {
        polygon.getPoints().addAll(
                300.0-x,97.0+y,
                292.5-x,115.0+y,
                275.0-x,115.0+y,
                290.0-x,127.5+y,
                285.0-x,150.0+y,
                300.0-x,135.0+y,
                315.0-x,150.0+y,
                310.0-x,127.5+y,
                325.0-x,115.0+y,
                307.5-x,115.0+y

        );
        polygon.setFill(Color.WHITE);
    }
    public void move() {
        polygon.setLayoutY(polygon.getLayoutY()+4);
    }
    public void add(Pane canvas) {
        canvas.getChildren().add(polygon);
    }
    public void remove(Pane canvas) {
        canvas.getChildren().remove(polygon);
    }
    public double getlevel() {
        return polygon.getLayoutY();
    }
    public boolean checkCollision(Circle ball) {
        Shape s=Shape.intersect(ball,polygon);

        //Point2D p=new Point2D(ball.getCenterX(),ball.getCenterY());

        if(!s.getBoundsInLocal().isEmpty()) {
            return true;

        }

        return false;
    }

    public void setlevel(double d) {
        polygon.setLayoutY(d);
    }
    public void setX(double d) {
        polygon.setLayoutX(d);
    }
    public double layout_X(){
        return polygon.getLayoutX();
    }

}