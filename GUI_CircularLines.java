import java.io.Serializable;

public class GUI_CircularLines extends Obstacle implements Serializable
{
    private static final long serialVersionUID = 987L;

    GUI_CircularLines(double x, double y, boolean b, int cs, double w)
    {
        super(x,y,b,cs,w);
    }
}
