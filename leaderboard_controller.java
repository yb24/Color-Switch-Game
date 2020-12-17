import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class leaderboard_controller implements Initializable,Loadable,Displayable {
    @FXML
    AnchorPane rootPane;
    public ListView<String> leader_list_view;
    private final ObservableList list= FXCollections.observableArrayList();
    private ArrayList<String> a;
    private MediaPlayer buttonPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        a = ColorSwitchApp.getObj().getLeaderboard().getLeaderboardData();
        loadData();  //to be called from our main program....
    }
    @Override
    public void loadData()
    {
        list.remove(list);
        list.addAll(a);
        leader_list_view.getItems().addAll(list);
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
