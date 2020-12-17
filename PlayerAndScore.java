import java.io.Serializable;

public class PlayerAndScore implements Serializable,Comparable<PlayerAndScore>
{
    private static final long serialVersionUID = 32L;
    private final String pName;
    private final int pScore;

    PlayerAndScore(String pName, int pScore)
    {
        this.pName = pName;
        this.pScore = pScore;
    }

    public int getpScore()
    {
        return pScore;
    }

    public String getPlayerAndScore()
    {
        return pName + "    Score: " + pScore;
    }

    @Override
    public int compareTo(PlayerAndScore o) {
        return o.getpScore() - this.pScore;
    }

    @Override
    public String toString()
    {
        return "pName: " + pName + " score: " + pScore;
    }
}
