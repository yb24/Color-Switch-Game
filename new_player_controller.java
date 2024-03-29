import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class new_player_controller implements Initializable,Displayable {
    public TextField new_player_name;
    public static String str;
    @FXML
    AnchorPane rootPane;
    private MediaPlayer buttonPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void premium_profile() throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        String name=new_player_name.getText();
        str = name;
        if(name.equals(""))
        {
            Stage window=new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("No Name");
            Label label= new Label();
            label.setText("Player name not Entered!!\n");
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
        else {
            displayScreen("premium_player_payment.fxml");
        }
    }

    public void normal_profile() throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        String name=new_player_name.getText();
        if(name.equals(""))
        {
            Stage window=new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("No Name");
            Label label= new Label();
            label.setText("Player name not Entered!!\n");
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
        else
        {
            rootPane.getChildren().clear();
            rootPane.getChildren().removeAll();
            ColorSwitchApp.getObj().addPlayer(name,false);
            Stage window=new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Player Created");
            Label label= new Label();
            label.setText("Player has been created!!\n");
            Button close=new Button("Return ");
            close.setOnAction(e->window.close());
            VBox layout=new VBox(20);
            layout.getChildren().addAll(label,close);
            layout.setAlignment(Pos.CENTER);
            Scene scene=new Scene(layout,250,100);
            window.setScene(scene);
            window.initStyle(StageStyle.UNDECORATED);
            window.showAndWait();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("player_profile.fxml"));
            rootPane.getChildren().addAll(pane);
        }
    }

    public void back() throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        displayScreen("player_profile.fxml");
    }

    @Override
    public void displayScreen(String fxmlFileName) throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        AnchorPane pane= FXMLLoader.load(getClass().getResource(fxmlFileName));
        rootPane.getChildren().addAll(pane);
    }
}
