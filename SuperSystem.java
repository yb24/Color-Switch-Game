import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class SuperSystem implements Serializable
{
    private static final long serialVersionUID = 42L;
    private int a;
    private String b;
    private Player currentPlayer;
    private final ArrayList<PlayerInfo> allPlayers;
    private final Leaderboard leaderboard;
    private Game currentGame;

    public SuperSystem()
    {
        this.a = 0;
        this.b = "default";
        this.currentPlayer = new Player();
        this.allPlayers = new ArrayList<>();
        this.leaderboard = new Leaderboard();
    }

    public void printDetails()
    {
        System.out.println("int a :" + a + " String b : " + b + " current player " + currentPlayer);
        System.out.println("All players:");
        for(PlayerInfo p: allPlayers)
        {
            p.printPlayerInfo();
        }
        System.out.println("Leaderboard:");
        displayLeaderboard();
        System.out.println("Current Game:");
        if(currentGame!=null)
        {
            yy();
        }
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(String b) {
        this.b = b;
    }

    public void setCurrentPlayer(Player p) {
        this.currentPlayer = p;
    }

    public Player getPlayerByIndex(int i)
    {
        if(i>=0 && i<allPlayers.size())
            return allPlayers.get(i).getPlayer();
        return null;
    }

    public void addPlayer(String name,boolean premiumStatus)
    {
        int id=0;
        for(PlayerInfo p: allPlayers)
        {
            id = Math.max(p.getPlayer().getID(),id);
        }
        id++;

        Player temp_player = new Player(name,id,premiumStatus);
        allPlayers.add(new PlayerInfo(temp_player));
        currentPlayer = temp_player;
    }

    public void removePlayer(int index)
    {
        allPlayers.remove(index);
    }

    public void removeSavedGame(int indexOfSavedGame)
    {
        for(PlayerInfo p: allPlayers)
        {
            if(p.getPlayer()==currentPlayer)
            {
                p.removeGame(indexOfSavedGame);
            }
        }
    }

    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    public void selectCurrentPlayer(int index)
    {
        currentPlayer = allPlayers.get(index).getPlayer();
    }

    public void newGame() throws Exception {
        int idOfGame = 0;
        for(PlayerInfo p: allPlayers)
        {
            if(p.getPlayer()==currentPlayer)
            {
                idOfGame = p.getUniqueID();
            }
        }

        currentGame = new Game(currentPlayer,idOfGame);
        currentGame.play();
    }

    public void saveGame() throws IOException {
        for(PlayerInfo p: allPlayers)
        {
            if(p.getPlayer()==currentPlayer)
            {
                p.addGame(currentGame);
                Test.serialize();
            }
        }
    }


    public void getSaveListOfCurrentPlayer(ArrayList<String> a)
    {
        for(PlayerInfo p: allPlayers)
        {
            if(p.checkEquality(currentPlayer))
            {
                p.forSaveListDisplay(a);
            }
        }
    }

    public void callBeforeExiting()
    {
        currentPlayer=null;
        currentGame=null;
    }

    public void displayLeaderboard()
    {
        leaderboard.disp();
    }

    public void getAllPlayersString(ArrayList<String> a)
    {
        for(PlayerInfo p: allPlayers)
        {
            a.add(p.callGetPlayerString());
        }
    }

    public Game getCurrentGame()
    {
        return currentGame;
    }

    public void yy()
    {
        currentGame.dispGamedetails();
    }

    public Leaderboard getLeaderboard()
    {
        return leaderboard;
    }

    public void tempFunc(String ts, int ti)
    {
        leaderboard.updateLeaderboard(ts,ti);
    }
}
