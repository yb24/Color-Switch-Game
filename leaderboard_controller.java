import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class leaderboard_controller implements Initializable,Loadable {
    @FXML
    AnchorPane rootPane;
    public ListView<String> leader_list_view;
    int ind;
    String leader;
    ObservableList list= FXCollections.observableArrayList();
    ArrayList<String> a;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        a = Test.getObj().getLeaderboard().getLeaderboardData();
        //a=new ArrayList<String>(1);
        //a.add("Einstein Score: 5");
        //a.add("Newton Score: 4");
        //a.add("Galileo Score: 2");
        //a.add("Einstein Score: 1");
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
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        AnchorPane pane= FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        rootPane.getChildren().addAll(pane);
    }
}
