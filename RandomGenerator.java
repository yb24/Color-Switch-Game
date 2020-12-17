import java.util.Random;

public class RandomGenerator extends Random
{
    public static RandomGenerator rg = null;

    private RandomGenerator()
    {
    }

    public static RandomGenerator getInstance()
    {
        if(rg==null)
            rg = new RandomGenerator();

        return rg;
    }
}
