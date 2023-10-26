package connect.model;

import connect.controllers.PlayersController;
import connect.IObserver;
import connect.Player;
import connect.Server;

import java.util.ArrayList;

public class Model {
    private final ArrayList<IObserver> observers = new ArrayList<>(); //массив обозревателей
    private final PlayersController players = new PlayersController();
    int ready;
    int pause;
    private String winner = null;
    private boolean Reset = true;
    private Server s;

    public void update()
    {
        for (IObserver o : observers) {
            o.update();
        }
    }

    /*
    public void updateScoreTable() {
        winners.setWinners(db.getAllPlayers());
        s.bcast();
    }*/

    public void init(Server s) {

        this.s = s;
    }

    public void start(Server s) {
        new Thread(
                ()->
                {
                    while (true) {
                        if (Reset) {
                            winner = null;
                            break;
                        }
                        if (pause != 0) {
                            synchronized(this) {
                                try {
                                    wait();
                                } catch(InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        for (Player player: players.getPlayers()) {

                        }

                        s.bcast(); //отправка данных с сервера на клиенты

                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException ignored) {
                        }
                    }
                }
        ).start();

    }

    private void restart() {
        Reset = true;
        players.reset();
        this.init(s);
    }

    private void checkWinner() {

    }


    public String getWinner() {
        return winner;
    }
    public void setWinner(String winner) {
        this.winner = winner;
    }
    public void addClient(Player player) {
        players.addPlayer(player);
    }
    public  void addObserver(IObserver o)
    {
        observers.add(o);
    }

    public ArrayList<Player> getClients() {
        return players.getPlayers();
    }

    public void setClients(ArrayList<Player> clientArrayList) {
        this.players.setPlayers(clientArrayList);
    }

}
