package connect.controllers;

import connect.Player;
import java.util.ArrayList;

public class PlayersController {
    private ArrayList<Player> players = new ArrayList<>();
    private int who_moves = 1;

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public PlayersController() { }

    public void addPlayer(Player clientData){
        players.add(clientData);
    }

    public void reset(){
        players.forEach(Player::reset);
    }
}
