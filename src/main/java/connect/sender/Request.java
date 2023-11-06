package connect.sender;

public class Request {
    PlayerActions playerActions;
    ServReactions servReactions;
    String playerName;
    int x, y;
    public Request(PlayerActions playerActions) {
        this.playerActions = playerActions;
    }
    public Request(PlayerActions playerActions, int x, int y) {
        this.playerActions = playerActions;
        this.x = x;
        this.y = y;
    }
    public Request(ServReactions servReactions) {
        this.servReactions = servReactions;
    }
    public Request(String name) {
        this.playerName = name;
    }
    public PlayerActions getClientActions() {
        return playerActions;
    }
    public ServReactions getServReactions() { return servReactions; }
    public int getX() { return x; }
    public int getY() { return y; }
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
}
