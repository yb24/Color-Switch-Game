import java.io.Serializable;

public abstract class Obstacle extends Item implements Serializable
{
    protected final int colorScheme;
    protected final double width;
    protected double orientation;
    Obstacle(double x, double y, boolean b, int cs, double w,double or)
    {
        super(x,y,b);
        colorScheme = cs;
        width = w;
        orientation=or;
    }

    public double getOrientation() {
        return orientation;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

    public int getColorScheme() {
        return colorScheme;
    }

    public double getWidth() {
        return width;
    }

    public void updateObstacle(double x, double y, boolean b)
    {
        updatePosition(x,y);
        setIsOutOfScreen(b);
    }

    public void printObstacle()
    {
        System.out.println("x coordinate: " + itemPosition.getX());
        System.out.println("y coordinate: " + itemPosition.getY());
        System.out.println("colorScheme: " + colorScheme);
        System.out.println("width: " + width);
    }
}
