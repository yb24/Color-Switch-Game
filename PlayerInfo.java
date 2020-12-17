import java.io.Serializable;
import java.util.ArrayList;

public class PlayerInfo implements Serializable
{
    private static final long serialVersionUID = 777L;
    private final Player thePlayer;
    private final ArrayList<Game> savelist;

    public PlayerInfo()
    {
        thePlayer = new Player();
        savelist = new ArrayList<>();
    }

    public PlayerInfo(Player p)
    {
        thePlayer = p;
        savelist = new ArrayList<>();
    }

    public Player getPlayer()
    {
        return thePlayer;
    }

    public boolean checkEquality(Player other)
    {
        return thePlayer==other;
    }

    public String callGetPlayerString()
    {
        return thePlayer.getPlayerString();
    }

    public void forSaveListDisplay(ArrayList<String> a)
    {
        for(Game g: savelist)
        {
            a.add(g.getGameString());
        }
    }

    public void addGame(Game gameToBeSaved)
    {
        savelist.add(gameToBeSaved);
    }

    public void removeGame(int indexOfGame)
    {
        if(indexOfGame>=0 && indexOfGame<savelist.size())
            savelist.remove(indexOfGame);
    }

    public int getUniqueID()
    {
        int tempGameID=0;
        for(Game g: savelist)
        {
            tempGameID = Math.max(g.getGameID(),tempGameID);
        }
        tempGameID++;

        return tempGameID;
    }

    public void printPlayerInfo()
    {
        System.out.println("thePlayer :" + thePlayer);
        System.out.println("saveList :");
        for(Game g: savelist)
        {
            g.dispGamedetails();
        }
    }
}
