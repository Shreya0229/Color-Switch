package ClassesAll;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Components {

    private Color c=Color.YELLOW;
    private Circle ball;
    private double dy=4;
    private int color_num=1;
    public Ball(int x,int y) {
        p=new Position(x,y);
        setBall(new Circle(10,c));
        ball.setCenterX(x);
        ball.setCenterY(y);

    }
    public void move() {
        ball.setLayoutY(ball.getLayoutY() + dy);
        if(Math.abs(dy)<4){
            dy = 4;
        }

        if(dy>0){//down
            dy =dy + 0.07*dy;
        }
        else{//up
            dy = dy-(0.09)*dy;
        }
    }
    public void movebymouseclick() {
        // TODO Auto-generated method stub
        ball.setLayoutY(ball.getLayoutY() -25);
        if(dy>0) {//down
            dy = -5;
        }else {//up
            dy =dy-0.2;
        }
    }
    public void add(Pane canvas) {
        // TODO Auto-generated method stub
        canvas.getChildren().add(ball);
    }
    public void remove(Pane canvas) {
        // TODO Auto-generated method stub
        canvas.getChildren().remove(ball);
    }

    public Color getC() {
        return c;
    }

    public void setColor(Color c) {
        this.c = c;
        this.ball.setFill(c);
    }

    public Circle getBall() {
        return ball;
    }

    public void setBall(Circle ball) {
        this.ball = ball;
    }
    public double coordX() {
        return ball.getLayoutX();
    }
    public double getlevel() {
        return ball.getLayoutY();
    }

    public double getDy() {
        return dy;
    }
    public boolean checkCollision(Circle ball) {
        return false;
    }
    public int getColor_num() {
        return color_num;
    }
    public void setColor_num(int color_num) {
        this.color_num = color_num;
    }


}