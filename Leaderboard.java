import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Leaderboard implements Serializable
{
    private static final long serialVersionUID = 56L;
    private final ArrayList<PlayerAndScore> topEntries;

    Leaderboard()
    {
        topEntries = new ArrayList<PlayerAndScore>(5);
        topEntries.add(new PlayerAndScore("GenericName1",5));
        topEntries.add(new PlayerAndScore("GenericName2",4));
        topEntries.add(new PlayerAndScore("GenericName3",3));
        topEntries.add(new PlayerAndScore("GenericName4",2));
        topEntries.add(new PlayerAndScore("GenericName5",1));
    }

    public void updateLeaderboard(String s, int n)
    {
        Collections.sort(topEntries);
        if(n>topEntries.get(4).getpScore())
        {
            topEntries.add(new PlayerAndScore(s,n));
            Collections.sort(topEntries);
            topEntries.remove(5);
        }
    }

    public ArrayList<String> getLeaderboardData()
    {
        ArrayList<String> retData = new ArrayList<String>();
        for(int i=0;i<topEntries.size();i++)
        {
            retData.add(topEntries.get(i).getPlayerAndScore());
        }
        return retData;
    }

    public void disp()
    {
        for(PlayerAndScore val: topEntries)
        {
            System.out.println(val);
        }
    }
}
