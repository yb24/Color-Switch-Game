import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class player_selector_controller implements Initializable,Displayable {
    @FXML
    AnchorPane rootPane;
    private MediaPlayer buttonPlayer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void Existing_player() throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        displayScreen("existing_players.fxml");
    }

    public void New_Player() throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        displayScreen("new_player.fxml");
    }

    public void back() throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        displayScreen("main_menu.fxml");
    }

    @Override
    public void displayScreen(String fxmlFileName) throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        AnchorPane pane= FXMLLoader.load(getClass().getResource(fxmlFileName));
        rootPane.getChildren().addAll(pane);
    }
}
