import java.io.Serializable;

public class GUI_Triangle extends Obstacle implements Serializable
{
    private static final long serialVersionUID = 23994L;
    private final double triangleSideLength;

    GUI_Triangle(double x, double y, boolean b, int cs, double w, double tsl)
    {
        super(x,y,b,cs,w);
        triangleSideLength=tsl;
    }

    public double getTriangleSideLength() {
        return triangleSideLength;
    }
}
