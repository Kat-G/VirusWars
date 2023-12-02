package soap.connect.model;

import soap.connect.GameServer;
import soap.connect.objects.GameBoardController;
import soap.connect.objects.Player;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private ArrayList<Player> players = new ArrayList<>();
    private final GameBoardController gameBoardController = new GameBoardController(10);

    private Player currentPlayer;
    private String winner = null;
    private GameServer s;


    public void init(GameServer s) {
        this.s = s;
    }

    public void start() {
        players.get(0).setReady(true);
        currentPlayer = players.get(0);

        for (Player player: players)
            System.out.println(player.isReady());

        if (players.size() == 2){
            //firstMove(0,0,players.get(0));
            //firstMove(0,0,players.get(1));
            new Thread(this::gameLoop).start();
        }
    }

    private void gameLoop() {
        while (true) {

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
        int currentPlayerIndex = players.indexOf(player);
        int nextPlayerIndex = (currentPlayerIndex + 1) % players.size();
        Player nextPlayer = players.get(nextPlayerIndex);
        nextPlayer.setReady(true);
        setCurrentPlayer(nextPlayer);
    }

    /*
    private int getIndex(Player player){

        if (player.getPlayerName() == "First"){
            return 1;
        }
        else {
            return 2;
        }
    }*/

    private void makeMove(int x, int y, Player player) {
        gameBoardController.Move(x, y, player.getIndex(), player.isFirstMove());
        System.out.println(player.getIndex() + " moves: " + player.getMoves());
        player.addMoves(1);
        System.out.println("after addMoves - " + player.getIndex() + " moves: " + player.getMoves());
        checkTurn(player);

        //gameBoardController.displayBoard();

        if (checkVictory(player)) {
            System.out.println("Winner: " + winner);
            setWinner(player.getPlayerName());
        }
    }

    public void firstMove(int x, int y, Player player) {
        gameBoardController.Move(x, y, player.getIndex(), player.isFirstMove());
        if (player.isFirstMove()){
            player.setFirstMove(false);
        }
        checkTurn(player);

        //gameBoardController.displayBoard();
    }

    public void skipMove(Player player) {
        System.out.println(player.getPlayerName() + " in game_board.skipMove!");
        if (player.isReady() && player.getMoves() == 0) {
            setTurnNextPlayer(player);
            System.out.println("i'm alive");
        }
    }


    public void setMove(int x, int y, Player player){
        System.out.println(player.getPlayerName() + " " + player.getIndex() + " in game_board.setMove!");
        if (player.isReady()) {
            makeMove(x,y,player);
            //System.out.println("i'm alive");
        }
        //gameBoardController.displayBoard();
    }

    private boolean checkVictory(Player player) {
        int enemy = (player.getIndex() == 1 ? 2 : 1);
        if (noFindEnemy(enemy)){
            winner = player.getPlayerName();
            return true;
        }
        return false;
    }
    private boolean noFindEnemy(int enemy){
        int[][] cells = gameBoardController.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j] == enemy) {
                    return false;
                }
            }
        }
        return true;
    }
    public String getWinner() {
        return winner;
    }
    public void setWinner(String winner) {
        this.winner = winner;
    }
    public void addClient(Player player) {
        players.add(player);
        start();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public ArrayList<Player> getClients() {
        return players;
    }

    public void setClients(ArrayList clientArrayList) {
        this.players = clientArrayList;
    }

    public int[][] getGameBoard() { return gameBoardController.getCells(); }

    public GameBoardController getGameBoardController() { return gameBoardController; }

    public void setGameBoard(int[][] gameBoard) { this.gameBoardController.setCells(gameBoard); }
}
