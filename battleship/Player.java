package battleship;

public class Player {

    private final char[][] field = getEmptyField();
    private final char[][] enemyField = getEmptyField();
    private int aliveShips = 5;

    private static char[][] getEmptyField() {
        char[][] emptyField = new char[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                emptyField[i][j] = '~';
            }
        }

        return emptyField;
    }

    public void loseShip() {
        aliveShips--;
    }

    public char[][] getField() {
        return field;
    }

    public char[][] getEnemyField() {
        return enemyField;
    }

    public int getAliveShips() {
        return aliveShips;
    }
}
