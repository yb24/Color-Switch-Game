import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class pause_game_controller implements Initializable {
    @FXML
    AnchorPane rootPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void return_to_main_menu() throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        AnchorPane pane= FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        rootPane.getChildren().addAll(pane);
    }

    public void save_game() throws IOException {
        Test.getObj().saveGame();
    }

    public void return_to_game() throws IOException, ClassNotFoundException {
        //aryan
        //close old collision2 if not done yet
        Test.deserialize();
        //open new collision2
    }
}
