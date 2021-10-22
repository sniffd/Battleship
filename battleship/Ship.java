package battleship;

public enum Ship {
    AIRCRAFT_CARRIER_FIRST_PLAYER("Aircraft Carrier", 5, "first"),
    BATTLESHIP_FIRST_PLAYER("Battleship", 4, "first"),
    SUBMARINE_FIRST_PLAYER("Submarine", 3, "first"),
    CRUISER_FIRST_PLAYER("Cruiser", 3, "first"),
    DESTROYER_FIRST_PLAYER("Destroyer", 2, "first"),
    AIRCRAFT_CARRIER_SECOND_PLAYER("Aircraft Carrier", 5, "second"),
    BATTLESHIP_SECOND_PLAYER("Battleship", 4, "second"),
    SUBMARINE_SECOND_PLAYER("Submarine", 3, "second"),
    CRUISER_SECOND_PLAYER("Cruiser", 3, "second"),
    DESTROYER_SECOND_PLAYER("Destroyer", 2, "second");

    private final String name;
    private final int size;
    private int durability;
    private final Cell[] cells;
    private final String owner;

    Ship(String name, int size, String owner) {
        this.name = name;
        this.size = size;
        this.durability = size;
        this.owner = owner;
        cells = new Cell[size];
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void getDamage() {
        durability--;
    }

    public int getDurability() {
        return durability;
    }

    public void addCells(int x1, int y1, int x2, int y2) {
        int index = 0;
        for (int i = y1; i <= y2; i++) {
            for (int j = x1; j <= x2; j++) {
                cells[index] = new Cell(j, i);
                index++;
            }
        }
    }

    public boolean isItsCell(int x, int y, String owner) {
        for (Cell cell : cells) {
            if (cell.isThisCell(x, y) && owner.equals(this.owner)) {
                return true;
            }
        }
        return false;
    }
}
