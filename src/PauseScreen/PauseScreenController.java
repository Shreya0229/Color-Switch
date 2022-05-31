package PauseScreen;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class PauseScreenController {
    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorScreen;

    @FXML
    private Rectangle restartButtonBox;

    @FXML
    private Text continueButton;

    @FXML
    private Text restartButton;

    @FXML
    private Text showSavedButton;

    @FXML
    private ImageView backButton;

    @FXML
    private ImageView homeScreen;


    @FXML
    public void goToGamePlayContinue(MouseEvent mouseEvent) throws Exception{
        stackPane.getChildren().remove(anchorScreen);
        stackPane.setVisible(false);
        GamePlay.Main obj= new GamePlay.Main();
        obj.start(new Stage());
        GamePlay.Main.load_saved_game=0;

    }

    public void goToGamePlay(javafx.scene.input.MouseEvent mouseEvent) throws Exception {
        GamePlay.Main.load_saved_game=0;
        stackPane.getChildren().remove(anchorScreen);
        stackPane.setVisible(false);
        GamePlay.Main obj= new GamePlay.Main();
        obj.start(new Stage());


    }


    public void goToHomeScreen(javafx.scene.input.MouseEvent mouseEvent)  throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/MainScreen/MainScreen.fxml"));
        Scene scene = homeScreen.getScene();
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

    public void goToSavedGames(javafx.scene.input.MouseEvent mouseEvent)  throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/SavedGames/SavedGames.fxml"));
        Scene scene = homeScreen.getScene();
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
}
