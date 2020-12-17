import java.io.Serializable;

public class Obstacle extends Item implements Serializable
{
    private final int colorScheme;
    private final double width;

    Obstacle(double x, double y, boolean b, int cs, double w)
    {
        super(x,y,b);
        colorScheme = cs;
        width = w;
    }

    public int getColorScheme() {
        return colorScheme;
    }

    public double getWidth() {
        return width;
    }
}
