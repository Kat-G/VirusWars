package connect.model;

import connect.controllers.GameBoardController;
import connect.controllers.PlayersController;
import connect.IObserver;
import connect.Player;
import connect.Server;

import java.util.ArrayList;

public class Model {
    private final ArrayList<IObserver> observers = new ArrayList<>(); //массив обозревателей
    private final PlayersController players = new PlayersController();
    private final GameBoardController gameBoardController = new GameBoardController(10);
    private Player currentPlayer; // Текущий игрок, чей ход
    private int remainingMoves; // Сколько ходов осталось текущему игроку
    private boolean turnInProgress; // Флаг, указывающий, что текущий ход еще не завершен

    private int next_x;
    private int next_y;

    private String winner = null;
    private boolean Reset = false;
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
        players.getPlayers().get(0).setReady(true);
        for (Player player: players.getPlayers())
            System.out.println(player.isReady());

        new Thread(this::gameLoop).start();
    }

    private void gameLoop() {
        while (true) {
            if (Reset) {
                winner = null;
                break;
            }

            //update();
            s.bcast(); //отправка данных с сервера на клиенты

            try {
                Thread.sleep(20);
            } catch (InterruptedException ignored) {}
        }
    }

    private void checkTurn(Player player) {
        if (player.getMoves()==3) {
            player.setReady(false);
            player.resetMoves();
            setTurnNextPlayer(player);
        }
    }

    private void setTurnNextPlayer(Player player) {
        int currentPlayerIndex = players.getPlayers().indexOf(player);
        int nextPlayerIndex = (currentPlayerIndex + 1) % players.getPlayers().size();
        Player nextPlayer = players.getPlayers().get(nextPlayerIndex);
        nextPlayer.setReady(true);
    }

    private int getIndex(Player player){
        if (player.getPlayerName()=="First"){
            return 1;
        }
        else {
            return 2;
        }
    }

    private void makeMove(int x, int y, Player player) {
        System.out.println("Index from makeMove: " + getIndex(player));
        gameBoardController.Move(x, y, getIndex(player), player.isFirstMove());
        if (player.isFirstMove()){
            player.setFirstMove(false);
        }
        player.addMoves(1);
        checkTurn(player);
        //update();
        gameBoardController.displayBoard();
        /*
        if (turnInProgress && player == currentPlayer && remainingMoves > 0) {
            // Обработка хода - например, вызов метода makeMove у gameBoardController
            gameBoardController.Move(x, y, players.getPlayers().indexOf(player.getPlayerName()));
            remainingMoves--;

            if (remainingMoves == 0) {
                endTurn(player); // Если ходы закончились, завершаем ход
            }

            update();
        }
         */
    }

    public void skipMove(Player player) {
        System.out.println("I'm in 'skipMove'!");
        int currentPlayerIndex = players.getPlayers().indexOf(player);
        Player nextPlayer = players.getPlayers().get(currentPlayerIndex);
        if (nextPlayer.isReady()) {
            System.out.println("I'm in 'skipMove'. It's work!");
            setTurnNextPlayer(player);
            //update();
        }
    }

    private void endTurn(Player player) {
        player.resetMoves();
        player.setReady(false);
    }

    public void setMove(int x, int y, Player player){
        System.out.println("I'm in 'setMove'! Player: " + player.getPlayerName());
        if (player.isReady()) {
            makeMove(x,y,player);
        }
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

    public int[][] getGameBoard() { return gameBoardController.getCells(); }

    public void setGameBoard(int[][] gameBoard) { this.gameBoardController.setCells(gameBoard); }
}
