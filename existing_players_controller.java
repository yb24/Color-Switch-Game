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

public class existing_players_controller implements Initializable,Loadable {
    public ListView<String> player_list_view;
    String player;
    int ind;
    static int index;
    ObservableList list= FXCollections.observableArrayList();

   /* Player_info selected_player;
    ArrayList<Player_info> all_players;
    SuperSystem s;*/

    @FXML
    Button new_game_button;
    @FXML
    AnchorPane rootPane;

    ArrayList<String> a;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        a = new ArrayList<>();
        Test.getObj().getAllPlayersString(a);
        loadData();  //to be called from our main program....

    }
    @Override
    public void loadData()
    {
        list.remove(list);
        list.addAll(a);
        player_list_view.getItems().addAll(list);
    }

    public void selected() {
        player= player_list_view.getSelectionModel().getSelectedItem();
        ind=player_list_view.getSelectionModel().getSelectedIndex();
        index = ind;
        //selected_player=all_players.get(ind);
    }


    public void Delete() {
        if(player==null || player.isEmpty())
        {
            Stage window=new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Player not Selected");
            Label label= new Label();
            label.setText("Player not Selected!!\n");
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
            player_list_view.getItems().remove(ind);
            a.remove(ind);
            Test.getObj().removePlayer(ind);
            //s.deletePlayer(selected_player);
            //selected_player=null;
            player=null;
        }
    }

    public void load_saved_game() throws IOException {
        if(player==null || player.isEmpty())
        {
            Stage window=new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Player not Selected");
            Label label= new Label();
            label.setText("Player not Selected!!\n");
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
            Test.getObj().selectCurrentPlayer(ind);
            rootPane.getChildren().clear();
            rootPane.getChildren().removeAll();
            AnchorPane pane= FXMLLoader.load(getClass().getResource("load_game.fxml"));
            rootPane.getChildren().addAll(pane);
        }
    }

    public void new_game() throws Exception {
        if(player==null || player.isEmpty())
        {
            Stage window=new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Player not Selected");
            Label label= new Label();
            label.setText("Player not Selected!!\n");
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
            Test.getObj().selectCurrentPlayer(ind);
            Test.getObj().newGame();
            Stage stage = (Stage) new_game_button.getScene().getWindow();
            stage.close();
        }
    }

    public void back() throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        AnchorPane pane= FXMLLoader.load(getClass().getResource("player_profile.fxml"));
        rootPane.getChildren().addAll(pane);
    }

    public void update_to_premium() throws IOException {
        if(player==null || player.isEmpty())
        {
            Stage window=new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Player not Selected");
            Label label= new Label();
            label.setText("Player not Selected!!\n");
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
            rootPane.getChildren().clear();
            rootPane.getChildren().removeAll();
            AnchorPane pane= FXMLLoader.load(getClass().getResource("premium_player_payment_existing_player.fxml"));
            rootPane.getChildren().addAll(pane);
        }
    }
}
