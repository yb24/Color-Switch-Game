import java.io.Serializable;

public abstract class Consumables extends Item implements Serializable
{
    Consumables(double x, double y, boolean b)
    {
        super(x,y,b);
    }
}
