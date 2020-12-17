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

public class premium_player_payment2_controller implements Initializable {
    @FXML
    AnchorPane rootPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void Gpay() {
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Payment alert...");
        Label label= new Label();
        label.setText("Payment made successfully (Gpay).. \n\nYou are a Premium Player now. \nEnjoy the benefits..");
        Button close=new Button("Return ");
        close.setOnAction(e->window.close());
        VBox layout=new VBox(20);
        layout.getChildren().addAll(label,close);
        layout.setAlignment(Pos.CENTER);
        Scene scene=new Scene(layout,250,150);
        window.setScene(scene);
        window.showAndWait();
        //Test.getObj().getCurrentPlayer().setPremiumStatus(true);
        Test.getObj().getPlayerByIndex(existing_players_controller.index).setPremiumStatus(true);
    }

    public void Paytm() {
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Payment alert...");
        Label label= new Label();
        label.setText("Payment made successfully (PayTM).. \n\nYou are a Premium Player now.\nEnjoy the benefits..");
        Button close=new Button("Return ");
        close.setOnAction(e->window.close());
        VBox layout=new VBox(20);
        layout.getChildren().addAll(label,close);
        layout.setAlignment(Pos.CENTER);
        Scene scene=new Scene(layout,250,150);
        window.setScene(scene);
        window.showAndWait();
        //Test.getObj().getCurrentPlayer().setPremiumStatus(true);
        Test.getObj().getPlayerByIndex(existing_players_controller.index).setPremiumStatus(true);
    }

    public void back() throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        AnchorPane pane= FXMLLoader.load(getClass().getResource("existing_players.fxml"));
        rootPane.getChildren().addAll(pane);
    }
}
