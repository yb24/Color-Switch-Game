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

public class support_us_controller implements Initializable {
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
        label.setText("Payment made successfully by GPay.. \n\nThank you for your support...");
        Button close=new Button("Return ");
        close.setOnAction(e->window.close());
        VBox layout=new VBox(20);
        layout.getChildren().addAll(label,close);
        layout.setAlignment(Pos.CENTER);
        Scene scene=new Scene(layout,250,150);
        window.setScene(scene);
        window.showAndWait();


    }

    public void Paytm() {
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Payment alert...");
        Label label= new Label();
        label.setText("Payment made successfully using PayTM.. \n\nThank you for your support...");
        Button close=new Button("Return ");
        close.setOnAction(e->window.close());
        VBox layout=new VBox(20);
        layout.getChildren().addAll(label,close);
        layout.setAlignment(Pos.CENTER);
        Scene scene=new Scene(layout,250,150);
        window.setScene(scene);
        window.showAndWait();
    }

    public void return_to_menu() throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().removeAll();
        AnchorPane pane= FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        rootPane.getChildren().addAll(pane);
    }
}
