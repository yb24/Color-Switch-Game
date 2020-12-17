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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class premium_player_payment_controller implements Initializable,Displayable {
    @FXML
    AnchorPane rootPane;
    private MediaPlayer buttonPlayer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void helper(String s) throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        ColorSwitchApp.getObj().addPlayer(new_player_controller.str,true);
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Payment alert...");
        Label label= new Label();
        label.setText(s);
        Button close=new Button("Return ");
        close.setOnAction(e->window.close());
        VBox layout=new VBox(20);
        layout.getChildren().addAll(label,close);
        layout.setAlignment(Pos.CENTER);
        Scene scene=new Scene(layout,250,150);
        window.setScene(scene);
        window.initStyle(StageStyle.UNDECORATED);
        window.showAndWait();
        displayScreen("player_profile.fxml");
    }
    public void Gpay() throws IOException {
        helper("Payment made successfully (Gpay).. \n\nProfile created..\n You are a Premium Player now. \nEnjoy the benefits..");
    }

    public void Paytm() throws IOException {
        helper("Payment made successfully (PayTM).. \n\nProfile Created...\n You are a Premium Player now.\nEnjoy the benefits..");
    }

    public void back() throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
        displayScreen("new_player.fxml");
    }

    @Override
    public void displayScreen(String fxmlFileName) throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        AnchorPane pane= FXMLLoader.load(getClass().getResource(fxmlFileName));
        rootPane.getChildren().addAll(pane);
    }
}
