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

public class Main_menu_controller implements Initializable,Displayable {
    @FXML
    AnchorPane rootPane;
    @FXML
    Button exit;

    private MediaPlayer buttonPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void Start_game() throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        displayScreen("player_profile.fxml");
    }

    public void show_leader_board() throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        displayScreen("Leaderboard.fxml");
    }

    public void How_to_play() throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        displayScreen("play_rules.fxml");
    }

    public void Exit() throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        Stage stage = (Stage) exit.getScene().getWindow();
        ColorSwitchApp.getObj().quitGame();
        ColorSwitchApp.serialize();
        stage.close();
    }

    public void about_us() throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        displayScreen("about_us.fxml");
    }

    public void support_us() throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        displayScreen("support_us.fxml");
    }

    @Override
    public void displayScreen(String fxmlFileName) throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        AnchorPane pane= FXMLLoader.load(getClass().getResource(fxmlFileName));
        rootPane.getChildren().addAll(pane);
    }
}
