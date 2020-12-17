import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.*;

public class ColorSwitchApp extends Application
{
    private static SuperSystem obj;
    private static MediaPlayer mediaPlayer;

    public static void load_music() {
        mediaPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/theme.mp3").toString()));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.3);
        mediaPlayer.setAutoPlay(true);
    }

    public static void serialize() throws IOException {
        ObjectOutputStream out = null;
        try
        {
            out = new ObjectOutputStream(new FileOutputStream("saveFile.txt"));
            out.writeObject(obj);
        }
        finally
        {
            if (out != null)
            {
                out.close();
            }
        }
    }

    public static void deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try
        {
            in = new ObjectInputStream(new FileInputStream("saveFile.txt"));
            obj = (SuperSystem) in.readObject();
        }
        catch (Exception e)
        {
            obj = new SuperSystem();
        }
        finally
        {
            if (in != null)
            {
                in.close();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static SuperSystem getObj()
    {
        return obj;
    }

    public static MediaPlayer getMediaPlayer()
    {
        return mediaPlayer;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        deserialize();
        load_music();
        Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        primaryStage.setTitle("-->>   COLOR SWITCH");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
