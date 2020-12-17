import java.io.Serializable;

public abstract class Item implements Serializable
{
    private static final long serialVersionUID = 4435L;
    protected Position itemPosition;
    protected boolean isOutOfScreen;

    public Item(double x, double y, boolean isOutOfScreen)
    {
        this.itemPosition = new Position(x,y);
        this.isOutOfScreen = isOutOfScreen;
    }

    public Position getPosition()
    {
        return itemPosition;
    }

    public double getX()
    {
        return itemPosition.getX();
    }

    public double getY()
    {
        return itemPosition.getY();
    }

    public void updatePosition(double a, double b)
    {
        itemPosition.setPosition(a,b);
    }

    public void setIsOutOfScreen(boolean bb)
    {
        isOutOfScreen = bb;
    }
}
