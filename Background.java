import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Random;

public class Background {
    ArrayList<Tile_object_animation> obstacle;
    Random rand = new Random();
    public Background()
    {
        obstacle=new ArrayList<>();
    }

    public ArrayList<Tile_object_animation> screen_obstacles_Setup()
    {
        int pos=0;
        for(int j=1;j<=3;j++) {
            int rand_int = rand.nextInt(6) + 1;
            if(j!=1)
                pos-=300;
            else
                pos=800;
            Tile_object_animation obj=screen_obstacles(rand_int,pos,0);
            obstacle.add(obj);
        }
        return obstacle;
    }
    public Tile_object_animation screen_obstacles(int rand_int, double pos, int difficulty)
    {
        KeyValue rValue,rValue2 = null;
        Group obstacle_shape=null,color_changer,point_star;
        KeyFrame keyFrame1=null;
        int rand_num2;
        int rand_num3;
        rand_int=4;
        Tile_object_animation obj=new Tile_object_animation();
        switch (rand_int) {
            case 1:
                rand_num2 = rand.nextInt(15)+85;
                rand_num3 = rand.nextInt(2);
                int num4=-1;
                if(rand_num3==0)
                    num4=2;
                else
                    num4=4;
                obstacle_shape = circleGenerator(300.0d, pos, rand_num2, num4, 75, 0,15);
                rValue = new KeyValue(obstacle_shape.rotateProperty(), 720);
                if(difficulty==1)
                    rValue2 = new KeyValue(obstacle_shape.translateXProperty(), 800);
                keyFrame1 = new KeyFrame(Duration.millis(6000), rValue,rValue2);
                obj=new Tile_object_animation(obstacle_shape, keyFrame1);
                break;
            case 2:
                rand_num2 = rand.nextInt(15)+50;
                rand_num3=rand.nextInt(2);
                obstacle_shape = plusGenerator(260, pos, rand_num2, 10,rand_num3);
                rValue = new KeyValue(obstacle_shape.rotateProperty(), 720);
                if(difficulty==1)
                    rValue2 = new KeyValue(obstacle_shape.translateXProperty(), 800);
                keyFrame1 = new KeyFrame(Duration.millis(6000), rValue,rValue2);
                obj=new Tile_object_animation(obstacle_shape, keyFrame1);
                break;
            case 3:
                int side_length=rand.nextInt(60)+160;
                rand_num2=rand.nextInt(2);
                obstacle_shape = triangleGenerator(300.0d, pos,side_length, 10,rand_num2);
                rValue = new KeyValue(obstacle_shape.rotateProperty(), 1440);
                if(difficulty==1)
                    rValue2 = new KeyValue(obstacle_shape.translateXProperty(), 800);
                keyFrame1 = new KeyFrame(Duration.millis(12000), rValue,rValue2);
                obj=new Tile_object_animation(obstacle_shape, keyFrame1);
                break;
            case 4:
                rand_num2=rand.nextInt(2);
                obstacle_shape = dominosGenerator(300, pos, 100,100, 10,rand_num2);
                rValue = new KeyValue(obstacle_shape.translateXProperty(), 200);
                if(difficulty==1)
                    rValue2 = new KeyValue(obstacle_shape.translateXProperty(), 200);
                keyFrame1 = new KeyFrame(Duration.millis(1500), rValue,rValue2);
                obj=new Tile_object_animation(obstacle_shape, keyFrame1);
                break;
            case 5:
                rand_num2=rand.nextInt(50)+150;
                rand_num3=rand.nextInt(10);
                obstacle_shape = rectangleGenerator(300, pos,rand_num2,15,rand.nextInt(2));
                rValue = new KeyValue(obstacle_shape.rotateProperty(), 720);
                if(difficulty==1)
                    rValue2 = new KeyValue(obstacle_shape.translateXProperty(), 800);
                keyFrame1 = new KeyFrame(Duration.millis(6000), rValue,rValue2);
                obj=new Tile_object_animation(obstacle_shape, keyFrame1);
                break;
            case 6: rand_num2 = rand.nextInt(15)+70;
                rand_num3 = rand.nextInt(2);
                num4=-1;
                if(rand_num3==0)
                    num4=2;
                else
                    num4=4;
                obstacle_shape = circleGenerator2(300.0d, pos, rand_num2, num4, 75, 45,15);
                rValue = new KeyValue(obstacle_shape.rotateProperty(), 720);
                if(difficulty==1)
                    rValue2 = new KeyValue(obstacle_shape.translateXProperty(), 800);
                keyFrame1 = new KeyFrame(Duration.millis(6000), rValue,rValue2);
                obj=new Tile_object_animation(obstacle_shape, keyFrame1);
                break;
            case 7:
                rand_num2=rand.nextInt(2);
                obstacle_shape=circularLines(200,pos,10,rand_num2);
                rValue = new KeyValue(obstacle_shape.rotateProperty(), 720);
                if(difficulty==1)
                    rValue2 = new KeyValue(obstacle_shape.translateXProperty(), 800);
                keyFrame1 = new KeyFrame(Duration.millis(12000), rValue,rValue2);
                obj=new Tile_object_animation(obstacle_shape, keyFrame1);
                break;
            case 8:  point_star=starGenerator(pos);
                rValue=new KeyValue(point_star.rotateProperty(), 720);
                keyFrame1 = new KeyFrame(Duration.millis(6000), rValue);
                obj=new Tile_object_animation(point_star, keyFrame1);
                break;
            case 9:color_changer=color_changer_Generator(pos);
                rValue=new KeyValue(color_changer.rotateProperty(), 720);
                keyFrame1 = new KeyFrame(Duration.millis(6000), rValue);
                obj=new Tile_object_animation(color_changer, keyFrame1);
                break;
            default:
                obj=new Tile_object_animation(null,null);
        }
        return obj;
    }
    public Group color_changer_Generator(double pos)
    {
        Ellipse color_changer=new Ellipse(300, pos, 30,30);
        color_changer.setStroke(Color.DARKGREY);
        Image img=new Image("color_changer.gif");
        color_changer.setFill(new ImagePattern(img));
        return new Group(color_changer);
    }
    public Group starGenerator(double pos)
    {
        Circle c =new Circle(300,pos,20);
        c.setStroke(Color.BLACK);
        Image im=new Image("star.gif");
        c.setFill(new ImagePattern(im));

        return new Group(c);
    }
    public Group circularLines(double x_centre, double y_centre, double width, int colorScheme)
    {
        Line[] arr_lines = new Line[8];

        for(int i=0; i<8; i++)
        {
            double x1=x_centre+100*Math.cos(Math.toRadians(i*45));
            double y1=y_centre+100*Math.sin(Math.toRadians(i*45));
            arr_lines[i] = new Line(x1,y1,x1+40,y1+40);
            switch (colorScheme)
            {
                case 0:
                {
                    switch (i%4)
                    {
                        case 0:
                        case 4:
                            arr_lines[i].setStrokeWidth(width+2);
                            arr_lines[i].setStroke(Color.BLUE);
                            break;
                        case 1:
                            arr_lines[i].setStroke(Color.YELLOW);
                            arr_lines[i].setStrokeWidth(width+2);
                            break;
                        case 2:
                            arr_lines[i].setStroke(Color.RED);
                            arr_lines[i].setStrokeWidth(width+2);
                            break;
                        case 3:
                            arr_lines[i].setStroke(Color.GREEN);
                            arr_lines[i].setStrokeWidth(width+2);
                            break;
                        default: break;
                    }
                }
                break;
                case 1:
                {
                    switch (i%4)
                    {
                        case 0:
                        case 4:
                            arr_lines[i].setStroke(Color.BLUE);
                            arr_lines[i].setStrokeWidth(width+2);
                            break;
                        case 1:
                            arr_lines[i].setStroke(Color.GREEN);
                            arr_lines[i].setStrokeWidth(width+2);
                            break;
                        case 2:
                            arr_lines[i].setStroke(Color.RED);
                            arr_lines[i].setStrokeWidth(width+2);
                            break;
                        case 3:
                            arr_lines[i].setStroke(Color.YELLOW);
                            arr_lines[i].setStrokeWidth(width+2);
                            break;
                        default: break;
                    }
                }
                break;
                default: break;
            }
        }
        return new Group(arr_lines[0],arr_lines[1],arr_lines[2],arr_lines[3],arr_lines[4],arr_lines[5],arr_lines[6],arr_lines[7]);
    }
    public Group circleGenerator(double centerX, double centerY, double radius, int colorScheme, double arcLength, double offset, double width)
    {
        Arc[] arr_arcs = new Arc[4];
        for(int i=0;i<4;i++)
        {
            arr_arcs[i] = new Arc(centerX,centerY,radius,radius,i*360.0d/4 + offset,arcLength);
            arr_arcs[i].setFill(null);
            switch (colorScheme)
            {
                case 2:
                {
                    switch (i)
                    {
                        case 0: arr_arcs[i].setStroke(Color.RED); break;
                        case 1: arr_arcs[i].setStroke(Color.GREEN); break;
                        case 2: arr_arcs[i].setStroke(Color.BLUE); break;
                        case 3: arr_arcs[i].setStroke(Color.YELLOW); break;
                        default: break;
                    }
                }
                break;
                case 4:
                {
                    switch (i)
                    {
                        case 0: arr_arcs[i].setStroke(Color.GREEN); break;
                        case 1: arr_arcs[i].setStroke(Color.RED); break;
                        case 2: arr_arcs[i].setStroke(Color.YELLOW); break;
                        case 3: arr_arcs[i].setStroke(Color.BLUE); break;
                        default: break;
                    }
                }
                break;
                default: break;
            }
            arr_arcs[i].setStrokeWidth(width);
            arr_arcs[i].setType(ArcType.OPEN);
        }

        return new Group(arr_arcs[0],arr_arcs[1],arr_arcs[2],arr_arcs[3]);
    }
    public Group circleGenerator2(double centerX, double centerY, double radius, int colorScheme, double arcLength, double offset, double width)
    {
        Arc[] arr_arcs = new Arc[8];
        for(int i=0;i<8;i++)
        {
            if(i<=3)
                arr_arcs[i] = new Arc(centerX,centerY,radius,radius,(i%4)*360.0d/4 + offset,arcLength);
            else
                arr_arcs[i] = new Arc(centerX,centerY,radius+20,radius+20,(i%4)*360.0d/4 + offset,arcLength);
            arr_arcs[i].setFill(null);
            switch (colorScheme)
            {
                case 2:
                {
                    switch (i%4)
                    {
                        case 0: arr_arcs[i].setStroke(Color.RED); break;
                        case 1: arr_arcs[i].setStroke(Color.GREEN); break;
                        case 2: arr_arcs[i].setStroke(Color.BLUE); break;
                        case 3: arr_arcs[i].setStroke(Color.YELLOW); break;
                        default: break;
                    }
                }
                break;
                case 4:
                {
                    switch (i%4)
                    {
                        case 0: arr_arcs[i].setStroke(Color.GREEN); break;
                        case 1: arr_arcs[i].setStroke(Color.RED); break;
                        case 2: arr_arcs[i].setStroke(Color.YELLOW); break;
                        case 3: arr_arcs[i].setStroke(Color.BLUE); break;
                        default: break;
                    }
                }
                break;
                default: break;
            }
            arr_arcs[i].setStrokeWidth(width);
            arr_arcs[i].setType(ArcType.OPEN);
        }

        return new Group(arr_arcs[0],arr_arcs[1],arr_arcs[2],arr_arcs[3],arr_arcs[4],arr_arcs[5],arr_arcs[6],arr_arcs[7]);
    }
    public Group plusGenerator(double x_centre, double y_centre, double halfLength, double width, int colorScheme)
    {
        Line[] arr_lines = new Line[4];

        arr_lines[0] = new Line(x_centre, y_centre -halfLength, x_centre, y_centre);
        arr_lines[1] = new Line(x_centre -halfLength, y_centre, x_centre, y_centre);
        arr_lines[2] = new Line(x_centre, y_centre, x_centre, y_centre +halfLength);
        arr_lines[3] = new Line(x_centre, y_centre, x_centre +halfLength, y_centre);

        for(int i=0; i<4; i++)
        {
            arr_lines[i].setStrokeWidth(width);
        }

        switch(colorScheme)
        {
            case 0:  arr_lines[0].setStroke(Color.YELLOW);
                arr_lines[1].setStroke(Color.RED);
                arr_lines[2].setStroke(Color.GREEN);
                arr_lines[3].setStroke(Color.BLUE);
                break;
            case 1:    arr_lines[0].setStroke(Color.GREEN);
                arr_lines[1].setStroke(Color.RED);
                arr_lines[2].setStroke(Color.BLUE);
                arr_lines[3].setStroke(Color.YELLOW);
        }

        return new Group(arr_lines[0],arr_lines[1],arr_lines[2],arr_lines[3]);
    }
    public Group triangleGenerator(double x_centre, double y_centre, double side_length, double width, int colorScheme)
    {
        double x1,y1,x2,y2,x3,y3,change1,change2,change3;
        change1=side_length*Math.cos(Math.toRadians(60));
        change2=side_length*Math.cos(Math.toRadians(60))*Math.tan(Math.toRadians(30));
        double v = side_length * Math.sin(Math.toRadians(60));
        change3= v -change2;

        x1= x_centre;
        y1= y_centre -change3;
        x2= x_centre -change1;
        x3= x_centre +change1;
        y2= y_centre +change2;
        y3=y2;
        double x1a,y1a,x1b,y1b,x2a,y2a,x2b,y2b,x3a,y3a,x3b,y3b;
        double l=side_length/3;
        double l1=l*Math.sin(Math.toRadians(60.0));
        double l2=l*Math.cos(Math.toRadians(60.0));
        x1a=x1-l2;
        x1b=x1+l2;
        y1a=y1+l1;
        y1b=y1a;
        x2b=x2+l2;
        y2b=y2-l1;
        x2a=x2+l;
        y2a=y2;
        x3b=x3-l;
        y3b=y3;
        x3a=x3-l2;
        y3a=y3-l1;
        Line[] arr_sides = new Line[9];
        arr_sides[0] = new Line(x1,y1,x1a,y1a);
        arr_sides[1] = new Line(x1a,y1a,x2b,y2b);
        arr_sides[2] = new Line(x2b,y2b,x2,y2);

        arr_sides[3] = new Line(x2,y2,x2a,y2a);
        arr_sides[4] = new Line(x2a,y2a,x3b,y3b);
        arr_sides[5] = new Line(x3b,y3b,x3,y3);

        arr_sides[6] = new Line(x3,y3,x3a,y3a);
        arr_sides[7] = new Line(x3a,y3a,x1b,y1b);
        arr_sides[8] = new Line(x1b,y1b,x1,y1);

        for(int i=0; i<9; i++)
        {
            arr_sides[i].setStrokeWidth(width);
        }

        switch(colorScheme)
        {
            case 0:
                arr_sides[0].setStroke(Color.BLUE);
                arr_sides[8].setStroke(Color.BLUE);
                arr_sides[2].setStroke(Color.YELLOW);
                arr_sides[3].setStroke(Color.YELLOW);
                arr_sides[5].setStroke(Color.RED);
                arr_sides[6].setStroke(Color.RED);
                arr_sides[1].setStroke(Color.GREEN);
                arr_sides[4].setStroke(Color.GREEN);
                arr_sides[7].setStroke(Color.GREEN);
                break;
            case 1:
                arr_sides[0].setStroke(Color.RED);
                arr_sides[8].setStroke(Color.RED);
                arr_sides[2].setStroke(Color.GREEN);
                arr_sides[3].setStroke(Color.GREEN);
                arr_sides[5].setStroke(Color.YELLOW);
                arr_sides[6].setStroke(Color.YELLOW);
                arr_sides[1].setStroke(Color.BLUE);
                arr_sides[4].setStroke(Color.BLUE);
                arr_sides[7].setStroke(Color.BLUE);
                break;
            default:
        }

        return new Group(arr_sides[0],arr_sides[1],arr_sides[2],arr_sides[3],arr_sides[4],arr_sides[5],arr_sides[6],arr_sides[7],arr_sides[8]);
  }
    public Group dominosGenerator(double x_centre,double y_centre,double side_length,double p, double width, int colorScheme)
    {
        Line[] arr_lines = new Line[6];

        for(int i=0; i<6; i++)
        {
                arr_lines[i] = new Line(x_centre+(i-2)*p,y_centre-side_length/2,x_centre+(i-2)*p,y_centre+side_length/2);

            switch (colorScheme)
            {
                case 0:
                {
                    switch (i)
                    {
                        case 0:
                        case 1:
                            arr_lines[i].setStroke(Color.YELLOW);
                            arr_lines[i].setStrokeWidth(width+2);
                            break;
                        case 2:
                            arr_lines[i].setStroke(Color.RED);
                            arr_lines[i].setStrokeWidth(width+2);
                            break;
                        case 3:
                            arr_lines[i].setStroke(Color.GREEN);
                            arr_lines[i].setStrokeWidth(width+2);
                            break;
                        case 4:
                            arr_lines[i].setStrokeWidth(width+2);
                            arr_lines[i].setStroke(Color.BLUE);
                            break;
                        default: break;
                    }
                }
                break;
                case 1:
                {
                    switch (i)
                    {
                        case 0:
                        case 4:
                            arr_lines[i].setStroke(Color.BLUE);
                            arr_lines[i].setStrokeWidth(width+5);
                            break;
                        case 1:
                            arr_lines[i].setStroke(Color.GREEN);
                            arr_lines[i].setStrokeWidth(width+2);
                            break;
                        case 2:
                            arr_lines[i].setStroke(Color.RED);
                            arr_lines[i].setStrokeWidth(width);
                            break;
                        case 3:
                            arr_lines[i].setStroke(Color.YELLOW);
                            arr_lines[i].setStrokeWidth(width+2);
                            break;
                        default: break;
                    }
                }
                break;
                default: break;
            }
        }

        return new Group(arr_lines[0],arr_lines[1],arr_lines[2],arr_lines[3],arr_lines[4]);
    }
    public Group rectangleGenerator(double x_centre, double y_centre, double side_length, double width, int colorScheme)
    {
        Line[] arr_sides = new Line[4];
        arr_sides[0] = new Line(x_centre+side_length/2, y_centre,x_centre, y_centre+side_length/2);
        arr_sides[1] = new Line(x_centre, y_centre+side_length/2,x_centre-side_length/2, y_centre);
        arr_sides[2] = new Line(x_centre-side_length/2, y_centre,x_centre,y_centre-side_length/2);
        arr_sides[3] = new Line(x_centre,y_centre-side_length/2,x_centre+side_length/2, y_centre);

        for(int i=0; i<4; i++)
        {
            arr_sides[i].setStrokeWidth(width);
        }

        switch (colorScheme)
        {
            case 0:
                arr_sides[0].setStroke(Color.GREEN);
                arr_sides[1].setStroke(Color.RED);
                arr_sides[2].setStroke(Color.YELLOW);
                arr_sides[3].setStroke(Color.BLUE);
                break;
            case 1: arr_sides[2].setStroke(Color.GREEN);
                arr_sides[1].setStroke(Color.RED);
                arr_sides[0].setStroke(Color.YELLOW);
                arr_sides[3].setStroke(Color.BLUE);
                break;
        }
        return new Group(arr_sides[0],arr_sides[1],arr_sides[2],arr_sides[3]);
    }
}
