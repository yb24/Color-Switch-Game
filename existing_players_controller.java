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

public class existing_players_controller implements Initializable,Loadable,Displayable {
    public ListView<String> player_list_view;
    private String player;
    private int ind;
    public static int index;
    private final ObservableList list= FXCollections.observableArrayList();

    @FXML
    Button new_game_button;
    @FXML
    AnchorPane rootPane;
    private MediaPlayer buttonPlayer;

    ArrayList<String> a;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        a = new ArrayList<>();
        ColorSwitchApp.getObj().getAllPlayersString(a);
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
    }


    public void Delete() throws UserNotFoundException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
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
            window.initStyle(StageStyle.UNDECORATED);
            window.showAndWait();
        }
        else
        {
            player_list_view.getItems().remove(ind);
            a.remove(ind);
            ColorSwitchApp.getObj().removePlayer(ind);
            //s.deletePlayer(selected_player);
            //selected_player=null;
            player=null;
        }
    }

    public void load_saved_game() throws IOException, UserNotFoundException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
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
            window.initStyle(StageStyle.UNDECORATED);
            window.showAndWait();
        }
        else
        {
            ColorSwitchApp.getObj().selectCurrentPlayer(ind);
            displayScreen("load_game.fxml");
        }
    }

    public void new_game() throws Exception {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
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
            window.initStyle(StageStyle.UNDECORATED);
            window.showAndWait();
        }
        else
        {
            ColorSwitchApp.getObj().selectCurrentPlayer(ind);
            ColorSwitchApp.getObj().newGame();
            Stage stage = (Stage) new_game_button.getScene().getWindow();
            stage.close();
        }
    }

    public void back() throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        displayScreen("player_profile.fxml");
    }

    public void update_to_premium() throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
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
            window.initStyle(StageStyle.UNDECORATED);
            window.showAndWait();
        }
        else
        {
            displayScreen("premium_player_payment_existing_player.fxml");
        }
    }

    @Override
    public void displayScreen(String fxmlFileName) throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        AnchorPane pane= FXMLLoader.load(getClass().getResource(fxmlFileName));
        rootPane.getChildren().addAll(pane);
    }
}
