import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.util.Duration;
import java.util.ArrayList;

public class Background {
    private final ArrayList<Tile_object_animation> obstacle;
    private final RandomGenerator rand = RandomGenerator.getInstance();
    public Background()
    {
        obstacle=new ArrayList<>();
    }

    public ArrayList<Tile_object_animation> screen_obstacles_Setup()
    {
        Tile_object_animation obj= screen_obstacles(rand.nextInt(7) + 1,100,0);
        obstacle.add(obj);
        Tile_object_animation obj2= screen_obstacles(rand.nextInt(7) + 1,500,0);
        obstacle.add(obj2);
        return obstacle;
    }

    public Tile_object_animation screen_obstacles(int rand_int, double pos, double difficulty)
    {
        KeyValue rValue,rValue2 = null;
        Group obstacle_shape,color_changer,point_star;
        KeyFrame keyFrame1;
        int rand_num2;
        int rand_num3;
        new Tile_object_animation();
        Tile_object_animation obj;
        Obstacle oo;
        switch (rand_int) {
            case 1:
                rand_num2 = rand.nextInt(15)+85;
                rand_num3 = rand.nextInt(2);
                obstacle_shape = circleGenerator(300.0d, pos, rand_num2, rand_num3, 75,15);
                rValue = new KeyValue(obstacle_shape.rotateProperty(), 720);
                if(difficulty>=0.48d)
                    rValue2 = new KeyValue(obstacle_shape.translateXProperty(), Math.max(100*difficulty,50.0d));
                keyFrame1 = new KeyFrame(Duration.millis(12000*(1-difficulty)), rValue,rValue2);
                oo = new GUI_Circle(300.0d,pos,false,rand_num3,15.0d,rand_num2,75.0d,0,0.0d);
                obj=new Tile_object_animation(obstacle_shape, keyFrame1,oo);
                break;
            case 2:
                rand_num2 = rand.nextInt(15)+50;
                rand_num3=rand.nextInt(2);
                obstacle_shape = plusGenerator(260, pos, rand_num2, 10,rand_num3);
                rValue = new KeyValue(obstacle_shape.rotateProperty(), 720);
                if(difficulty>=0.48d)
                    rValue2 = new KeyValue(obstacle_shape.translateXProperty(), Math.max(100*difficulty,60.0d));
                keyFrame1 = new KeyFrame(Duration.millis(24000*(1-difficulty)), rValue,rValue2);
                oo = new GUI_Plus(260.0d,pos,false,rand_num3,10.0d,rand_num2,0.0d);
                obj=new Tile_object_animation(obstacle_shape, keyFrame1,oo);
                break;
            case 3:
                int side_length=rand.nextInt(60)+160;
                rand_num2=rand.nextInt(2);
                obstacle_shape = triangleGenerator(300.0d, pos,side_length, 10,rand_num2);
                rValue = new KeyValue(obstacle_shape.rotateProperty(), 1440);
                if(difficulty>=0.48d)
                    rValue2 = new KeyValue(obstacle_shape.translateXProperty(), Math.max(100*difficulty,50.0d));
                keyFrame1 = new KeyFrame(Duration.millis(32000*(1-difficulty)), rValue,rValue2);
                oo = new GUI_Triangle(300.0d,pos,false,rand_num2,10.0d,side_length,0.0d);
                obj=new Tile_object_animation(obstacle_shape, keyFrame1,oo);
                break;
            case 4:
                rand_num2=rand.nextInt(2);
                obstacle_shape = dominosGenerator(300, pos, 100,100, 10,rand_num2);
                rValue = new KeyValue(obstacle_shape.translateXProperty(), 200);
                keyFrame1 = new KeyFrame(Duration.millis(3000*(1-difficulty)), rValue);
                oo = new GUI_Dominoes(300.0d,pos,false,rand_num2,10.0d,100.0d,100.0d,0.0d);
                obj=new Tile_object_animation(obstacle_shape, keyFrame1,oo);
                break;
            case 5:
                rand_num2=rand.nextInt(50)+150;
                rand_num3=rand.nextInt(2);
                obstacle_shape = rectangleGenerator(300, pos,rand_num2,15,rand_num3);
                rValue = new KeyValue(obstacle_shape.rotateProperty(), 720);
                if(difficulty>=0.48d)
                    rValue2 = new KeyValue(obstacle_shape.translateXProperty(), Math.max(100*difficulty,60.0d));
                keyFrame1 = new KeyFrame(Duration.millis(24000*(1-difficulty)), rValue,rValue2);
                oo = new GUI_Rectangle(300.0d,pos,false,rand_num3,15.0d,rand_num2,0.0d);
                obj=new Tile_object_animation(obstacle_shape, keyFrame1,oo);
                break;
            case 6: rand_num2 = rand.nextInt(15)+70;
                rand_num3 = rand.nextInt(2);
                obstacle_shape = circleGenerator2(300.0d, pos, rand_num2, rand_num3, 75, 15);
                rValue = new KeyValue(obstacle_shape.rotateProperty(), 720);
                if(difficulty>=0.48d)
                    rValue2 = new KeyValue(obstacle_shape.translateXProperty(), Math.max(100*difficulty,50.0d));
                keyFrame1 = new KeyFrame(Duration.millis(12000*(1-difficulty)), rValue,rValue2);
                oo = new GUI_Circle(300.0d,pos,false,rand_num3,15.0d,rand_num2,75.0d,1,0.0d);
                obj=new Tile_object_animation(obstacle_shape, keyFrame1,oo);
                break;
            case 7:
                rand_num2=rand.nextInt(2);
                obstacle_shape=circularLines(200,pos,10,rand_num2);
                rValue = new KeyValue(obstacle_shape.rotateProperty(), 720);
                if(difficulty>=0.48d)
                    rValue2 = new KeyValue(obstacle_shape.translateXProperty(), Math.max(100*difficulty,60.0d));
                keyFrame1 = new KeyFrame(Duration.millis(24000*(1-difficulty)), rValue,rValue2);
                oo = new GUI_CircularLines(200.0d,pos,false,rand_num2,10.0d,0.0d);
                obj=new Tile_object_animation(obstacle_shape, keyFrame1,oo);
                break;
            case 8:  point_star=starGenerator(pos);
                rValue=new KeyValue(point_star.rotateProperty(), 720);
                keyFrame1 = new KeyFrame(Duration.millis(6000), rValue);
                obj=new Tile_object_animation(point_star, keyFrame1,null);
                break;
            case 9:color_changer=color_changer_Generator(pos);
                rValue=new KeyValue(color_changer.rotateProperty(), 720);
                keyFrame1 = new KeyFrame(Duration.millis(6000), rValue);
                obj=new Tile_object_animation(color_changer, keyFrame1,null);
                break;
            default:
                obj=new Tile_object_animation(null,null,null);
        }
        return obj;
    }

    public Tile_object_animation already_present_color_changer_star(double y_position,Consumables c)
    {
        Group consumable;
        if(c.getClass().getName().equals("ColorChanger"))
        {
            consumable =color_changer_Generator(y_position);
        }
        else
        {
            consumable=starGenerator(y_position);
        }
        KeyValue rValue=new KeyValue(consumable.rotateProperty(), 720);
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(6000), rValue);
        return (new Tile_object_animation(consumable, keyFrame1,null));

    }

    public Tile_object_animation already_present_rectangle_triangle_plus(Obstacle o,double difficulty)
    {
        Group obstacle;
        double y, w, sl;
        int cs;
        y=o.getY();
        cs=o.getColorScheme();
        w=o.getWidth();
        if(o.getClass().getName().equals("GUI_Plus"))
        {
            sl=((GUI_Plus)o).getHalfLength();
            obstacle=plusGenerator(260,y,sl,w,cs);
        }
        else if(o.getClass().getName().equals("GUI_Rectangle"))
        {
            sl=((GUI_Rectangle)o).getRectangleSideLength();
            obstacle=rectangleGenerator(300,y,sl,w,cs);
        }
        else
        {
            sl=((GUI_Triangle)o).getTriangleSideLength();
            obstacle=triangleGenerator(300,y,sl,w,cs);
        }
        KeyValue rValue=new KeyValue(obstacle.rotateProperty(), 1440);
        KeyValue rValue2=null;
        if(difficulty>=0.48d)
            rValue2 =new KeyValue(obstacle.translateXProperty(), Math.max(100*difficulty,50.0d));
        KeyFrame keyFrame1;
        if(o.getClass().getName().equals("GUI_Triangle"))
            keyFrame1 = new KeyFrame(Duration.millis(32000*(1-difficulty)), rValue,rValue2);
        else
            keyFrame1 = new KeyFrame(Duration.millis(24000*(1-difficulty)), rValue,rValue2);
        return (new Tile_object_animation(obstacle, keyFrame1,o));
    }

    public Tile_object_animation already_present_circles(Obstacle o,double difficulty)
    {
        Group obstacle;
        double y, w,r,al;
        int t,cs;
        y=o.getY();
        w=o.getWidth();
        r=((GUI_Circle)o).getRadius();
        al=((GUI_Circle)o).getArcLength();
        t=((GUI_Circle)o).getType();
        cs=o.getColorScheme();
        if(t==0)
            obstacle=circleGenerator(300,y,r,cs,al,w);
        else
            obstacle=circleGenerator2(300,y,r,cs,al,w);
        KeyValue rValue=new KeyValue(obstacle.rotateProperty(), 720);
        KeyValue rValue2=null;
        if(difficulty>=0.48d)
            rValue2 =new KeyValue(obstacle.translateXProperty(), Math.max(100*difficulty,50.0d));
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(12000*(1-difficulty)), rValue,rValue2);
        return (new Tile_object_animation(obstacle, keyFrame1,o));
    }

    public Tile_object_animation already_present_circular_lines(Obstacle o,double difficulty)
    {
        Group obstacle;
        double y, w;
        int cs;
        y=o.getY();
        w=o.getWidth();
        cs=o.getColorScheme();
        obstacle=circularLines(200,y,w,cs);
        KeyValue rValue=new KeyValue(obstacle.rotateProperty(), 720);
        KeyValue rValue2=null;
        if(difficulty>=0.48d)
            rValue2 =new KeyValue(obstacle.translateXProperty(), Math.max(100*difficulty,60.0d));
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(24000*(1-difficulty)), rValue,rValue2);
        return (new Tile_object_animation(obstacle, keyFrame1,o));
    }

    public Tile_object_animation already_present_dominoes(Obstacle o,double difficulty)
    {
        Group obstacle;
        double y,w,sl,p;
        int cs;
        y=o.getY();
        w=o.getWidth();
        cs=o.getColorScheme();
        sl=((GUI_Dominoes)o).getSideLength();
        p=((GUI_Dominoes)o).getP();
        obstacle=dominosGenerator(300,y,sl,p,w,cs);
        KeyValue rValue=new KeyValue(obstacle.translateXProperty(), 200);
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(3000*(1-difficulty)), rValue);
        return (new Tile_object_animation(obstacle, keyFrame1,o));
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

    public Group circleGenerator(double centerX, double centerY, double radius, int colorScheme, double arcLength, double width)
    {
        Arc[] arr_arcs = new Arc[4];
        for(int i=0;i<4;i++)
        {
            arr_arcs[i] = new Arc(centerX,centerY,radius,radius,i*360.0d/4 ,arcLength);
            arr_arcs[i].setFill(null);
            switch (colorScheme)
            {
                case 0:
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
                case 1:
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

    public Group circleGenerator2(double centerX, double centerY, double radius, int colorScheme, double arcLength, double width)
    {
        Arc[] arr_arcs = new Arc[8];
        for(int i=0;i<8;i++)
        {
            if(i<=3)
                arr_arcs[i] = new Arc(centerX,centerY,radius,radius,(i%4)*360.0d/4,arcLength);
            else
                arr_arcs[i] = new Arc(centerX,centerY,radius+20,radius+20,(i%4)*360.0d/4,arcLength);
            arr_arcs[i].setFill(null);
            switch (colorScheme)
            {
                case 0:
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
                case 1:
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
        Line[] arr_lines = new Line[5];

        for(int i=0; i<5; i++)
        {
                arr_lines[i] = new Line(x_centre+(i-2)*p,y_centre-side_length/2,x_centre+(i-2)*p,y_centre+side_length/2);

            switch (colorScheme)
            {
                case 0:
                {
                    switch (i)
                    {
                        case 0:
                            arr_lines[i].setStroke(Color.BLUE);
                            arr_lines[i].setStrokeWidth(width+2);
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
