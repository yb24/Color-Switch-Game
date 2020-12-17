import java.io.Serializable;

public class GUI_Rectangle extends Obstacle implements Serializable
{
    private static final long serialVersionUID = 71712L;
    private final double rectangleSideLength;

    GUI_Rectangle(double x, double y, boolean b, int cs, double w, double rsl)
    {
        super(x,y,b,cs,w);
        this.rectangleSideLength = rsl;
    }

    public double getRectangleSideLength() {
        return rectangleSideLength;
    }
}
