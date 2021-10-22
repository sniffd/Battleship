package battleship;

public class Cell {
    private final int x;
    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isThisCell(int x, int y) {
        return this.x == x && this.y == y;
    }
}
