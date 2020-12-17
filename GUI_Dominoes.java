import java.io.Serializable;

public class GUI_Dominoes extends Obstacle implements Serializable
{
    private static final long serialVersionUID = 8766L;
    private final double sideLength,p;

    GUI_Dominoes(double x, double y, boolean b, int cs, double w, double sl, double p,double or)
    {
        super(x,y,b,cs,w,or);
        this.sideLength=sl;
        this.p=p;
    }

    public double getSideLength() {
        return sideLength;
    }

    public double getP() {
        return p;
    }

    @Override
    public void printObstacle() {
        super.printObstacle();
        System.out.println("sideLength: " + sideLength);
        System.out.println("p: " + p);
    }
}
