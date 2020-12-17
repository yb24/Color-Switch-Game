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

public class premium_player_payment2_controller implements Initializable,Displayable {
    @FXML
    AnchorPane rootPane;
    private MediaPlayer buttonPlayer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void helper(String s) throws IOException {
        buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
        buttonPlayer.play();
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
        ColorSwitchApp.getObj().callSetPremiumStatusOfPlayerInfo(existing_players_controller.index,true);
        ColorSwitchApp.getObj().callUpdateExtraLife(existing_players_controller.index);
        back();
    }
    public void Gpay() throws IOException {
        try {
            if(ColorSwitchApp.getObj().isPlayerAtIndexPremium(existing_players_controller.index))
                helper("You are already a\n premium player");
            else if(!ColorSwitchApp.getObj().isPlayerAtIndexPremium(existing_players_controller.index))
                helper("Payment made successfully (Gpay).. \n\nYou are a Premium Player now. \nEnjoy the benefits..");
            else
                throw new TransactionFailedException("Transaction Failed Exception Caught");
        }
        catch (TransactionFailedException tfe)
        {
            System.out.println(tfe.getMessage());
        }
    }

    public void Paytm() throws IOException {
        try {
            if(ColorSwitchApp.getObj().isPlayerAtIndexPremium(existing_players_controller.index))
                helper("You are already a\n premium player");
            else if(!ColorSwitchApp.getObj().isPlayerAtIndexPremium(existing_players_controller.index))
                helper("Payment made successfully (PayTM).. \n\nYou are a Premium Player now.\nEnjoy the benefits..");
            else
                throw new TransactionFailedException("Transaction Failed Exception Caught");
        }
        catch (TransactionFailedException tfe)
        {
            System.out.println(tfe.getMessage());
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
