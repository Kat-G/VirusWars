import connect.IObserver;
import connect.controllers.GameBoardController;
import connect.model.Model;
import connect.model.ModelBuilder;
import connect.resp.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class GameUI implements IObserver {
    private final GameBoardController gameBoard;
    private final JFrame frame;
    private final JTextField xField;
    private final JTextField yField;

    private Socket socket;
    int port = 3124;
    InetAddress ip = null;
    Sender sender;
    String playerName;

    private Model m = ModelBuilder.build();

    public void initialize() {
        m.addObserver(this);
    }

    public void dataInit(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);

    }
    public void connectToServer() {
        try {
            ip = InetAddress.getLocalHost();
            socket = new Socket(ip, port);

            playerName = "Player";
            Sender sender = new Sender(socket);
            sender.sendRequest(new Request(playerName));
            Request msg = sender.getRequest();

            if (msg.getServReactions() == ServReactions.ACCEPT){
                new Thread(
                        ()->
                        {
                            try {
                                while (true) {
                                    Response ra = sender.getResp();
                                    m.setClients(ra.clients);
                                    m.setGameBoard(ra.gameBoard);
                                    m.setWinner(ra.winner);
                                    m.update();

                                    updateBoard(m.getGameBoard());
                                }

                            } catch (IOException ex) {

                            }

                        }
                ).start();
                dataInit(socket);
            } else {
               //alertError(msg.getServReactions());
            }
        } catch (IOException ignored) {   }
    }

    public GameUI(GameBoardController gameBoard) {
        this.gameBoard = gameBoard;

        frame = new JFrame("Война вирусов");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel boardPanel = new JPanel(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
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

        inputPanel.add(new JLabel("X:"));
        inputPanel.add(xField);
        inputPanel.add(new JLabel("Y:"));
        inputPanel.add(yField);
        inputPanel.add(moveButton);
        inputPanel.add(passButton);

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);



        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = Integer.parseInt(xField.getText());
                int y = Integer.parseInt(yField.getText());

                // Отправить ход на сервер
                sender.sendRequest(new Request(ClientActions.MOVE,x,y));
            }
        });

        passButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Отказ от хода
                sender.sendRequest(new Request(ClientActions.SKIP));
            }
        });

        frame.setVisible(true);

        connectToServer();
    }

    // Метод для обновления игрового поля в интерфейсе
    public void updateBoard(int[][] cells) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JLabel cell = (JLabel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(i * 10 + j);
                if (cells[i][j] == 0) {
                    cell.setText(" ");
                } else if (cells[i][j] == 1) {
                    cell.setText("X");
                } else if (cells[i][j] == 2) {
                    cell.setText("O");
                } else if (cells[i][j] == 3) {
                    cell.setText("X");
                    cell.setForeground(Color.RED);
                } else if (cells[i][j] == 4) {
                    cell.setText("O");
                    cell.setForeground(Color.RED);
                }
            }
        }
    }

    @Override
    public void update() {
        System.out.println("Hi from Client!");
        updateBoard(m.getGameBoard());
    }

    public static void main(String[] args) {
        // Пример использования
        GameBoardController gameBoard = new GameBoardController(10);
        GameUI gameUI = new GameUI(gameBoard);

        // Предполагается, что у вас есть метод updateBoard(int[][] cells), который обновляет интерфейс с новым состоянием поля.
        //int[][] initialCells = new int[10][10];
        //gameUI.updateBoard(initialCells);
    }


}
