package sockets.connect;

import sockets.connect.objects.Player;
import sockets.connect.model.Model;
import sockets.connect.model.ModelBuilder;
import sockets.connect.sender.Request;
import sockets.connect.sender.Response;
import sockets.connect.sender.Sender;

import java.io.IOException;
import java.net.Socket;

public class ClientAtServer implements Runnable {
    Socket socket;
    Server server;
    Sender sender;
    Model game_model = ModelBuilder.build();
    sockets.connect.objects.Player Player;

    public ClientAtServer(Socket socket, Server server, String playerName)  {
        this.socket = socket;
        this.server = server;
        Player = new Player(playerName);
        sender = new Sender(socket);
    }
    public String getPlayerName() {
        return Player.getPlayerName();
    }

    public void sendInfoToClient() {
        Response serverResp = new Response();
        serverResp.players = game_model.getClients();
        serverResp.gameBoard = game_model.getGameBoard();
        serverResp.currentPlayer = game_model.getCurrentPlayer();
        serverResp.winner = game_model.getWinner();

        sender.sendResp(serverResp);
    }

    @Override
    public void run() {
        try {
            System.out.println("Client thread " + Player.getPlayerName() + " started");
            game_model.addClient(Player);

            game_model.start();

            server.bcast();

            while(true)
            {
                Request msg = sender.getRequest();

                switch (msg.getClientActions()){
                    case FIRST_MOVE -> { game_model.firstMove(msg.getX(),msg.getY(),this.Player); }
                    case MOVE -> { game_model.setMove(msg.getX(),msg.getY(),this.Player); }
                    case SKIP -> { game_model.skipMove(this.Player); }
                }
            }
        } catch (IOException ignored) {
        }
    }
    public Player getPlayer() {
        return Player;
    }
}
