package connect;

public class Player {
    private String playerName;
    private int moves = 0;
    private int counts = 0;

    private boolean isReady = false;
    private boolean isFirstMove = true;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public void reset() {
        moves = 0;
        counts = 0;
        isReady = false;
    }

    public void addCounts(int a) {
        this.counts += a;
    }

    public void sudCounts(int a) {
        this.counts -= a;
    }

    public void addMoves(int a) {
        this.moves += a;
    }

    public void resetMoves() {
        this.moves = 0;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public boolean isReady() {
        return isReady;
    }

    public int getMoves() {
        return moves;
    }

    public String getPlayerName() { return playerName; }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public void setFirstMove(boolean firstMove) {
        isFirstMove = firstMove;
    }
}
