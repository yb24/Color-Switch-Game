import java.io.Serializable;

public class GUI_Plus extends Obstacle implements Serializable
{
    private static final long serialVersionUID = 1010L;
    private final double halfLength;

    GUI_Plus(double x, double y, boolean b, int cs, double w, double hl,double or)
    {
        super(x,y,b,cs,w,or);
        halfLength = hl;
    }

    public double getHalfLength() {
        return halfLength;
    }

    @Override
    public void printObstacle() {
        super.printObstacle();
        System.out.println("halfLength: " + halfLength);
    }
}
