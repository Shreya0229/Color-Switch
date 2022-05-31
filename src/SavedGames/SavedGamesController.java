package SavedGames;

import ClassesAll.DataBase;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SavedGamesController {

    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane ScreenAnchor;

    @FXML
    private ImageView HomeButton;

    @FXML
    private Text load1;

    @FXML
    private Text load2;

    @FXML
    private Text load3;

    @FXML
    private Text load4;


    @FXML
    void open1(MouseEvent event) throws Exception {

        if(DataBase.savedGamesList.size()==1){
            DataBase.savedGamesList.get(1);
        }
        else{
            GamePlay.Main.load_saved_game=0;
            stackPane.getChildren().remove(ScreenAnchor);
            stackPane.setVisible(false);
            GamePlay.Main obj= new GamePlay.Main();
            obj.start(new Stage());
        }
    }

    @FXML
    void open2(MouseEvent event) throws Exception {
        if(DataBase.savedGamesList.size()==2){
            DataBase.savedGamesList.get(2);
        }
        else{
            GamePlay.Main.load_saved_game=0;
            stackPane.getChildren().remove(ScreenAnchor);
            stackPane.setVisible(false);
            GamePlay.Main obj= new GamePlay.Main();
            obj.start(new Stage());
        }
    }

    @FXML
    void open3(MouseEvent event) {
        if(DataBase.savedGamesList.size()==3){
            DataBase.savedGamesList.get(3);
        }
    }

    @FXML
    void open4(MouseEvent event) {
        if(DataBase.savedGamesList.size()==4){
            DataBase.savedGamesList.get(4);
        }
    }


    public void goToHomeScreen(javafx.scene.input.MouseEvent mouseEvent)  throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/MainScreen/MainScreen.fxml"));
        Scene scene = HomeButton.getScene();
        root.translateYProperty().set(scene.getHeight());
        stackPane.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event1 -> {
            stackPane.getChildren().remove(ScreenAnchor);
        });
        timeline.play();

    }
}
