import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class load_game_controller implements Initializable,Loadable {
    @FXML
    AnchorPane rootPane;
    public ListView<String> game_list_view;
    int ind;
    String game;
    ObservableList list= FXCollections.observableArrayList();

    ArrayList<String> a;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        a=new ArrayList<String>();
        Test.getObj().getSaveListOfCurrentPlayer(a);
        loadData();  //to be called from our main program....

    }
    @Override
    public void loadData()
    {
        list.remove(list);
        list.addAll(a);
        game_list_view.getItems().addAll(list);
    }

    public void selected() {
        game= game_list_view.getSelectionModel().getSelectedItem();
        ind=game_list_view.getSelectionModel().getSelectedIndex();
    }

    public void Start() {
        if(game==null || game.isEmpty())
        {
            Stage window=new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Game not Selected");
            Label label= new Label();
            label.setText("Game not Selected!!\n");
            Button close=new Button("Return ");
            close.setOnAction(e->window.close());
            VBox layout=new VBox(20);
            layout.getChildren().addAll(label,close);
            layout.setAlignment(Pos.CENTER);
            Scene scene=new Scene(layout,200,100);
            window.setScene(scene);
            window.showAndWait();
        }
        else
        {

        }

    }

    public void Delete() {
        if(game==null || game.isEmpty())
        {
            Stage window=new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Game not Selected");
            Label label= new Label();
            label.setText("Game not Selected!!\n");
            Button close=new Button("Return ");
            close.setOnAction(e->window.close());
            VBox layout=new VBox(20);
            layout.getChildren().addAll(label,close);
            layout.setAlignment(Pos.CENTER);
            Scene scene=new Scene(layout,200,100);
            window.setScene(scene);
            window.showAndWait();
        }
        else
        {
            game_list_view.getItems().remove(ind);
            a.remove(ind);
            Test.getObj().removeSavedGame(ind);
            game=null;
        }
    }

    public void back() throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        AnchorPane pane= FXMLLoader.load(getClass().getResource("existing_players.fxml"));
        rootPane.getChildren().addAll(pane);
    }
}
