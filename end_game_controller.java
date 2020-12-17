import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class end_game_controller implements Initializable,Displayable {
    @FXML
    AnchorPane rootPane;
    @FXML
    Button spendAndContinue;
    @FXML
    Button newGame;
    @FXML
    Text txf;

    private MediaPlayer buttonPlayer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txf.setText(ColorSwitchApp.getObj().callGetEndGameCondition());
        ColorSwitchApp.getMediaPlayer().play();
    }

    public void spend_and_continue() throws Exception {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        if(ColorSwitchApp.getObj().callGetScore()>=3)
        {
            ColorSwitchApp.getMediaPlayer().stop();
            ColorSwitchApp.getObj().callSetScore(ColorSwitchApp.getObj().callGetScore()-3);
            ColorSwitchApp.getObj().resumeGame(false);
            Stage stage = (Stage) spendAndContinue.getScene().getWindow();
            stage.close();}
        else
        {
            Stage window=new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Not Enough Score..");
            Label label= new Label();
            label.setText("You have score deficiency!!\n");
            Button close=new Button("Return ");
            close.setOnAction(e->window.close());
            VBox layout=new VBox(20);
            layout.getChildren().addAll(label,close);
            layout.setAlignment(Pos.CENTER);
            Scene scene=new Scene(layout,250,100);
            window.setScene(scene);
            window.initStyle(StageStyle.UNDECORATED);
            window.showAndWait();
        }
    }

    public void new_game() throws Exception {
        ColorSwitchApp.getMediaPlayer().stop();
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        ColorSwitchApp.getObj().callUpdateLeaderboard();
        ColorSwitchApp.getObj().resetGame();
        Stage stage = (Stage) newGame.getScene().getWindow();
        stage.close();
    }

    public void return_to_main_menu() throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        ColorSwitchApp.getObj().callUpdateLeaderboard();
        ColorSwitchApp.getObj().quitGame();
        displayScreen("main_menu.fxml");
    }

    public void avail_extra_life() throws Exception {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        if(ColorSwitchApp.getObj().callGetExtraLife()==1)
        {
            ColorSwitchApp.getMediaPlayer().stop();
            ColorSwitchApp.getObj().callSetExtraLife(-1);
            ColorSwitchApp.getObj().resumeGame(false);
            Stage stage = (Stage) spendAndContinue.getScene().getWindow();
            stage.close();
        }
        else
        {
            Stage window=new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("No Extra Life..");
            Label label= new Label();
            label.setText("You do not have extra life!!\n");
            Button close=new Button("Return ");
            close.setOnAction(e->window.close());
            VBox layout=new VBox(20);
            layout.getChildren().addAll(label,close);
            layout.setAlignment(Pos.CENTER);
            Scene scene=new Scene(layout,250,100);
            window.setScene(scene);
            window.initStyle(StageStyle.UNDECORATED);
            window.showAndWait();
        }
    }

    @Override
    public void displayScreen(String fxmlFileName) throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        AnchorPane pane= FXMLLoader.load(getClass().getResource(fxmlFileName));
        rootPane.getChildren().addAll(pane);
    }
}
