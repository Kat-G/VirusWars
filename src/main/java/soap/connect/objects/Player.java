package soap.connect.objects;

import com.google.gson.Gson;
import soap.connect.sender.Response;

import java.io.IOException;

public class Player {
    private String playerName;
    private int moves = 0;
    private boolean isReady = false;
    private boolean isFirstMove = true;
    private int index;

    public Player() { }

    public Player(String playerName, int index) {
        this.playerName = playerName;
        this.index = index;
    }

    public void reset() {
        moves = 0;
        isReady = false;
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

    public void setMoves(int moves) { this.moves = moves; }

    public String getPlayerName() { return playerName; }

    public void setPlayerName(String name) { playerName = name; }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public void setFirstMove(boolean firstMove) {
        isFirstMove = firstMove;
    }

    public int getIndex() { return index;  }

    public void setIndex(int index) { this.index = index; }
}
