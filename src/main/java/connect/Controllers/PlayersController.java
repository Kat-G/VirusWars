package connect.Controllers;

import connect.Player;
import java.util.ArrayList;

public class PlayersController {
    private ArrayList<Player> players = new ArrayList<>();
    private int who_moves = 1;

    public PlayersController() { }

    public void addPlayer(Player clientData){
        players.add(clientData);
    }
}
