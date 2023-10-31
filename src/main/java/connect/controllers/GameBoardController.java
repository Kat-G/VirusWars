package connect.controllers;

public class GameBoardController {
    // 0 - свободная ячейка
    // 1 - захваченная 1-ым игроком ячейка
    // 2 - захваченная 2-ым игроком ячейка
    // 3 - убитая 1-ым игроком ячейка
    // 4 - убитая 2-ым игроком ячейка

    private int[][] cells; // Массив для представления игрового поля
    private int size; // Размер игрового поля (например, 10x10)

    public GameBoardController(int size) {
        this.size = size;
        cells = new int[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        // Инициализация всех ячеек значением 0
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = 0;
            }
        }
    }

    private boolean isMoveValid(int x, int y, int player) {
        int enemy = (player==1 ? 2 : 1);
        if (x < 0 || x >= size || y < 0 || y >= size) {
            return false;
        }
        if ((cells[x][y]!=0 && cells[x][y]!=enemy) || (!isNeighbors(x, y, player))) {
            return false;
        }
        return true;
    }

    private boolean isNeighbors(int x, int y, int player) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (x + i >= 0 && x + i < size && y + j >= 0 && y + j < size &&
                        (cells[x + i][y + j] == player || cells[x + i][y + j] == player+2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void Move(int x, int y, int player, boolean firstMove) {
        System.out.println("I'm in GameBoard!");
        if (firstMove) {
            if (player == 1) {
                cells[size-1][0] = player;
            }
            else {
                cells[0][size-1] = player;
            }
            System.out.println("if-player: " + player);
        }
        else {
            int enemy = (player == 1 ? 2 : 1);
            if (isMoveValid(x, y, player)) {
                if (cells[x][y] == 0) {
                    cells[x][y] = player;
                }
                if (cells[x][y] == enemy) {
                    cells[x][y] = player + 2;
                }
            }
            System.out.println("else-player: " + player);
        }
    }

    public void reset() {
        initializeBoard();
    }

    public int[][] getCells() {
        return cells;
    }

    public void setCells(int[][] cells) {
        this.cells = cells;
    }

    public void displayBoard() {
        // Вывод игрового поля на экран
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(cells[i][j] + " ");
            }
            System.out.println();
        }
    }
}
