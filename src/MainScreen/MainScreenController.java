package MainScreen;

import javafx.animation.Interpolator;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.*;

import java.io.IOException;

public class MainScreenController {

    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane ScreenAnchor;

    @FXML
    private Text ResumeGameButton;

    @FXML
    private Text ExitGameButton;

    @FXML
    private ImageView PlayButton;

    @FXML
    public void startGamePlayImg(javafx.scene.input.MouseEvent mouseEvent) throws Exception {
        GamePlay.Main.load_saved_game=0;
        stackPane.getChildren().remove(ScreenAnchor);
        stackPane.setVisible(false);
        GamePlay.Main obj= new GamePlay.Main();
            obj.start(new Stage());

    }

    public void exitTheGame(javafx.scene.input.MouseEvent mouseEvent) {
        Platform.exit();
        System.exit(0);
    }

    public void goToSavedGames(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/SavedGames/SavedGames.fxml"));
        Scene scene = PlayButton.getScene();
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

