import java.io.Serializable;

public class Position implements Serializable
{
    private static final long serialVersionUID = 111L;
    private double x;
    private double y;

    Position(double x, double y)
    {
        this.x=x;
        this.y=y;
    }

    public void setPosition(double p, double q)
    {
        x=p;
        y=q;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }
}
