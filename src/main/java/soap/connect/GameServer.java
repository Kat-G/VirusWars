package soap.connect;

import com.google.gson.Gson;
import soap.connect.model.Model;
import soap.connect.model.ModelBuilder;
import soap.connect.objects.Player;
import soap.connect.sender.Response;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;

@WebService
public class GameServer {
    public static final String url = "http://localhost:8080/VirusWarsServer";
    Model game_model = ModelBuilder.build();
    ArrayList<Player> allClients = new ArrayList<>();
    Gson gson = new Gson();

    public GameServer()  {
        //game_model.init(this);
    }

    @WebMethod
    public String addClient() {
        if (allClients.size() < 2) {
            String name;
            int index;
            if (allClients.isEmpty()) {
                name = "First";
                index = 1;
            } else {
                name = "Second";
                index = 2;
            }
            Player player = new Player(name,index);
            allClients.add(player);
            game_model.addClient(player);
            firstMove(0,0,player);
            return name;
        }
        else {
            return null;
        }
    }

    public Player findPlayer(String playerName) {
        for (Player player : allClients) {
            if (player.getPlayerName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    @WebMethod
    public String sendToClient(){
        Response serverResp = new Response();
        serverResp.setPlayers(game_model.getClients());
        serverResp.setGameBoard(game_model.getGameBoard());
        serverResp.setCurrentPlayer(game_model.getCurrentPlayer());
        serverResp.setWinner(game_model.getWinner());

        return gson.toJson(serverResp);
    }


    public void firstMove(int x, int y, Player player) {
        game_model.firstMove(x, y, player);
    }
    @WebMethod
    public void setMove(int x, int y, String playerName) {
        game_model.setMove(x, y, findPlayer(playerName));
    }
    @WebMethod
    public void skipMove(String playerName) {
        game_model.skipMove(findPlayer(playerName));
    }

    public static void main(String[] args) {
        GameServer service=new GameServer();
        Endpoint.publish(url,service);
    }
}
