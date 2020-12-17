import java.io.Serializable;

public class GUI_Circle extends Obstacle implements Serializable
{
    private static final long serialVersionUID = 23994L;
    private final int type; //0-singleCircle 1-doubleCircle
    private final double radius;
    private final double arcLength;

    GUI_Circle(double x, double y, boolean b, int cs, double w, double r, double al, int t,double or)
    {
        super(x,y,b,cs,w,or);
        radius = r;
        arcLength = al;
        type = t;
    }

    public double getRadius() {
        return radius;
    }

    public double getArcLength() {
        return arcLength;
    }

    public int getType() {
        return type;
    }


    @Override
    public void printObstacle()
    {
        super.printObstacle();
        System.out.println("radius: " + radius);
        System.out.println("arcLength: " + arcLength);
        System.out.println("type: " + type);
    }
}
