import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class obstacle_touched_controller implements Initializable {
    @FXML
    AnchorPane rootPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void spend_and_continue() {
    }

    public void new_game() {
    }

    public void return_to_main_menu() throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        AnchorPane pane= FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        rootPane.getChildren().addAll(pane);
    }
}
