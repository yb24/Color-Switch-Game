import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.*;

public class Test extends Application
{
    private static SuperSystem obj;

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
            System.out.println("Exception caught");
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

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        launch(args);
        /*
        Reader.init(System.in);
        //int choice;

        System.out.println("Hi");

        System.out.println("Deserializing now");
        deserialize();

        System.out.println("obj after deserialization");
        obj.printDetails();
        System.out.println();

        System.out.print("Enter value of a: ");
        obj.setA(Reader.nextInt());

        System.out.print("Enter value of b: ");
        obj.setB(Reader.next());

        System.out.print("Enter current player name : ");
        String temp_String = Reader.next();
        System.out.print("Enter current player ID : ");
        int temp_int = Reader.nextInt();
        obj.setCurrentPlayer(temp_String,temp_int);


        obj.addPlayer(temp_String,temp_int);

        System.out.print("Select current Player, enter index:");
        obj.selectCurrentPlayer(Reader.nextInt());

        System.out.println("Updating leaderboard");
        obj.tempFunc("cde",5);

        System.out.println();
        System.out.println("Serializing now");
        serialize();

        System.out.println("obj after serialization");
        obj.printDetails();

        System.out.println("Bye");

         */
    }

    public static SuperSystem getObj()
    {
        return obj;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Reader.init(System.in);
        deserialize();
        //obj.yy();
        obj.printDetails();
        Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        primaryStage.setTitle("-->>   COLOR SWITCH");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
