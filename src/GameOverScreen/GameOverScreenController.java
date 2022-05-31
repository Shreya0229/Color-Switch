package GameOverScreen;

import GamePlay.Main;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class GameOverScreenController {

    public static int currScoreVar;
    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorScreen;

    @FXML
    private ImageView replayGameButton;

    @FXML
    private ImageView homeButton;

    @FXML
    private static TextArea scoreTextArea;

    @FXML
    private static TextArea BestscoreTextArea;

    public static void setScoreTextArea(){
        scoreTextArea.setText(currScoreVar+"");
        return;
    }

    @FXML
    private ImageView useStarsButton;

    public void goToHomeScreen(javafx.scene.input.MouseEvent mouseEvent)  throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MainScreen/MainScreen.fxml"));
        Scene scene = homeButton.getScene();
        root.translateYProperty().set(scene.getHeight());
        stackPane.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event1 -> {
            stackPane.getChildren().remove(anchorScreen);
        });
        timeline.play();

    }

    public void goToGamePlay(javafx.scene.input.MouseEvent mouseEvent) throws Exception {
        stackPane.getChildren().remove(anchorScreen);
        stackPane.setVisible(false);
        GamePlay.Main obj= new GamePlay.Main();
        obj.start(new Stage());
    }

    public void reduceStars(javafx.scene.input.MouseEvent mouseEvent) throws Exception {
        if(Main.total_stars>5){
            stackPane.getChildren().remove(anchorScreen);
            stackPane.setVisible(false);
            GamePlay.Main obj= new GamePlay.Main();
            obj.start(new Stage());
            GamePlay.Main.load_saved_game=0;
        }
    }
}
