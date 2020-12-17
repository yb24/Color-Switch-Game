import java.io.Serializable;

public class DummyGame implements Serializable
{
    private static final long serialVersionUID = 888L;
    private final int GameID;
    private final int var1;

    public DummyGame()
    {
        GameID = -1;
        var1 = 0;
    }

    public DummyGame(int GameID, int var1)
    {
        this.GameID = GameID;
        this.var1 = var1;
        //gameobj = new collision2();
    }

    public int getGameID()
    {
        return GameID;
    }

    public String getDummyGameString()
    {
        return "Game " + GameID + "   score: " + var1;
    }

    public void printDummyGameDetails()
    {
        System.out.println("GameID: " + GameID + " var1: " + var1);
    }
}