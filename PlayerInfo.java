import java.io.Serializable;
import java.util.ArrayList;

public class PlayerInfo implements Serializable
{
    private static final long serialVersionUID = 777L;
    private final Player thePlayer;
    private final ArrayList<Game> savelist;

    public PlayerInfo(Player p)
    {
        thePlayer = p;
        savelist = new ArrayList<>();
    }

    public boolean checkSaveList(int current_game_id)
    {
        for(Game g: savelist)
        {
            if(g.getGameID()==current_game_id)
                return true;
        }
        return false;
    }

    public Player getPlayer()
    {
        return thePlayer;
    }

    public Game getGame(int indexOfSavedGame)
    {
        return savelist.get(indexOfSavedGame);
    }

    public int getSizeOfSavelist()
    {
        return savelist.size();
    }

    public boolean checkEquality(Player other)
    {
        return thePlayer==other;
    }

    public void callSetPremiumStatus(boolean b)
    {
        thePlayer.setPremiumStatus(b);
    }

    public boolean callGetPremiumStatus()
    {
        return thePlayer.getPremiumStatus();
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

    public void updateExtraLife()
    {
        for(Game g:savelist)
        {
            if(g.getExtraLife()==0)
                g.setExtraLife(1);
        }
    }
}
