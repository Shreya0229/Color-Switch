package GamePlay;

import ClassesAll.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import GameOverScreen.GameOverScreenController;
import ClassesAll.GameVariables;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.nio.file.Paths;

public class Main extends Application {

    public static MediaPlayer mediaP;


    public static int load_saved_game=0;
    Pane canvas=new Pane();
    Ball b=new Ball(250,550);
    TextArea textArea = new TextArea();
    ArrayList<Components> obs=new ArrayList<>();
    int state=0;//0-not running/paused,1-running,-1-terminated
    int fps=40;
    int X_dim=500;
    int Y_dim=610;
    Timeline gamevent;
    public static int total_stars=0;
    int start_count=0;
    Random rand = new Random();
    boolean flag=false;
    Components spawned=null;
    boolean hold=false;
    Color yellow=Color.YELLOW;
    Color deep_pink=Color.web("0xff0080");
    Color purple=Color.web("0x8c13fb");
    Color light_blue=Color.web("0x35e2f2");

        public void initialize() {
            CircleObs c=new CircleObs();
            c.intialize();
            c.add(canvas);
            c.play();
            obs.add(c);
            draw();
        }
    public void checkCollision(Stage stage) {
        for(int j=0;j<obs.size();j++) {
            Components c=obs.get(j);
            if(c.checkCollision(b.getBall())) {
                if(c instanceof Obstacles) {
                    //GameOver
//                    GameOverScreenController.setScoreTextArea();
                    final Stage stage1;
                    FXMLLoader pane;
                    Parent taskselectwindow = null;
                    pane = new FXMLLoader(getClass().getResource("/GameOverScreen/GameOverScreen.fxml"));
                    try {
                        taskselectwindow = (Parent) pane.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage1 = new Stage();
                    stage1.setScene(new Scene(taskselectwindow));
                    stage1.setTitle("Color Switch");
                    stage1.show();
                    stage.close();
                    return;
                }
                if(c instanceof Star) {
                    //StartCount
                    start_count=start_count+1;
                    total_stars=total_stars+1;
                    textArea.setText(""+start_count);
                    GameOverScreenController.currScoreVar=start_count;
                    c.remove(canvas);
                    obs.remove(j);
                    j--;
                    return;
                }
                if(c instanceof ColorSwitchBall) {
                    //ChangeColor
                    int op=rand.nextInt(3)+1;
                    int curr=b.getColor_num();
                    while(op==curr) {
                        op=rand.nextInt(3)+1;
                    }
                    if(op==1) {
                        b.setColor(yellow);
                        b.setColor_num(1);

                    }else if(op==2) {
                        b.setColor(deep_pink);
                        b.setColor_num(2);
                    }else if(op==3) {
                        b.setColor(purple);
                        b.setColor_num(3);
                    }else if(op==4) {
                        b.setColor(light_blue);
                        b.setColor_num(4);
                    }
                    c.remove(canvas);
                    obs.remove(j);
                    j--;
                    return;
                }
            }
        }
    }

    @Override
        public void start(Stage stage) throws Exception {
            // TODO Auto-generated method stub

//            putMusic();
            EventHandler<ActionEvent> gamecontrol=new EventHandler<>() {

                public void handle(ActionEvent arg0) {
                    // TODO Auto-generated method stub
                    checkCollision(stage);
                    if(b.getlevel()<-150) {
                        flag=true;
                    }
                    if(spawned==null ||spawned.getlevel()>225) {
                        hold=false;
                    }
                    if(!hold) {
                        draw();
                    }
                    if(flag) {
                        for(Components o:obs) {
                            o.move();
                        }
                        flag=false;
                    }

                    for(int j=0;j<obs.size();j++) {
                        Components o=obs.get(j);
                        if(o.getlevel()>800) {
                            if(o instanceof Obstacles) {
                                Obstacles a=(Obstacles)o;
                                a.getRotate().stop();
                            }
                            //o.rotate.stop();
                            obs.remove(j);
                            o.remove(canvas);

                            j--;
                            //draw();
                        }
                    }
                    b.move();

                }

            };
            EventHandler<MouseEvent> mouseclick=new EventHandler<>() {

                @Override
                public void handle(MouseEvent arg0) {
                    // TODO Auto-generated method stub
                    if(state==0) {
                        gamevent.play();
                        state=1;
                    }
                    if(!flag) {
                        b.movebymouseclick();
                    }

                }

            };


//......starts
            FileInputStream input = new FileInputStream("src/ImagesAll/pause.png");
            Image image = new Image(input, 70, 70, true, true);
            ImageView imageView = new ImageView(image);


            Button btnSave = new Button( "",imageView);
            btnSave.setStyle("-fx-background-color: #272727");

            btnSave.setMaxSize(10, 10);


            btnSave.setOnAction(event -> {
                load_saved_game=1;

                try {
                    pause();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Stage stage1;
                FXMLLoader pane;
                Parent taskselectwindow = null;
                pane = new FXMLLoader(getClass().getResource("/PauseScreen/PauseScreen.fxml"));
                try {
                    taskselectwindow = (Parent) pane.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage1 = new Stage();
                stage1.setScene(new Scene(taskselectwindow));
                stage1.setTitle("Color Switch");
                stage1.show();
                stage.close();
                return;
            });


            VBox vbox = new VBox(10, btnSave);
            vbox.setAlignment(Pos.TOP_RIGHT);
            vbox.setLayoutX(380);
            vbox.setLayoutY(20);
            vbox.setPadding(new Insets(10, 20, 10, 20));
            vbox.setStyle("-fx-background-color: #272727");




            textArea.setText(""+start_count);

            textArea.setStyle("-fx-font-size: 4em;"+"-fx-text-inner-color: black;");
            textArea.setDisable(true);
            textArea.setMaxSize(4,3);

            textArea.setLayoutX(35);
            textArea.setLayoutY(30);


//....ends

//            final Group root2= new Group(canvas , vbox, textArea);

            canvas.getChildren().addAll(vbox, textArea);

            gamevent=new Timeline(new KeyFrame(Duration.millis(fps),gamecontrol));
            gamevent.setCycleCount(Timeline.INDEFINITE);



        if(load_saved_game==1) {
            resume();
        }else {
            initialize();
            b.add(canvas);
        }


        stage.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseclick);
        Scene scene=new Scene(canvas,X_dim,Y_dim, Color.BLACK);
//        scene.setFill(Color.web("0x272727"));
        stage.setTitle("Color Switch");


        EventHandler<KeyEvent> key=new EventHandler<>() {

            @Override
            public void handle(KeyEvent arg0) {
                // TODO Auto-generated method stub

                try {
                    pause();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.exit(0);
            }

        };

        stage.addEventFilter(KeyEvent.KEY_PRESSED, key);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
//
//        Region region = ( Region ) textArea.lookup( ".content" );
//        region.setBackground( new Background( new BackgroundFill( Color.web("0x272727"), CornerRadii.EMPTY, Insets.EMPTY ) ) );
        }
    public void draw() {
        hold=true;
        int op=rand.nextInt(6)+1;
        Components b=null;
        if(op==1) {
            //System.out.println("Circle created");
            b=new CircleObs();
        }else if(op==2) {
            //System.out.println("Triangle created");
            b=new TriangleObs();
        }else if(op==3) {
            //System.out.println("Cross created");
            b=new CrossObs();
        }else if(op==4) {
            //System.out.println("Square created");
            b=new SquareObs();
        }else if(op==5) {
            b=new Star();
        }else if(op==6) {
            b=new ColorSwitchBall();
        }
        b.add(canvas);
        if(b instanceof Obstacles) {
            Obstacles c=(Obstacles)b;
            c.play();

        }

        obs.add(b);
        spawned=b;
    }

    public void resume() throws FileNotFoundException, ClassNotFoundException, IOException {
        GameVariables g=GameVariables.deserialize();
        canvas=new Pane();
        b.getBall().setLayoutY(g.getBall_layout_Y());
        b.add(canvas);
        //variables load
        flag=g.isFlag();
        hold=g.isHold();
        start_count=g.getStar_count();
        state=g.getState();
        load_saved_game=0;
        int spawned_idx=g.getSpawned_idx();
        String type=g.getType();


        //components
        ArrayList<Double> stars=g.getStars();
        ArrayList<Double> squares=g.getSquares();
        ArrayList<Double> circles=g.getCircles();
        ArrayList<Double> triangles=g.getTriangles();
        ArrayList<Double> cross=g.getCross();
        ArrayList<Double> colorswitchball=g.getColorswitchball();

        for(int j=0;j<stars.size();j=j+2) {
            Star s=new Star();
            s.setlevel(stars.get(j+1));
            s.add(canvas);
            obs.add(s);
            if(type.contentEquals("Stars") && spawned_idx==j) {
                spawned=s;
            }
        }
        for(int j=0;j<squares.size();j=j+8) {
            SquareObs s=new SquareObs();
            //s.getline().setLayoutX(squares.get(j));
            s.getline().setLayoutY(squares.get(j+1));

            //s.getline1().setLayoutX(squares.get(j+2));
            s.getline1().setLayoutY(squares.get(j+3));

            //s.getline2().setLayoutX(squares.get(j+4));
            s.getline2().setLayoutY(squares.get(j+5));

            //s.getline3().setLayoutX(squares.get(j+6));
            s.getline3().setLayoutY(squares.get(j+7));


            s.add(canvas);
            obs.add(s);
            s.play();
            if(type.contentEquals("Squares") && spawned_idx==j) {
                spawned=s;
            }

        }
        for(int j=0;j<cross.size();j=j+8) {
            //System.out.println(j);
            CrossObs s=new CrossObs();
            //s.getline().setLayoutX(cross.get(j));
            s.getline().setLayoutY(cross.get(j+1));

            //s.getline1().setLayoutX(cross.get(j+2));
            s.getline1().setLayoutY(cross.get(j+3));

            //s.getline2().setLayoutX(cross.get(j+4));
            s.getline2().setLayoutY(cross.get(j+5));

            //s.getline3().setLayoutX(cross.get(j+6));
            s.getline3().setLayoutY(cross.get(j+7));


            s.add(canvas);
            obs.add(s);
            s.play();
            //System.out.println(type.contentEquals("Cross"));
            if(type.contentEquals("Cross") && spawned_idx==j) {
                //System.out.println("check");
                spawned=s;
            }

        }
        for(int j=0;j<triangles.size();j=j+6) {
            TriangleObs s=new TriangleObs();
            //s.getline().setLayoutX(triangles.get(j));
            s.getline().setLayoutY(triangles.get(j+1));

            //s.getline1().setLayoutX(triangles.get(j+2));
            s.getline1().setLayoutY(triangles.get(j+3));

            //s.getline2().setLayoutX(triangles.get(j+4));
            s.getline2().setLayoutY(triangles.get(j+5));

            s.add(canvas);
            obs.add(s);
            s.play();
            if(type.contentEquals("Triangles") && spawned_idx==j) {
                spawned=s;
            }

        }
        for(int j=0;j<circles.size();j=j+8) {
            CircleObs s=new CircleObs();
            //s.getarc().setLayoutX(circles.get(j));
            s.getarc().setLayoutY(circles.get(j+1));

            //s.getarc1().setLayoutX(circles.get(j+2));
            s.getarc1().setLayoutY(circles.get(j+3));

            //s.getarc2().setLayoutX(circles.get(j+4));
            s.getarc2().setLayoutY(circles.get(j+5));

            //s.getarc3().setLayoutX(circles.get(j+6));
            s.getarc3().setLayoutY(circles.get(j+7));


            s.add(canvas);
            obs.add(s);
            s.play();
            if(type.contentEquals("Circles") && spawned_idx==j) {
                spawned=s;
            }

        }
        for(int j=0;j<colorswitchball.size();j=j+8) {
            ColorSwitchBall s=new ColorSwitchBall();
            //s.getarc().setLayoutX(circles.get(j));
            s.getarc().setLayoutY(colorswitchball.get(j+1));

            //s.getarc1().setLayoutX(circles.get(j+2));
            s.getarc1().setLayoutY(colorswitchball.get(j+3));

            //s.getarc2().setLayoutX(circles.get(j+4));
            s.getarc2().setLayoutY(colorswitchball.get(j+5));

            //s.getarc3().setLayoutX(circles.get(j+6));
            s.getarc3().setLayoutY(colorswitchball.get(j+7));

            s.add(canvas);
            obs.add(s);
            if(type.contentEquals("ColorSwitchBall") && spawned_idx==j) {
                spawned=s;
            }

        }
        gamevent.play();

    }


    public void pause() throws FileNotFoundException, IOException {
        GameVariables g=new GameVariables();
        //ball save
        g.setBall_center_X(b.getBall().getCenterX());
        g.setBall_center_X(b.getBall().getCenterY());
        g.setBall_layout_X(b.coordX());
        g.setBall_layout_Y(b.getlevel());
        g.setBall_color(b.getBall().getFill().toString());

        //variables save
        g.setFlag(flag);
        g.setHold(hold);
        g.setStar_count(start_count);
        g.setState(state);
        g.setLoad_saved_game(1);//saving bit

        //components
        ArrayList<Double> stars=new ArrayList<>();
        ArrayList<Double> squares=new ArrayList<>();
        ArrayList<Double> circles=new ArrayList<>();
        ArrayList<Double> triangles=new ArrayList<>();
        ArrayList<Double> cross=new ArrayList<>();
        ArrayList<Double> colorswitchball=new ArrayList<>();
        for(int i=0;i<obs.size();i++) {
            Components c=obs.get(i);
            if(c instanceof Star) {

                Star t=(Star)c;
                if(t==spawned) {
                    g.setSpawned_idx(stars.size());
                    g.setType("Stars");
                }
                stars.add(t.layout_X());
                stars.add(t.getlevel());

            }else if(c instanceof ColorSwitchBall ) {
                ColorSwitchBall t=(ColorSwitchBall)c;
                if(t==spawned) {
                    g.setSpawned_idx(colorswitchball.size());
                    g.setType("ColorSwitchBall");
                }
                colorswitchball.add(t.getarc().getLayoutX());
                colorswitchball.add(t.getarc().getLayoutY());

                colorswitchball.add(t.getarc1().getLayoutX());
                colorswitchball.add(t.getarc1().getLayoutY());

                colorswitchball.add(t.getarc2().getLayoutX());
                colorswitchball.add(t.getarc2().getLayoutY());

                colorswitchball.add(t.getarc3().getLayoutX());
                colorswitchball.add(t.getarc3().getLayoutY());

            }else if(c instanceof TriangleObs) {
                TriangleObs t=(TriangleObs)c;
                if(t==spawned) {
                    g.setSpawned_idx(triangles.size());
                    g.setType("Triangles");
                }
                triangles.add(t.getline().getLayoutX());
                triangles.add(t.getline().getLayoutY());

                triangles.add(t.getline1().getLayoutX());
                triangles.add(t.getline1().getLayoutY());

                triangles.add(t.getline2().getLayoutX());
                triangles.add(t.getline2().getLayoutY());
            }else if(c instanceof CircleObs) {
                CircleObs t=(CircleObs)c;
                if(t==spawned) {
                    g.setSpawned_idx(circles.size());
                    g.setType("Circles");
                }
                circles.add(t.getarc().getLayoutX());
                circles.add(t.getarc().getLayoutY());

                circles.add(t.getarc1().getLayoutX());
                circles.add(t.getarc1().getLayoutY());

                circles.add(t.getarc2().getLayoutX());
                circles.add(t.getarc2().getLayoutY());

                circles.add(t.getarc3().getLayoutX());
                circles.add(t.getarc3().getLayoutY());
            }else if(c instanceof SquareObs) {
                SquareObs t=(SquareObs)c;
                if(t==spawned) {
                    g.setSpawned_idx(squares.size());
                    g.setType("Squares");
                }
                squares.add(t.getline().getLayoutX());
                squares.add(t.getline().getLayoutY());

                squares.add(t.getline1().getLayoutX());
                squares.add(t.getline1().getLayoutY());

                squares.add(t.getline2().getLayoutX());
                squares.add(t.getline2().getLayoutY());

                squares.add(t.getline3().getLayoutX());
                squares.add(t.getline3().getLayoutY());

            }else if(c instanceof CrossObs) {
                CrossObs t=(CrossObs)c;
                if(t==spawned) {
                    g.setSpawned_idx(cross.size());
                    g.setType("Cross");
                }
                cross.add(t.getline().getLayoutX());
                cross.add(t.getline().getLayoutY());

                cross.add(t.getline1().getLayoutX());
                cross.add(t.getline1().getLayoutY());

                cross.add(t.getline2().getLayoutX());
                cross.add(t.getline2().getLayoutY());

                cross.add(t.getline3().getLayoutX());
                cross.add(t.getline3().getLayoutY());
            }
        }
        g.setCircles(circles);
        g.setColorswitchball(colorswitchball);
        g.setCross(cross);
        g.setTriangles(triangles);
        g.setSquares(squares);
        g.setStars(stars);

        GameVariables.serialize(g);
    }


    public void putMusic(){
        String destination= "src/ImagesAll/MainSound.mp3";
        Media sound = new Media(Paths.get(destination).toUri().toString());
        mediaP = new MediaPlayer(sound);
        mediaP.setAutoPlay(true);
        mediaP.setCycleCount(MediaPlayer.INDEFINITE);
        mediaP.setStartTime(Duration.seconds(0));
        mediaP.setStopTime(Duration.seconds(30));
        mediaP.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}