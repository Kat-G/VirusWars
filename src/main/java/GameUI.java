import connect.model.Model;
import connect.model.ModelBuilder;
import connect.sender.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class GameUI{
    private final JFrame frame;
    private final JTextField xField;
    private final JTextField yField;

    private Socket socket;
    int port = 3124;
    InetAddress ip = null;
    Sender sender;
    String playerName = "Player";
    int size = 10;
    boolean show_flag = true;
    private final Model game_model = ModelBuilder.build();
    JLabel statusLabel = new JLabel();


    public void dataInit(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);

    }
    public void connectToServer() {
        try {
            ip = InetAddress.getLocalHost();
            socket = new Socket(ip, port);

            Sender sender = new Sender(socket);
            sender.sendRequest(new Request(playerName));
            Request msg = sender.getRequest();
            playerName = msg.getPlayerName();
            if (msg.getServReactions() == ServReactions.ACCEPT){
                new Thread(
                        ()->
                        {
                            try {
                                while (true) {
                                    Response resp = sender.getResp();
                                    game_model.setClients(resp.players);
                                    game_model.setGameBoard(resp.gameBoard);
                                    game_model.setWinner(resp.winner);
                                    game_model.setCurrentPlayer(resp.currentPlayer);

                                    updateGameBoard(game_model.getGameBoard());
                                }

                            } catch (IOException ex) {
                            }

                        }
                ).start();
                dataInit(socket);
            } else {
                System.out.println("Error connect to server");
            }
        } catch (IOException ignored) {   }
    }

    public GameUI() {

        frame = new JFrame("Война вирусов");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel boardPanel = new JPanel(new GridLayout(size, size));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JLabel cell = new JLabel();
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cell.setHorizontalAlignment(JLabel.CENTER);
                boardPanel.add(cell);
            }
        }

        JPanel inputPanel = new JPanel();
        xField = new JTextField(2);
        yField = new JTextField(2);
        JButton moveButton = new JButton("Сделать ход");
        JButton passButton = new JButton("Отказаться от хода");
        JButton connectButton = new JButton("Подключиться");

        inputPanel.add(new JLabel("X:"));
        inputPanel.add(xField);
        inputPanel.add(new JLabel("Y:"));
        inputPanel.add(yField);
        inputPanel.add(moveButton);
        inputPanel.add(passButton);
        inputPanel.add(connectButton);

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);
        frame.add(statusLabel, BorderLayout.NORTH);

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer();
                sender.sendRequest(new Request(PlayerActions.FIRST_MOVE,0,0));
                System.out.println("Player:" + playerName);
            }
        });

        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = Integer.parseInt(xField.getText());
                int y = Integer.parseInt(yField.getText());

                sender.sendRequest(new Request(PlayerActions.MOVE,x,y));
            }
        });

        passButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sender.sendRequest(new Request(PlayerActions.SKIP));
            }
        });

        frame.setVisible(true);
    }

    public void updateStatusLabel(String playerName, String currentPlayer, int remainingMoves) {
        statusLabel.setText("Игрок: " + playerName + " | Ходит: " + currentPlayer + " | Осталось ходов: " + (3-remainingMoves));
    }

    public void updateGameBoard(int[][] cells) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JLabel cell = (JLabel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(i * size + j);
                if (cells[i][j] == 0) {
                    cell.setText("(" + i + "," + j + ")");
                    cell.setForeground(Color.GRAY);
                } else if (cells[i][j] == 1) {
                    cell.setText("X");
                    cell.setForeground(Color.BLACK);
                } else if (cells[i][j] == 2) {
                    cell.setText("O");
                    cell.setForeground(Color.BLACK);
                } else if (cells[i][j] == 3) {
                    cell.setText("X");
                    cell.setForeground(Color.RED);
                } else if (cells[i][j] == 4) {
                    cell.setText("O");
                    cell.setForeground(Color.RED);
                }
            }
        }
        updateStatusLabel(playerName, game_model.getCurrentPlayer().getPlayerName(), game_model.getCurrentPlayer().getMoves());
        checkWinner();
    }

    private void checkWinner() {
        if (game_model.getWinner() != null && show_flag) {
            System.out.println("Победитель:" + game_model.getWinner());
            showWinMessage(game_model.getWinner());
            show_flag = false;
        }
    }

    public void showWinMessage(String winner) {
        JOptionPane.showMessageDialog(frame, "Winner: " + winner, "Победа", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        GameUI gameUI = new GameUI();
    }
}
