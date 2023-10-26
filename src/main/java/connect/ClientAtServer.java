package connect;

import connect.model.Model;
import connect.model.ModelBuilder;
import connect.resp.Request;
import connect.resp.Response;
import connect.resp.Sender;

import java.io.IOException;
import java.net.Socket;

public class ClientAtServer implements Runnable {
    Socket socket;
    Server server;
    Sender sender;
    Model model = ModelBuilder.build();
    Player player;

    public ClientAtServer(Socket socket, Server server, String playerName)  {
        this.socket = socket;
        this.server = server;
        player = new Player(playerName);
        sender = new Sender(socket);
    }
    public String getPlayerName() {
        return player.getPlayerName();
    }

    public void sendInfoToClient() {
        Response serverResp = new Response();
        serverResp.clients = model.getClients();
        serverResp.winner = model.getWinner();
        sender.sendResp(serverResp);
    }

    @Override
    public void run() {
        try {
            System.out.println("Cilent thread " + player.getPlayerName() + " started");
            model.addClient(player);
            server.bcast();

            while(true)
            {
                Request msg = sender.getRequest();

                switch (msg.getClientActions()){
                    //case STOP: {  model.pause(this.getPlayerName()); break; }

                }
            }
        } catch (IOException ignored) {

        }
    }
    public Player getPlayer() {
        return player;
    }
}
