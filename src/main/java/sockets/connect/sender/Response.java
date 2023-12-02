package sockets.connect.sender;

import sockets.connect.objects.Player;

import java.util.ArrayList;

public class Response {
    public ArrayList<Player> players;
    public int[][] gameBoard;
    public Player currentPlayer;
    public String winner;
}
