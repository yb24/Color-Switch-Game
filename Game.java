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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.io.Serializable;
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
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Game extends Application implements Serializable, Initializable
{
    private static final long serialVersionUID = 7070L;
    private final Player user;
    private final int GameID;
    private int score;
    private int obstacles_passed;
    private double difficulty;
    private int javafx_star,col_changer;
    private int extraLife;
    private final Ball ball;
    private final ArrayList<Star> stars;
    private final ArrayList<ColorChanger> colorChangers;
    private final ArrayList<Obstacle> obstacles;
    private transient String end_game_reason;
    private transient Background background;
    private transient RandomGenerator rand;
    private transient ArrayList<Tile_object_animation> obstacles_list;
    private transient ArrayList<Tile_object_animation> star_list;
    private transient ArrayList<Tile_object_animation> color_changer_list;
    private transient ArrayList<Timeline> timeline_obstacle;
    private transient ArrayList<Timeline> timeline_star;
    private transient ArrayList<Timeline> timeline_color_changer;
    private transient Circle javaFX_ball;
    private transient Text txf;
    private transient Timeline timeline2;
    private transient Media ball_tap_sound;
    private transient Media star_collection;
    private transient Media color_changer_collection;
    private boolean isNew;
    private transient int a;
    private transient Parent root2;
    private transient ArrayList<Long> obstacle_animation_begin;
    private transient ArrayList<Double> orientation_list;

    Game(Player u,int id)
    {
        a=0;
        user = u;
        obstacles_passed=0;
        difficulty=0.0d;
        GameID = id;
        score=0;
        end_game_reason= "";
        if(user.getPremiumStatus())
            extraLife=1;
        else
            extraLife=0;
        root2=null;
        ball = new Ball();
        stars = new ArrayList<>();
        colorChangers = new ArrayList<>();
        obstacles = new ArrayList<>();
        background =new Background();
        obstacles_list =new ArrayList<>();
        color_changer_list =new ArrayList<>();
        star_list =new ArrayList<>();
        obstacle_animation_begin=new ArrayList<>();
        orientation_list=new ArrayList<>();
        txf=new Text();
        rand = RandomGenerator.getInstance();
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
            case 5: javaFX_ball =new Circle(ball.getX(),ball.getY(),5.0d,Color.PINK);
                break;
            default: break;
        }
        timeline2=new Timeline();
        ball_tap_sound=new Media(ColorSwitchApp.class.getResource("/sounds/ball_tap.wav").toString());
        star_collection=new Media(ColorSwitchApp.class.getResource("/sounds/star_hit.wav").toString());
        color_changer_collection=new Media(ColorSwitchApp.class.getResource("/sounds/cc3.wav").toString());
        javafx_star =0;
        score=0;
        col_changer=0;
    }
    public void setScore(int s)
    {
        this.score=s;
    }
    public int getGameID()
    {
        return GameID;
    }
    public int getExtraLife()
    {
        return extraLife;
    }
    public void setExtraLife(int el) {
        extraLife=el;
    }

    public int getScore()
    {
        return score;
    }
    public String getEndGameCondition()
    {
        return this.end_game_reason;
    }
    public void setBallToPink()
    {
        ball.setColorCodeOfBall(5);
    }

    public String getGameString()
    {
        return "Game " + GameID + "   score: " + score;
    }

    public void play(boolean bb) throws Exception {
        isNew = bb;
        this.start(new Stage());
    }

    void check_end_game_condition(Group root, Stage myStage,String s, int y_pos)
    {
        Media game_over=new Media(ColorSwitchApp.class.getResource("/sounds/game_over.wav").toString());
        MediaPlayer game_over_player=new MediaPlayer(game_over);
        game_over_player.play();
        end_game_reason=s;
        timeline2.stop();
        a = 1;
        int j=0;
        for (Timeline timeline : timeline_obstacle) {
            timeline.stop();
            long time_of_animation =System.currentTimeMillis()-obstacle_animation_begin.get(j);
            orientation_list.add(time_of_animation%timeline.getKeyFrames().get(0).getTime().toMillis());
            j++;
        }
        timeline_obstacle.clear();
        //updating ball
        if(y_pos==1)
            ball.updatePosition(300.0d, javaFX_ball.getCenterY()-50);
        else
            ball.updatePosition(300.0d, javaFX_ball.getCenterY());
        //updating stars
        for (int i = 0; i < star_list.size(); i++) {
            stars.get(i).updatePosition(300.0d, star_list.get(i).shape.localToScene(star_list.get(i).shape.getBoundsInLocal()).getCenterY());
        }
        //updating colorChangers
        for (int i = 0; i < color_changer_list.size(); i++) {
            colorChangers.get(i).updatePosition(300.0d, color_changer_list.get(i).shape.localToScene(color_changer_list.get(i).shape.getBoundsInLocal()).getCenterY());
        }
        //updating obstacles
        for (int i = 0; i < obstacles_list.size(); i++) {
            obstacles.get(i).updateObstacle(obstacles_list.get(i).shape.localToScene(obstacles_list.get(i).shape.getBoundsInLocal()).getCenterX(), obstacles_list.get(i).shape.localToScene(obstacles_list.get(i).shape.getBoundsInLocal()).getCenterY(), true);
            obstacles.get(i).setOrientation(orientation_list.get(i));
        }
        try {
            root2 = FXMLLoader.load(getClass().getResource("end_game.fxml"));
            ColorSwitchApp.serialize();
            root.getChildren().clear();
            myStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage childStage = new Stage();
        childStage.setScene(new Scene(root2));
        childStage.initStyle(StageStyle.UNDECORATED);
        childStage.show();
    }

    private int checkShapeIntersection(Shape block, Group root, Stage myStage) {
        ArrayList<Shape> nodes_obstacles=new ArrayList<>();
        ArrayList<Shape> nodes_star=new ArrayList<>();
        ArrayList<Shape> nodes_color_changer=new ArrayList<>();
        for (Tile_object_animation tile_object_animation : obstacles_list) {
            for (int j = 0; j < tile_object_animation.shape.getChildren().size(); j++) {
                nodes_obstacles.add((Shape) tile_object_animation.shape.getChildren().get(j));
            }
        }
        for (Tile_object_animation tile_object_animation : color_changer_list) {
            for (int j = 0; j < tile_object_animation.shape.getChildren().size(); j++) {
                nodes_color_changer.add((Shape) tile_object_animation.shape.getChildren().get(j));
            }
        }
        for (Tile_object_animation tile_object_animation : star_list) {
            for (int j = 0; j < tile_object_animation.shape.getChildren().size(); j++) {
                nodes_star.add((Shape) tile_object_animation.shape.getChildren().get(j));
            }
        }
        for (Shape static_bloc : nodes_obstacles) {
            Shape intersect = Shape.intersect(block, static_bloc);
            if (intersect.getBoundsInLocal().getWidth() != -1) {
                if(static_bloc.getStroke()!=block.getFill() && block.getFill()!=Color.PINK)
                {
                    if(a==0) {
                        check_end_game_condition(root,myStage,"-> Obstacle touched!! ",0);
                    }
                    return 1;
                }

            }
        }
        //////////////////
        for (Shape static_bloc : nodes_star) {
            Shape intersect = Shape.intersect(block, static_bloc);
            if (intersect.getBoundsInLocal().getWidth() != -1) {
                if (static_bloc.getStroke() == Color.BLACK) {
                    if(user.getPremiumStatus())
                        score+=2;
                    else
                        score++;
                    MediaPlayer star_hit_player=new MediaPlayer(star_collection);
                    star_hit_player.play();
                    txf.setText(String.valueOf(score));
                    for (int i = 0; i < root.getChildren().size(); i++) {
                        if (root.getChildren().get(i).equals(star_list.get(0).shape)) {
                            root.getChildren().remove(i);
                            i--;
                        }
                    }
                    star_list.remove(0);
                    timeline_star.remove(0);
                    stars.remove(0);
                }
            }
        }


        //////////////////

        for (Shape static_bloc : nodes_color_changer) {
            Shape intersect = Shape.intersect(block, static_bloc);
            if (intersect.getBoundsInLocal().getWidth() != -1) {
                if (static_bloc.getStroke() == Color.DARKGREY) {
                    MediaPlayer color_changer_hit_player=new MediaPlayer(color_changer_collection);
                    color_changer_hit_player.play();
                    int rand_int = rand.nextInt(5);
                    int ball_prev_color=ball.getColorCodeOfBall();
                    if(rand_int+1==ball_prev_color)
                        rand_int=(rand_int+1)%4;
                    switch (rand_int) {
                        case 0:
                            block.setFill(Color.RED);
                            ball.setColorCodeOfBall(1);
                            break;
                        case 1:
                            block.setFill(Color.GREEN);
                            ball.setColorCodeOfBall(2);
                            break;
                        case 2:
                            block.setFill(Color.BLUE);
                            ball.setColorCodeOfBall(3);
                            break;
                        case 3:
                            block.setFill(Color.YELLOW);
                            ball.setColorCodeOfBall(4);
                            break;
                        case 4:
                            block.setFill(Color.PINK);
                            ball.setColorCodeOfBall(5);
                            break;
                        default:
                            break;
                    }
                    for (int i = 0; i < root.getChildren().size(); i++) {
                        if (root.getChildren().get(i).equals(color_changer_list.get(0).shape)) {
                            root.getChildren().remove(i);
                            i--;
                        }
                    }
                    color_changer_list.remove(0);
                    timeline_color_changer.remove(0);
                    colorChangers.remove(0);
                }
            }
        }

return 0;
    }

    public void shift_screen(double mag,Group root)
    {
        int rm_obstacle=-1,rm_star=-1,rm_color_changer=-1;
        double max=0;
        int flag=0;
        Bounds bound2=null,bound;
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
            if(bound.getCenterY()>=900) {
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
            if(bound.getCenterY()>=900) {
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
                if(rm_obstacle<=obstacles_list.size()) {
                    obstacles_list.remove(rm_obstacle);
                    timeline_obstacle.remove(rm_obstacle);
                    obstacles.remove(rm_obstacle);
                }
            }
            else if(rm_color_changer!=-1)
            {
                for (int i = 0; i < root.getChildren().size(); i++) {
                    if (root.getChildren().get(i).equals(color_changer_list.get(rm_color_changer).shape)) {
                        root.getChildren().remove(i);
                        i--;
                    }
                }
                if(rm_color_changer<=color_changer_list.size()) {
                    color_changer_list.remove(rm_color_changer);
                    timeline_color_changer.remove(rm_color_changer);
                    colorChangers.remove(rm_color_changer);
                }
            }
            else {
                for (int i = 0; i < root.getChildren().size(); i++) {
                    if (root.getChildren().get(i).equals(star_list.get(rm_star).shape)) {
                        root.getChildren().remove(i);
                        i--;
                    }
                }
                if(rm_star<=star_list.size()) {
                    star_list.remove(rm_star);
                    timeline_star.remove(rm_star);
                    stars.remove(rm_star);
                }
            }
        }
        if(max>=900)
        {
            Tile_object_animation obj;
            javafx_star++;
            col_changer++;
            obstacles_passed++;
            if(obstacles_passed<=2)
                difficulty=0.0d;
            else
                difficulty=Math.log10((double)obstacles_passed/2);

            //special privileges to premium player....
            if(user.getPremiumStatus())
                difficulty/=2;
            difficulty=Math.min(difficulty,0.8d);
            if(flag==0)
                obj = background.screen_obstacles(rand.nextInt(7)+1,0.0d,difficulty);
            else
                obj = background.screen_obstacles(rand.nextInt(7)+1,-150.0d,difficulty);
            bound = obj.shape.localToScene(obj.shape.getBoundsInLocal());
            double position= bound.getCenterY();
            //javafx_star ka code
            if(javafx_star %2==0)
            {
                Tile_object_animation obj2;
                obj2 = background.screen_obstacles(8,position,0);
                stars.add(new Star(300.0d,position));
                obj2.shape.localToScene(obj2.shape.getBoundsInLocal());
                root.getChildren().add(obj2.shape);
                timeline_star.add(new Timeline());
                star_list.add(obj2);
                obj2.shape.localToScene(obj2.shape.getBoundsInLocal());
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
                obj2.shape.localToScene(obj2.shape.getBoundsInLocal());
                root.getChildren().add(obj2.shape);
                timeline_color_changer.add(new Timeline());
                color_changer_list.add(obj2);
                //dispGamedetails();
                obj2.shape.localToScene(obj2.shape.getBoundsInLocal());
                timeline_color_changer.get(timeline_color_changer.size() - 1).setCycleCount(Timeline.INDEFINITE);
                timeline_color_changer.get(timeline_color_changer.size() - 1).setAutoReverse(true);
                timeline_color_changer.get(timeline_color_changer.size() - 1).getKeyFrames().addAll(obj.animation);
                timeline_color_changer.get(timeline_color_changer.size() - 1).play();
                col_changer=0;
            }
            root.getChildren().add(obj.shape);
            timeline_obstacle.add(new Timeline());
            obstacles_list.add(obj);
            obstacles.add(obj.getRetObstacle());
            obj.shape.localToScene(obj.shape.getBoundsInLocal());
            timeline_obstacle.get(timeline_obstacle.size() - 1).setCycleCount(Timeline.INDEFINITE);
            timeline_obstacle.get(timeline_obstacle.size() - 1).setAutoReverse(true);
            timeline_obstacle.get(timeline_obstacle.size() - 1).getKeyFrames().addAll(obj.animation);
            timeline_obstacle.get(timeline_obstacle.size() - 1).play();
            for (Tile_object_animation tile_object_animation : obstacles_list) {
                tile_object_animation.shape.localToScene(tile_object_animation.shape.getBoundsInLocal());
            }
        }
    }

    @Override
    public void start(Stage myStage) throws Exception {
        if(isNew)
        {
            ArrayList<Tile_object_animation> initial_objects= background.screen_obstacles_Setup();
            for (Tile_object_animation initial_object : initial_objects) {
                obstacles_list.add(initial_object);
                obstacles.add(initial_object.getRetObstacle());
            }
        }
        else
        {
            obstacles_list =new ArrayList<>();
            color_changer_list =new ArrayList<>();
            star_list =new ArrayList<>();
            obstacle_animation_begin=new ArrayList<>();
            orientation_list=new ArrayList<>();
            txf=new Text();
            end_game_reason="";
            root2=null;
            a=0;
            rand = RandomGenerator.getInstance();
            timeline_obstacle  = new ArrayList<>();
            timeline_color_changer  = new ArrayList<>();
            timeline_star  = new ArrayList<>();
            background=new Background();
            timeline2=new Timeline();
            ball_tap_sound=new Media(ColorSwitchApp.class.getResource("/sounds/ball_tap.wav").toString());
            star_collection=new Media(ColorSwitchApp.class.getResource("/sounds/star_hit.wav").toString());
            color_changer_collection=new Media(ColorSwitchApp.class.getResource("/sounds/cc3.wav").toString());
            // ball set-up
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
                case 5: javaFX_ball =new Circle(ball.getX(),ball.getY(),5.0d,Color.PINK);
                    break;
                default: break;
            }

            //color changer set-up

            for (ColorChanger colorChanger : colorChangers) {
                color_changer_list.add(background.already_present_color_changer_star(colorChanger.getY(), colorChanger));
            }
            //star set up
            for (Star star : stars) {
                star_list.add(background.already_present_color_changer_star(star.getY(), star));
            }
            for (Obstacle obstacle : obstacles) {
                switch (obstacle.getClass().getName()) {
                    case "GUI_Rectangle":
                    case "GUI_Plus":
                    case "GUI_Triangle":
                        obstacles_list.add(background.already_present_rectangle_triangle_plus(obstacle, difficulty));
                        break;
                    case "GUI_Circle":
                        obstacles_list.add(background.already_present_circles(obstacle, difficulty));
                        break;
                    case "GUI_CircularLines":
                        obstacles_list.add(background.already_present_circular_lines(obstacle, difficulty));
                        break;
                    case "GUI_Dominoes":
                        obstacles_list.add(background.already_present_dominoes(obstacle, difficulty));
                        break;
                }
            }
        }


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
        txf.setTranslateX(520);
        txf.setTranslateY(40);
        txf.setStyle("-fx-background-color: #000000;-fx-text-inner-color: red; ");
        txf.setFont(Font.font("Verdana",20));
        txf.setFill(Color.RED);
        if(user.getName().length()>=7)
            txf.setText(user.getName().substring(0,6)+"\n   "+ score);
        else
            txf.setText(user.getName()+"\n "+ score);
        Group root = new Group();
        //root.getChildren().addAll(background,javaFX_ball);
        for(int i = 0; i< obstacles_list.size(); i++) {
            root.getChildren().add(obstacles_list.get(i).shape);
            timeline_obstacle.add(new Timeline());
            timeline_obstacle.get(i).setCycleCount(Timeline.INDEFINITE);
            timeline_obstacle.get(i).setAutoReverse(true);
            timeline_obstacle.get(i).getKeyFrames().addAll(obstacles_list.get(i).animation);
            timeline_obstacle.get(i).playFrom(Duration.millis(obstacles.get(i).getOrientation()));
            obstacle_animation_begin.add(System.currentTimeMillis());
        }

        for(int i=0;i<color_changer_list.size();i++)
        {
            root.getChildren().add((color_changer_list.get(i).shape));
            timeline_color_changer.add(new Timeline());
            timeline_color_changer.get(i).setCycleCount(Timeline.INDEFINITE);
            timeline_color_changer.get(i).setAutoReverse(true);
            timeline_color_changer.get(i).getKeyFrames().addAll(color_changer_list.get(i).animation);
            timeline_color_changer.get(i).play();
        }

        for(int i=0;i<star_list.size();i++)
        {
            root.getChildren().add(star_list.get(i).shape);
            timeline_star.add(new Timeline());
            timeline_star.get(i).setCycleCount(Timeline.INDEFINITE);
            timeline_star.get(i).setAutoReverse(true);
            timeline_star.get(i).getKeyFrames().addAll(star_list.get(i).animation);
            timeline_star.get(i).play();
        }


        root.getChildren().add(javaFX_ball);
        root.getChildren().add(button);
        root.getChildren().add(txf);
        Scene myScene = new Scene(root,600,800,Color.BLACK);
        myStage.setScene(myScene);
        myStage.initStyle(StageStyle.UNDECORATED);
        myStage.setTitle("Gameplay Animation");
        myStage.show();
        button.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                timeline2.stop();
                MediaPlayer buttonPlayer;
                buttonPlayer = new MediaPlayer(new Media(ColorSwitchApp.class.getResource("/sounds/button_click.wav").toString()));
                buttonPlayer.play();
                int j=0;
                for (Timeline timeline : timeline_obstacle) {
                    timeline.stop();
                    long time_of_animation =System.currentTimeMillis()-obstacle_animation_begin.get(j);
                    orientation_list.add((double) time_of_animation%timeline.getKeyFrames().get(0).getTime().toMillis());
                    j++;
                }
                timeline_obstacle.clear();
                //updating ball
                ball.updatePosition(300.0d, javaFX_ball.getCenterY());
                //updating stars
                for (int i = 0; i < star_list.size(); i++) {
                    stars.get(i).updatePosition(300.0d, star_list.get(i).shape.localToScene(star_list.get(i).shape.getBoundsInLocal()).getCenterY());
                }

                //updating colorChangers
                for (int i = 0; i < color_changer_list.size(); i++) {
                    colorChangers.get(i).updatePosition(300.0d, color_changer_list.get(i).shape.localToScene(color_changer_list.get(i).shape.getBoundsInLocal()).getCenterY());

                }
                //updating obstacles
                for (int i = 0; i < obstacles_list.size(); i++) {
                    obstacles.get(i).updateObstacle(obstacles_list.get(i).shape.localToScene(obstacles_list.get(i).shape.getBoundsInLocal()).getCenterX(), obstacles_list.get(i).shape.localToScene(obstacles_list.get(i).shape.getBoundsInLocal()).getCenterY(), true);
                    obstacles.get(i).setOrientation(orientation_list.get(i));
                }
                try {
                    root2 = FXMLLoader.load(getClass().getResource("pause_game.fxml"));
                    ColorSwitchApp.serialize();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage childStage = new Stage();
                childStage.setScene(new Scene(root2));
                childStage.initStyle(StageStyle.UNDECORATED);
                childStage.show();
                root.getChildren().clear();
                myStage.close();
            }
        });
       javaFX_ball.centerYProperty().addListener((observable, oldvalue, newvalue)->{
           if(a==0)
              a=checkShapeIntersection(javaFX_ball,root,myStage);
           if(javaFX_ball.getCenterY()>800)
             {
                 if(a==0) {
                     check_end_game_condition(root,myStage,"-> Ball out of Screen!!",1);
                 }
                 a=1;
             }
        });
        myScene.setOnKeyPressed(e-> {
            if (e.getCode() == KeyCode.W) {
                timeline2.stop();
                MediaPlayer mediaPlayer=new MediaPlayer(ball_tap_sound);
                mediaPlayer.play();
                timeline2.getKeyFrames().clear();
                KeyValue xValue  = new KeyValue(javaFX_ball.centerYProperty(), 805);
                KeyFrame keyFrame2  = new KeyFrame(Duration.millis((900- javaFX_ball.getCenterY())*20), xValue);
                timeline2.setCycleCount(1);
                timeline2.getKeyFrames().addAll(keyFrame2);
                javaFX_ball.setCenterY(javaFX_ball.getCenterY() - 20);
                checkShapeIntersection(javaFX_ball,root,myStage);
                if(javaFX_ball.getCenterY()<=400) {
                    shift_screen( 20,root);
                    javaFX_ball.setCenterY(400);
                }
                timeline2.play();
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
