import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class premium_player_payment_controller implements Initializable {
    @FXML
    AnchorPane rootPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void Gpay() throws IOException {
        Test.getObj().addPlayer(new_player_controller.str,true);
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Payment alert...");
        Label label= new Label();
        label.setText("Payment made successfully (Gpay).. \n\nProfile created..\n You are a Premium Player now. \nEnjoy the benefits..");
        Button close=new Button("Return ");
        close.setOnAction(e->window.close());
        VBox layout=new VBox(20);
        layout.getChildren().addAll(label,close);
        layout.setAlignment(Pos.CENTER);
        Scene scene=new Scene(layout,250,150);
        window.setScene(scene);
        window.showAndWait();
        AnchorPane pane= FXMLLoader.load(getClass().getResource("player_profile.fxml"));
        rootPane.getChildren().addAll(pane);
    }

    public void Paytm() throws IOException {
        Test.getObj().addPlayer(new_player_controller.str,true);
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Payment alert...");
        Label label= new Label();
        label.setText("Payment made successfully (PayTM).. \n\nProfile Created...\n You are a Premium Player now.\nEnjoy the benefits..");
        Button close=new Button("Return ");
        close.setOnAction(e->window.close());
        VBox layout=new VBox(20);
        layout.getChildren().addAll(label,close);
        layout.setAlignment(Pos.CENTER);
        Scene scene=new Scene(layout,250,150);
        window.setScene(scene);
        window.showAndWait();
        AnchorPane pane= FXMLLoader.load(getClass().getResource("player_profile.fxml"));
        rootPane.getChildren().addAll(pane);
    }

    public void back() throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        AnchorPane pane= FXMLLoader.load(getClass().getResource("new_player.fxml"));
        rootPane.getChildren().addAll(pane);
    }
}
