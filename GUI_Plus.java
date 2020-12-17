import java.io.Serializable;

public class GUI_Plus extends Obstacle implements Serializable
{
    private static final long serialVersionUID = 1010L;
    private final double halfLength;

    GUI_Plus(double x, double y, boolean b, int cs, double w, double hl)
    {
        super(x,y,b,cs,w);
        halfLength = hl;
    }

    public double getHalfLength() {
        return halfLength;
    }
}
