import connect.Controllers.GameBoardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameUI {
    private final GameBoardController gameBoard;
    private final JFrame frame;
    private final JTextField xField;
    private final JTextField yField;

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
                // gameBoard.makeMove(x, y, currentPlayer);
            }
        });

        passButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Отказ от хода
                // gameBoard.passMove(currentPlayer);
            }
        });

        frame.setVisible(true);
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

    public static void main(String[] args) {
        // Пример использования
        GameBoardController gameBoard = new GameBoardController(10);
        GameUI gameUI = new GameUI(gameBoard);

        // Предполагается, что у вас есть метод updateBoard(int[][] cells), который обновляет интерфейс с новым состоянием поля.
        int[][] initialCells = new int[10][10];
        //gameUI.updateBoard(initialCells);
    }
}
