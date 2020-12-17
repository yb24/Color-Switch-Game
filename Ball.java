import java.io.Serializable;

public class Ball extends Item implements Serializable
{
    private static final long serialVersionUID = 4324L;
    private int colorCodeOfBall; //1-RED 2-GREEN 3-BLUE 4-YELLOW 5-PINK

    Ball()
    {
        super(300.0d,700.0d,false);
        colorCodeOfBall = 1;
    }

    public int getColorCodeOfBall()
    {
        return colorCodeOfBall;
    }

    public void setColorCodeOfBall(int val)
    {
        colorCodeOfBall = val;
    }
}
