package sockets.connect;

import sockets.connect.model.Model;
import sockets.connect.model.ModelBuilder;
import sockets.connect.sender.Request;
import sockets.connect.sender.Sender;
import sockets.connect.sender.ServReactions;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    int port = 3124;
    InetAddress ip = null;
    ExecutorService service = Executors.newFixedThreadPool(2); // ограничиваем кол-во клиентких потоков 2
    ArrayList<ClientAtServer> allClients = new ArrayList<>();

    Sender sender;
    Model model = ModelBuilder.build();


    public void bcast(){ //отправка данных на клиенты
        allClients.forEach(ClientAtServer::sendInfoToClient);
    }

    public void serverStart(){
        ServerSocket ss;
        try {
            ip = InetAddress.getLocalHost();
            ss = new ServerSocket(port, 2, ip);
            System.out.println("Server start");

            model.init(this);

            while(true)
            {
                Socket cs;
                cs = ss.accept();
                sender = new Sender(cs);
                Request msg = sender.getRequest();
                String respName = msg.getPlayerName();
                if (tryAddClient(cs)) {
                    System.out.println(respName + " connected");
                } else {
                    cs.close();
                }
            }

        } catch (IOException ignored) {}
    }

    private boolean tryAddClient(Socket sock) {
        if (allClients.size() >= 2) {
            sender.sendRequest(new Request(ServReactions.ERROR));
            return false;
        } else {
            String name;
            if (allClients.isEmpty()) {
                name = "First";
            } else {
               name = "Second";
            }
            Request request = new Request(ServReactions.ACCEPT);
            request.setPlayerName(name);
            sender.sendRequest(request);
            ClientAtServer c = new ClientAtServer(sock, this, name);
            allClients.add(c);
            service.submit(c);
            return true;
        }
    }

    public static void main(String[] args) {
        new Server().serverStart();
    }
}
