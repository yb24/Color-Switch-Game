import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import static java.lang.System.exit;

public class Game extends Application implements Serializable, Initializable
{
    private static final long serialVersionUID = 7070L;
    private final Player user;
    private final int GameID;
    private int score;
    private Ball ball;
    private ArrayList<Star> stars;
    private ArrayList<ColorChanger> colorChangers;
    private ArrayList<Obstacle> obstacles;
    private transient Background background;
    private transient Random rand = new Random();
    private transient ArrayList<Tile_object_animation> obstacles_list;
    private transient ArrayList<Tile_object_animation> star_list;
    private transient ArrayList<Tile_object_animation> color_changer_list;
    private transient ArrayList<Timeline> timeline_obstacle;
    private transient ArrayList<Timeline> timeline_star;
    private transient ArrayList<Timeline> timeline_color_changer;
    private transient Circle javaFX_ball;
    private transient int a;
    private transient boolean change=false;
    private transient int javafx_star =0,col_changer=0;
    private transient Text txf;
    private transient Timeline timeline2=new Timeline();

    Game(Player u,int id)
    {
        user = u;
        GameID = id;
        score=0;
        ball = new Ball();
        stars = new ArrayList<>();
        colorChangers = new ArrayList<>();
        background =new Background();
        obstacles_list =new ArrayList<>();
        color_changer_list =new ArrayList<>();
        star_list =new ArrayList<>();
        timeline_obstacle  = new ArrayList<>();
        timeline_color_changer  = new ArrayList<>();
        timeline_star  = new ArrayList<>();
        switch (ball.getColorCodeOfBall())
        {
            case 1: javaFX_ball = new Circle(ball.getX(),ball.getY(),5.0d,Color.RED);
                break;
            case 2: javaFX_ball = new Circle(ball.getX(),ball.getY(),5.0d,Color.GREEN);
                break;
            case 3: javaFX_ball = new Circle(ball.getX(),ball.getY(),5.0d,Color.BLUE);
                break;
            case 4: javaFX_ball = new Circle(ball.getX(),ball.getY(),5.0d,Color.YELLOW);
                break;
            default: break;
        }
        a=0;
        change=false;
        javafx_star =0;
        score=0;
        col_changer=0;
    }

    public int getGameID()
    {
        return GameID;
    }

    public int getScore()
    {
        return score;
    }

    public String getGameString()
    {
        return "Game " + GameID + "   score: " + score;
    }

    public void dispGamedetails()
    {
        System.out.println("GameID: " + GameID + " score: " + score);
        System.out.println(ball.getX());
        System.out.println(ball.getY());
        System.out.println(ball.getColorCodeOfBall());
        System.out.println("All Color Changers:");
        for(ColorChanger cc: colorChangers)
        {
            System.out.println(cc.getY());
        }
        System.out.println("All Stars:");
        for(Star s: stars)
        {
            System.out.println(s.getY());
        }
    }

    public void play() throws Exception {
        this.start(new Stage());
    }

    private int checkShapeIntersection(Shape block, Group root, Stage myStage) throws IOException {
        ArrayList<Shape> nodes_obstacles=new ArrayList<>();
        ArrayList<Shape> nodes_star=new ArrayList<>();
        ArrayList<Shape> nodes_color_changer=new ArrayList<>();
        int rm=0, star_struck =0,color_changer_struck=0;
        for(int i = 0; i< obstacles_list.size(); i++) {
            for(int j = 0; j< obstacles_list.get(i).shape.getChildren().size(); j++)
            {
                nodes_obstacles.add((Shape) obstacles_list.get(i).shape.getChildren().get(j));
            }
        }
        for(int i = 0; i< color_changer_list.size(); i++) {
            for(int j = 0; j< color_changer_list.get(i).shape.getChildren().size(); j++)
            {
                nodes_color_changer.add((Shape) color_changer_list.get(i).shape.getChildren().get(j));
            }
        }
        for(int i = 0; i< star_list.size(); i++) {
            for(int j = 0; j< star_list.get(i).shape.getChildren().size(); j++)
            {
                nodes_star.add((Shape) star_list.get(i).shape.getChildren().get(j));
            }
        }
        boolean collisionDetected = false;
        for (Shape static_bloc : nodes_obstacles) {
            System.out.println("shapes ka y is this...  "+(static_bloc.localToScene(static_bloc.getBoundsInLocal()).getCenterY()));
            Shape intersect = Shape.intersect(block, static_bloc);
            if (intersect.getBoundsInLocal().getWidth() != -1) {
                if(static_bloc.getStroke()!=block.getFill() )
                {
                    System.out.println("ab ball was at: "+ (javaFX_ball.getCenterY()));
                    System.out.println("color of shape was: "+ (static_bloc.getStroke()));
                    System.out.println("ye caused collision is this...  "+(static_bloc.localToScene(static_bloc.getBoundsInLocal()).getCenterY()));

                    //myStage.close();
                    System.out.println("Crash "+(static_bloc.getStroke()==Color.WHITE));
                    Parent root2= FXMLLoader.load(getClass().getResource("obstacle_touched.fxml"));
                    Stage childStage =new Stage();
                    childStage.setScene(new Scene(root2));
                    childStage.initModality(Modality.APPLICATION_MODAL);
                    childStage.show();
                    return 1;
                }
            }
        }
        //////////////////
        for (Shape static_bloc : nodes_star) {
            //System.out.println("javafx_star  "+(static_bloc.localToScene(static_bloc.getBoundsInLocal()).getCenterY()));
            Shape intersect = Shape.intersect(block, static_bloc);
            if (intersect.getBoundsInLocal().getWidth() != -1) {
                if (static_bloc.getStroke() == Color.BLACK) {
                    if(user.getPremiumStatus())
                        score+=2;
                    else
                        score++;
                    txf.setText(String.valueOf(score));
                    star_struck = 1;
                }
            }
            if(star_struck ==1) {
                rm = -1;
                for (int i = 0; i < star_list.size(); i++) {
                    if (star_list.get(i).shape.getChildren().get(0) instanceof Circle) {
                        rm = i;
                        break;
                    }
                }
                if (rm != -1) {
                    for (int i = 0; i < root.getChildren().size(); i++) {
                        if (root.getChildren().get(i).equals(star_list.get(rm).shape)) {
                            root.getChildren().remove(i);
                            i--;
                        }
                    }
                    Tile_object_animation h = star_list.remove(rm);
                    timeline_star.remove(rm);
                }
                star_struck =0;
            }
        }


        //////////////////

        for (Shape static_bloc : nodes_color_changer) {
            //System.out.println("color_changer  "+(static_bloc.localToScene(static_bloc.getBoundsInLocal()).getCenterY()));
            Shape intersect = Shape.intersect(block, static_bloc);
            if (intersect.getBoundsInLocal().getWidth() != -1) {
                if (static_bloc.getStroke() == Color.DARKGREY) {
                    int rand_int = rand.nextInt(4);
                    switch (rand_int) {
                        case 0:
                            block.setFill(Color.YELLOW);
                            ball.setColorCodeOfBall(4);
                            break;
                        case 1:
                            block.setFill(Color.GREEN);
                            ball.setColorCodeOfBall(2);
                            break;
                        case 2:
                            block.setFill(Color.RED);
                            ball.setColorCodeOfBall(1);
                            break;
                        case 3:
                            block.setFill(Color.BLUE);
                            ball.setColorCodeOfBall(3);
                            break;
                        default:
                            break;
                    }
                    color_changer_struck = 1;
                }
            }
            if(color_changer_struck ==1) {
                rm = -1;
                for (int i = 0; i < color_changer_list.size(); i++) {
                    if (color_changer_list.get(i).shape.getChildren().get(0) instanceof Ellipse) {
                        rm = i;
                        break;
                    }
                }
                if (rm != -1) {
                    for (int i = 0; i < root.getChildren().size(); i++) {
                        if (root.getChildren().get(i).equals(color_changer_list.get(rm).shape)) {
                            root.getChildren().remove(i);
                            i--;
                        }
                    }
                    Tile_object_animation h = color_changer_list.remove(rm);
                    timeline_color_changer.remove(rm);
                }
                color_changer_struck = 0;
            }
        }



        ////////////////
return 0;
    }

    public void shift_screen(double mag,Group root)
    {
        int rm_obstacle=-1,rm_star=-1,rm_color_changer=-1;
        double max=0;
        int flag=0;
        Bounds bound2=null,bound=null;
        for(int i = 0; i< obstacles_list.size(); i++) {
            bound= obstacles_list.get(i).shape.localToScene(obstacles_list.get(i).shape.getBoundsInLocal());
            if(i>0)
            {
                if( (int) (bound2.getCenterY()-bound.getCenterY())>500)
                    flag = 1;
            }
            for(int j = 0; j< obstacles_list.get(i).shape.getChildren().size(); j++)
            {
                if(obstacles_list.get(i).shape.getChildren().get(j) instanceof Arc)
                {
                    Arc arc= (Arc) obstacles_list.get(i).shape.getChildren().get(j);
                    arc.setCenterY(arc.getCenterY()+mag);
                }
                else if(obstacles_list.get(i).shape.getChildren().get(j) instanceof Line)
                {
                    Line line= (Line) obstacles_list.get(i).shape.getChildren().get(j);
                    line.setStartY(line.getStartY()+mag);
                    line.setEndY(line.getEndY()+mag);
                }
            }
            bound2 = obstacles_list.get(i).shape.localToScene(obstacles_list.get(i).shape.getBoundsInLocal());
            System.out.println((i+1)+" Bounds after translate "+(bound2.getCenterY()));
            if(bound.getCenterY()>=900) {
                System.out.println("to remove index: "+(i));
                rm_obstacle=i;
                max=bound.getCenterY();
            }
        }
        for(int i = 0; i< star_list.size(); i++) {
            for(int j = 0; j< star_list.get(i).shape.getChildren().size(); j++)
            {
                if(star_list.get(i).shape.getChildren().get(j) instanceof Circle)
                {
                    Circle cir= (Circle) star_list.get(i).shape.getChildren().get(j);
                    cir.setCenterY(cir.getCenterY()+mag);
                }
            }
            bound= star_list.get(i).shape.localToScene(star_list.get(i).shape.getBoundsInLocal());
            if(bound.getCenterY()>=900) {
                System.out.println("to remove index: "+(i));
                rm_star=i;
                max=bound.getCenterY();
            }
        }
        for(int i = 0; i< color_changer_list.size(); i++) {
            for(int j = 0; j< color_changer_list.get(i).shape.getChildren().size(); j++)
            {
                if(color_changer_list.get(i).shape.getChildren().get(j) instanceof Ellipse)
                {
                    Ellipse elps= (Ellipse) color_changer_list.get(i).shape.getChildren().get(j);
                    elps.setCenterY(elps.getCenterY()+mag);
                }
            }
            bound= color_changer_list.get(i).shape.localToScene(color_changer_list.get(i).shape.getBoundsInLocal());
            //colorChangers.get(i).updatePosition(300.0d,bound.getCenterY());
            if(bound.getCenterY()>=900) {
                System.out.println("to remove index: "+(i));
                rm_color_changer=i;
                max=bound.getCenterY();
            }
        }
        if(rm_obstacle!=-1 || rm_color_changer!=-1 || rm_star!=-1) {
            if(rm_obstacle!=-1) {
                for (int i = 0; i < root.getChildren().size(); i++) {
                    if (root.getChildren().get(i).equals(obstacles_list.get(rm_obstacle).shape)) {
                        root.getChildren().remove(i);
                        i--;
                    }
                }
                Tile_object_animation h = obstacles_list.remove(rm_obstacle);
                timeline_obstacle.remove(rm_obstacle);
                obstacles.remove(rm_obstacle);
            }
            else if(rm_color_changer!=-1)
            {
                for (int i = 0; i < root.getChildren().size(); i++) {
                    if (root.getChildren().get(i).equals(color_changer_list.get(rm_color_changer).shape)) {
                        root.getChildren().remove(i);
                        i--;
                    }
                }
                Tile_object_animation h = color_changer_list.remove(rm_color_changer);
                timeline_color_changer.remove(rm_color_changer);
                colorChangers.remove(rm_color_changer);
            }
            else if(rm_star!=-1)
            {
                for (int i = 0; i < root.getChildren().size(); i++) {
                    if (root.getChildren().get(i).equals(star_list.get(rm_star).shape)) {
                        root.getChildren().remove(i);
                        i--;
                    }
                }
                Tile_object_animation h = star_list.remove(rm_star);
                timeline_star.remove(rm_star);
                stars.remove(rm_star);
            }
        }
        if(max>=900)
        {
            Tile_object_animation obj;
            javafx_star++;
            col_changer++;
            if(flag==0)
                obj = background.screen_obstacles(rand.nextInt(7)+1,0.0d,0);
            else
                obj = background.screen_obstacles(rand.nextInt(7)+1,-150.0d,0);
            bound = obj.shape.localToScene(obj.shape.getBoundsInLocal());
            double position= bound.getCenterY();
            //javafx_star ka code
            if(javafx_star %2==0)
            {
                Tile_object_animation obj2;
                obj2 = background.screen_obstacles(8,position,0);
                stars.add(new Star(300.0d,position));
                bound = obj2.shape.localToScene(obj2.shape.getBoundsInLocal());
                root.getChildren().add(obj2.shape);
                timeline_star.add(new Timeline());
                star_list.add(obj2);
                change = true;
                bound = obj2.shape.localToScene(obj2.shape.getBoundsInLocal());
                timeline_star.get(timeline_star.size() - 1).setCycleCount(Timeline.INDEFINITE);
                timeline_star.get(timeline_star.size() - 1).setAutoReverse(true);
                timeline_star.get(timeline_star.size() - 1).getKeyFrames().addAll(obj.animation);
                timeline_star.get(timeline_star.size() - 1).play();
                javafx_star =0;
            }
            if(col_changer%(rand.nextInt(2)+1)==0)
            {
                Tile_object_animation obj2;
                obj2 = background.screen_obstacles(9,position+200,0);
                colorChangers.add(new ColorChanger(300.0d,position+200));
                bound = obj2.shape.localToScene(obj2.shape.getBoundsInLocal());
                root.getChildren().add(obj2.shape);
                timeline_color_changer.add(new Timeline());
                color_changer_list.add(obj2);
                change = true;
                bound = obj2.shape.localToScene(obj2.shape.getBoundsInLocal());
                timeline_color_changer.get(timeline_color_changer.size() - 1).setCycleCount(Timeline.INDEFINITE);
                timeline_color_changer.get(timeline_color_changer.size() - 1).setAutoReverse(true);
                timeline_color_changer.get(timeline_color_changer.size() - 1).getKeyFrames().addAll(obj.animation);
                timeline_color_changer.get(timeline_color_changer.size() - 1).play();
                col_changer=0;
            }
            root.getChildren().add(obj.shape);
            timeline_obstacle.add(new Timeline());
            obstacles_list.add(obj);
            change = true;
            bound = obj.shape.localToScene(obj.shape.getBoundsInLocal());
            timeline_obstacle.get(timeline_obstacle.size() - 1).setCycleCount(Timeline.INDEFINITE);
            timeline_obstacle.get(timeline_obstacle.size() - 1).setAutoReverse(true);
            timeline_obstacle.get(timeline_obstacle.size() - 1).getKeyFrames().addAll(obj.animation);
            timeline_obstacle.get(timeline_obstacle.size() - 1).play();
            for (int i = 0; i < obstacles_list.size(); i++) {
                bound = obstacles_list.get(i).shape.localToScene(obstacles_list.get(i).shape.getBoundsInLocal());
                System.out.println((i + 1) + "new list Bounds " + (bound.getCenterY()));
            }
        }
    }

    @Override
    public void start(Stage myStage) throws Exception {
        Tile_object_animation obj= background.screen_obstacles(rand.nextInt(7) + 1,100,0);
        obstacles_list.add(obj);
        Tile_object_animation obj2= background.screen_obstacles(rand.nextInt(7) + 1,500,0);
        obstacles_list.add(obj2);
        Image img = new Image(getClass().getResource("play.gif").toURI().toString());
        ImageView view = new ImageView(img);
        view.setStyle("-fx-border-radius: 8px;");
        view.setFitHeight(80);
        view.setPreserveRatio(true);
        //Creating a Button
        Button button = new Button();
        //Setting the location of the button
        button.setTranslateX(0);
        button.setShape(new Circle(10));
        button.setTranslateY(15);
        button.setStyle("-fx-background-color: #000000; ");
        //Setting the size of the button
        button.setPrefSize(80, 80);
        //Setting a graphic to the button
        button.setGraphic(view);
        txf=new Text();
        txf.setTranslateX(570);
        txf.setTranslateY(25);
        txf.setStyle("-fx-background-color: #000000;-fx-text-inner-color: red; ");
        txf.setFont(Font.font("Verdana",20));
        txf.setFill(Color.RED);
        txf.setText(String.valueOf((score)));

        Group root = new Group();
        //root.getChildren().addAll(background,javaFX_ball);
        int size=0;
        for(int i = 0; i< obstacles_list.size(); i++) {
            root.getChildren().add(obstacles_list.get(i).shape);
            timeline_obstacle.add(new Timeline());
            timeline_obstacle.get(i).setCycleCount(Timeline.INDEFINITE);
            timeline_obstacle.get(i).setAutoReverse(true);
            timeline_obstacle.get(i).getKeyFrames().addAll(obstacles_list.get(i).animation);
            timeline_obstacle.get(i).play();
            //timeline.getKeyFrames().add(shapes.get(i).animation);
        }
        System.out.println("Size of obstacle: "+(size));
        root.getChildren().add(javaFX_ball);
        root.getChildren().add(button);
        root.getChildren().add(txf);
        Scene myScene = new Scene(root,600,800,Color.BLACK);
        myStage.setScene(myScene);
        myStage.setTitle("Gameplay Animation");
        myStage.show();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Parent root2= null;
                ball.updatePosition(300.0d,javaFX_ball.getCenterY());
                for(int i=0;i<star_list.size();i++)
                {
                    stars.get(i).updatePosition(300.0d,star_list.get(i).shape.localToScene(star_list.get(i).shape.getBoundsInLocal()).getCenterY());
                }
                for(int i=0;i<color_changer_list.size();i++)
                {
                    colorChangers.get(i).updatePosition(300.0d,color_changer_list.get(i).shape.localToScene(color_changer_list.get(i).shape.getBoundsInLocal()).getCenterY());
                }
                //update obstacles
                try {
                    root2 = FXMLLoader.load(getClass().getResource("pause_game.fxml"));
                    Test.serialize();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage childStage =new Stage();
                childStage.setScene(new Scene(root2));
                childStage.initModality(Modality.APPLICATION_MODAL);
                childStage.initOwner(myStage);
                childStage.show();
                shift_screen( 900.0d- javaFX_ball.getCenterY(),root);
                javaFX_ball.setCenterY(900.0d);
                ball.updatePosition(javaFX_ball.getCenterX(), 900.0d);
                System.out.print(javaFX_ball.getCenterY());
            }
        });
       javaFX_ball.centerYProperty().addListener((observable, oldvalue, newvalue)->{
             System.out.println("old value "+oldvalue+ " ");
             try {
                 checkShapeIntersection(javaFX_ball,root,myStage);
             } catch (IOException ioException) {
                 ioException.printStackTrace();
             }
             System.out.println("new value "+newvalue);
        });
        myScene.setOnKeyPressed(e-> {
            if (e.getCode() == KeyCode.W) {
                timeline2.stop();
                timeline2.getKeyFrames().clear();
                KeyValue xValue  = new KeyValue(javaFX_ball.centerYProperty(), 800);
                KeyFrame keyFrame2  = new KeyFrame(Duration.millis((900- javaFX_ball.getCenterY())*20), xValue);
                timeline2.setCycleCount(1);
                timeline2.getKeyFrames().addAll(keyFrame2);
                javaFX_ball.setCenterY(javaFX_ball.getCenterY() - 20);
                //ball.updatePosition(javaFX_ball.getCenterX(), javaFX_ball.getCenterY());
               try {
                   checkShapeIntersection(javaFX_ball,root,myStage);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                if(javaFX_ball.getCenterY()<=400) {
                    System.out.println("hi");
                    shift_screen( 20,root);
                    javaFX_ball.setCenterY(400);
                    //ball.updatePosition(javaFX_ball.getCenterX(), javaFX_ball.getCenterY());
                }
                timeline2.play();

            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
