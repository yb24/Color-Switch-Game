import java.io.Serializable;

public class Player implements Serializable
{
    private static final long serialVersionUID = 420L;
    private final String name;
    private final int ID;
    private boolean premiumStatus;

    public Player()
    {
        name = "defaultPlayer";
        ID = 0;
        premiumStatus = false;
    }

    public Player(String name, int ID, boolean b)
    {
        this.name = name;
        this.ID = ID;
        this.premiumStatus = b;
    }

    public void setPremiumStatus(boolean b)
    {
        premiumStatus = b;
    }

    public boolean getPremiumStatus()
    {
        return premiumStatus;
    }

    public int getID()
    {
        return ID;
    }

    public String getPlayerString()
    {
        return "ID: " + ID + "    " + name;
    }

    @Override
    public String toString() {
        return "name: " + name + " ID: " + ID + " status: " + premiumStatus;
    }

    public void printPlayerDetails()
    {
        System.out.println("name: " + name + " ID: " + ID);
    }

}
