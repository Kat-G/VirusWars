package soap;

import com.google.gson.Gson;
import soap.connect.model.Model;
import soap.connect.model.ModelBuilder;

import soap.connect.sender.Response;
import soap.vw.webservice.GameServer;
import soap.vw.webservice.GameServerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

public class GameUI {
    private final JFrame frame;
    private final JTextField xField;
    private final JTextField yField;

    static GameServer service;

    String playerName;
    int size = 10;
    boolean show_flag = true;
    private final Model game_model = ModelBuilder.build();
    JLabel statusLabel = new JLabel();
    Gson gson = new Gson();


    public void connectToServer() {
        playerName = service.addClient();
        new Thread(() ->
                {
                    while (true) {
                        Response resp = gson.fromJson(service.sendToClient(), Response.class);
                        game_model.setClients(resp.getPlayers());
                        game_model.setGameBoard(resp.getGameBoard());
                        game_model.setWinner(resp.getWinner());
                        game_model.setCurrentPlayer(resp.getCurrentPlayer());

                        updateGameBoard(game_model.getGameBoard());
                    }
                }
        ).start();
    }

    public GameUI() throws MalformedURLException {

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

        service = new GameServerService(new URL("http://localhost:8080/VirusWarsServer?wsdl")).getGameServerPort();

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connectToServer();

                    System.out.println("Player:" + playerName);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = Integer.parseInt(xField.getText());
                int y = Integer.parseInt(yField.getText());

                service.setMove(x,y,playerName);
            }
        });

        passButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                service.skipMove(playerName);
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
            show_flag = false;
            System.out.println("Winner:" + game_model.getWinner());
            showWinMessage(game_model.getWinner());
        }
    }

    public void showWinMessage(String winner) {
        JOptionPane.showMessageDialog(frame, "Winner: " + winner, "Победа", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) throws MalformedURLException {
        GameUI gameUI = new GameUI();
    }
}