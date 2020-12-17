import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class SuperSystem implements Serializable
{
    private static final long serialVersionUID = 42L;
    private Player currentPlayer;
    private final ArrayList<PlayerInfo> allPlayers;
    private final Leaderboard leaderboard;
    private Game currentGame;

    public SuperSystem()
    {
        this.currentPlayer = new Player();
        this.allPlayers = new ArrayList<>();
        this.leaderboard = new Leaderboard();
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

    public void removePlayer(int index) throws UserNotFoundException {
        if(index>=0 && index<allPlayers.size())
            allPlayers.remove(index);
        else
            throw new UserNotFoundException("UserNotFoundException caught");
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

    public void selectCurrentPlayer(int index) throws UserNotFoundException {
        currentPlayer = allPlayers.get(index).getPlayer();
        if(currentPlayer==null)
            throw new UserNotFoundException("UserNotFoundException caught");
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
        ColorSwitchApp.getMediaPlayer().stop();
        currentGame = new Game(currentPlayer,idOfGame);
        currentGame.play(true);
    }

    public void loadGame(int indexOfSavedGame) throws Exception {
        for(PlayerInfo p: allPlayers)
        {
            if(p.getPlayer()==currentPlayer)
            {
                if(indexOfSavedGame>=0 && indexOfSavedGame<p.getSizeOfSavelist())
                    currentGame = p.getGame(indexOfSavedGame);
                if(currentGame!=null) {
                    ColorSwitchApp.getMediaPlayer().stop();
                    currentGame.play(false);
                }
                else
                    throw new GameNotFoundException("GameNotFoundException caught");
            }
        }
    }

    public void resumeGame(boolean paused) throws Exception {
        if(!paused)
            currentGame.setBallToPink();
        if(currentGame!=null)
            currentGame.play(false);
        else
            throw new GameNotFoundException("GameNotFoundException caught");
    }

    public void saveGame() throws IOException {
        for(PlayerInfo p: allPlayers)
        {
            if(p.getPlayer()==currentPlayer)
            {
                if(!p.checkSaveList(currentGame.getGameID()))
                    p.addGame(currentGame);
                ColorSwitchApp.serialize();
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

    public void quitGame()
    {
        currentPlayer=null;
        currentGame=null;
    }

    public void getAllPlayersString(ArrayList<String> a)
    {
        for(PlayerInfo p: allPlayers)
        {
            a.add(p.callGetPlayerString());
        }
    }

    public void resetGame() throws Exception {
        currentGame=null;
        newGame();
    }

    public int callGetScore()
    {
        return currentGame.getScore();
    }

    public int callGetExtraLife()
    {
        return currentGame.getExtraLife();
    }

    public void callSetExtraLife(int el)
    {
        currentGame.setExtraLife(el);
    }

    public void callUpdateExtraLife(int index)
    {
        if(index>=0 && index<allPlayers.size())
            allPlayers.get(index).updateExtraLife();
    }

    public boolean isPlayerAtIndexPremium(int index)
    {
        if(index>=0 && index<allPlayers.size())
        {
            return allPlayers.get(index).callGetPremiumStatus();
        }
        return false;
    }

    public void callSetPremiumStatusOfPlayerInfo(int index, boolean b)
    {
        if(index>=0 && index<allPlayers.size())
            allPlayers.get(index).callSetPremiumStatus(b);
    }

    public void callSetScore(int s)
    {
        currentGame.setScore(s);
    }

    public Leaderboard getLeaderboard()
    {
        return leaderboard;
    }

    public void callUpdateLeaderboard()
    {
        if(currentPlayer!=null && currentGame!=null)
            leaderboard.updateLeaderboard(currentPlayer.getName(),currentGame.getScore());
    }

    public String callGetEndGameCondition() {
        return currentGame.getEndGameCondition();
    }
}
