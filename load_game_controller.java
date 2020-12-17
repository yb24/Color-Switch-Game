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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class load_game_controller implements Initializable,Loadable,Displayable {
    @FXML
    AnchorPane rootPane;
    @FXML
    Button load_game;
    public ListView<String> game_list_view;
    int ind;
    String game;
    private MediaPlayer buttonPlayer;
    private final ObservableList list= FXCollections.observableArrayList();

    ArrayList<String> a;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        a= new ArrayList<>();
        ColorSwitchApp.getObj().getSaveListOfCurrentPlayer(a);
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

    public void Start() throws Exception {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
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
            window.initStyle(StageStyle.UNDECORATED);
            window.showAndWait();
        }
        else
        {
            ColorSwitchApp.getObj().loadGame(ind);
            Stage stage = (Stage) load_game.getScene().getWindow();
            stage.close();
        }

    }

    public void Delete() {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
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
            window.initStyle(StageStyle.UNDECORATED);
            window.showAndWait();
        }
        else
        {
            game_list_view.getItems().remove(ind);
            a.remove(ind);
            ColorSwitchApp.getObj().removeSavedGame(ind);
            game=null;
        }
    }

    public void back() throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        displayScreen("existing_players.fxml");
    }

    @Override
    public void displayScreen(String fxmlFileName) throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        AnchorPane pane= FXMLLoader.load(getClass().getResource(fxmlFileName));
        rootPane.getChildren().addAll(pane);
    }
}
