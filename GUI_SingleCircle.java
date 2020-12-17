import java.io.Serializable;

public class GUI_SingleCircle extends Obstacle implements Serializable
{
    private static final long serialVersionUID = 23994L;
    private final int type; //0-singleCircle 1-doubleCircle
    private final double radius;
    private final double arcLength;

    GUI_SingleCircle(double x, double y, boolean b, int cs, double w, double r, double al, int t)
    {
        super(x,y,b,cs,w);
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
}
