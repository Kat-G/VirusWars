package connect.resp;

public class Request {
    ClientActions clientActions;
    ServReactions servReactions;
    String playerName;
    int x, y;
    public Request(ClientActions clientActions) {
        this.clientActions = clientActions;
    }
    public Request(ClientActions clientActions, int x, int y) {
        this.clientActions = clientActions;
        this.x = x;
        this.y = y;
    }
    public Request(ServReactions servReactions) {
        this.servReactions = servReactions;
    }
    public Request(String name) {
        this.playerName = name;
    }
    public ClientActions getClientActions() {
        return clientActions;
    }
    public ServReactions getServReactions() { return servReactions; }
    public int getX() { return x; }
    public int getY() { return y; }
    public String getPlayerName() { return playerName; }
}
