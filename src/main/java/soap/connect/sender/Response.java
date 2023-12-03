package soap.connect.sender;

import soap.connect.objects.Player;

import java.util.ArrayList;

public class Response {
    private ArrayList<Player> players;
    private int[][] gameBoard;
    private Player currentPlayer;
    private String winner;

    public Response() { }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void print(){
        System.out.println(players + "\n"+ gameBoard + "\n"+ currentPlayer + "\n"+ winner);
    }
}
