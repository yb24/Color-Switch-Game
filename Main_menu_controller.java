import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main_menu_controller extends Application implements Initializable {
    @FXML
    AnchorPane rootPane;
    @FXML
    Button exit;

    //SuperSystem system=new SuperSystem();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        primaryStage.setTitle("-->>   COLOR SWITCH");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void Start_game(ActionEvent actionEvent) throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        AnchorPane pane= FXMLLoader.load(getClass().getResource("player_profile.fxml"));
        rootPane.getChildren().addAll(pane);
    }

    public void show_leader_board() throws IOException {
        //system.get_SuperSystem();
        //system.displayLeaderboard();
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        AnchorPane pane= FXMLLoader.load(getClass().getResource("Leaderboard.fxml"));
        rootPane.getChildren().addAll(pane);
    }

    public void How_to_play() throws IOException {
        //system.get_SuperSystem();
        //system.displayInstruction();
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        AnchorPane pane= FXMLLoader.load(getClass().getResource("play_rules.fxml"));
        rootPane.getChildren().addAll(pane);
    }

    public void Exit() throws IOException {
        Stage stage = (Stage) exit.getScene().getWindow();
        // do what you have to do
        //Test.getObj().callBeforeExiting();
        Test.serialize();
        stage.close();
    }

    public void about_us() throws IOException {
        //system.get_SuperSystem();
        //system.displayAboutUs();
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        AnchorPane pane= FXMLLoader.load(getClass().getResource("about_us.fxml"));
        rootPane.getChildren().addAll(pane);
    }

    public void support_us() throws IOException {
        //system.get_SuperSystem();
        //system.displaySupportUs();
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        AnchorPane pane= FXMLLoader.load(getClass().getResource("support_us.fxml"));
        rootPane.getChildren().addAll(pane);
    }
}
