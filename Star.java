import java.io.Serializable;

public class Star extends Consumables implements Serializable
{
    Star(double x, double y)
    {
        super(x,y,false);
    }
}
