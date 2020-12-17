import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class pause_game_controller implements Initializable,Displayable {
    @FXML
    AnchorPane rootPane;
    @FXML
    Button returnToGame;
    private MediaPlayer buttonPlayer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColorSwitchApp.getMediaPlayer().play();
    }

    public void return_to_main_menu() throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        displayScreen("main_menu.fxml");
    }

    public void save_game() throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        ColorSwitchApp.getObj().saveGame();
        return_to_main_menu();
    }

    public void return_to_game() throws Exception {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        ColorSwitchApp.getMediaPlayer().stop();
        ColorSwitchApp.getObj().resumeGame(true);
        Stage stage = (Stage) returnToGame.getScene().getWindow();
        stage.close();
    }

    @Override
    public void displayScreen(String fxmlFileName) throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        AnchorPane pane= FXMLLoader.load(getClass().getResource(fxmlFileName));
        rootPane.getChildren().addAll(pane);
    }
}
