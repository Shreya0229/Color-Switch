package ClassesAll;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class GameVariables implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = -3599706612236618200L;

    public static void serialize(GameVariables g) throws FileNotFoundException, IOException {
        ObjectOutputStream out=null;
        out=new ObjectOutputStream(new FileOutputStream("out.txt"));
        out.writeObject(g);
        out.close();
    }
    public static GameVariables deserialize() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream in =null;
        in=new ObjectInputStream(new FileInputStream("out.txt"));
        GameVariables p=(GameVariables)in.readObject();
        in.close();
        return p;
    }

    //GameVars
    private boolean flag=false;
    private boolean hold=false;
    private int star_count=0;
    private int state=0;
    private int load_saved_game=0;
    private int spawned_idx=0;
    private String type="";

    //ball
    private double ball_center_X=0;
    private double ball_center_Y=0;
    private double ball_layout_X=0;
    private double ball_layout_Y=0;
    private String ball_color="";


    //components
    private ArrayList<Double> stars=new ArrayList<>();
    private ArrayList<Double> squares=new ArrayList<>();
    private ArrayList<Double> circles=new ArrayList<>();
    private ArrayList<Double> triangles=new ArrayList<>();
    private ArrayList<Double> cross=new ArrayList<>();
    private ArrayList<Double> colorswitchball=new ArrayList<>();

    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public int getStar_count() {
        return star_count;
    }
    public void setStar_count(int star_count) {
        this.star_count = star_count;
    }
    public boolean isHold() {
        return hold;
    }
    public void setHold(boolean hold) {
        this.hold = hold;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public double getBall_center_Y() {
        return ball_center_Y;
    }
    public void setBall_center_Y(double ball_center_Y) {
        this.ball_center_Y = ball_center_Y;
    }
    public double getBall_center_X() {
        return ball_center_X;
    }
    public void setBall_center_X(double ball_center_X) {
        this.ball_center_X = ball_center_X;
    }
    public double getBall_layout_X() {
        return ball_layout_X;
    }
    public void setBall_layout_X(double ball_layout_X) {
        this.ball_layout_X = ball_layout_X;
    }
    public String getBall_color() {
        return ball_color;
    }
    public void setBall_color(String ball_color) {
        this.ball_color = ball_color;
    }
    public double getBall_layout_Y() {
        return ball_layout_Y;
    }
    public void setBall_layout_Y(double ball_layout_Y) {
        this.ball_layout_Y = ball_layout_Y;
    }

    public int getLoad_saved_game() {
        return load_saved_game;
    }
    public void setLoad_saved_game(int load_saved_game) {
        this.load_saved_game = load_saved_game;
    }
    public ArrayList<Double> getStars() {
        return stars;
    }
    public void setStars(ArrayList<Double> stars) {
        this.stars = stars;
    }
    public ArrayList<Double> getTriangles() {
        return triangles;
    }
    public void setTriangles(ArrayList<Double> triangles) {
        this.triangles = triangles;
    }
    public ArrayList<Double> getSquares() {
        return squares;
    }
    public void setSquares(ArrayList<Double> squares) {
        this.squares = squares;
    }
    public ArrayList<Double> getCircles() {
        return circles;
    }
    public void setCircles(ArrayList<Double> circles) {
        this.circles = circles;
    }
    public ArrayList<Double> getCross() {
        return cross;
    }
    public void setCross(ArrayList<Double> cross) {
        this.cross = cross;
    }
    public ArrayList<Double> getColorswitchball() {
        return colorswitchball;
    }
    public void setColorswitchball(ArrayList<Double> colorswitchball) {
        this.colorswitchball = colorswitchball;
    }
    public int getSpawned_idx() {
        return spawned_idx;
    }
    public void setSpawned_idx(int spawned_idx) {
        this.spawned_idx = spawned_idx;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

}