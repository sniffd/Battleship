package battleship;

import battleship.exception.WrongLengthException;
import battleship.exception.WrongLocationException;

import java.util.*;

public class Main {

    private static final Map<Character, Integer> coordMap = getMap();
    private static final Scanner sc = new Scanner(System.in);
    private static final Ship[] ships = Ship.values();
    private static final Player first = new Player();
    private static final Player second = new Player();

    public static void main(String[] args) {
        Player player = first;
        Player enemy = second;
        Player tmp;

        addShips();
        while (first.getAliveShips() > 0 && second.getAliveShips() > 0) {
            try {
                takeShot(player, enemy);
                tmp = player;
                player = enemy;
                enemy = tmp;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void takeShot(Player player, Player enemy) {
        char[][] field = player.getField();
        char[][] enemyField = enemy.getField();
        char[][] playerEnemyField = player.getEnemyField();
        System.out.println("Press Enter and pass the move to another player");
        sc.nextLine();
        printField(playerEnemyField);
        System.out.println("---------------------");
        printField(field);
        System.out.println((player == first ? "Player 1" : "Player 2") + ", it's your turn:");
        String coord = sc.nextLine();
        Integer y = coordMap.get(coord.charAt(0));
        int x = Integer.parseInt(coord.substring(1)) - 1;

        if (y == null || x > 9 || x < 0) {
            throw new WrongLocationException("Error! You entered the wrong coordinates! Try again:");
        } else {
            if (enemyField[y][x] == 'O') {
                playerEnemyField[y][x] = 'X';
                enemyField[y][x] = 'X';
                if (hitShip(x, y, enemy)) {
                    if (enemy.getAliveShips() == 0) {
                        System.out.println("You sank the last ship. You won. Congratulations!");
                    } else {
                        System.out.println("You sank a ship! Specify a new target:");
                    }
                } else {
                    System.out.println("You hit a ship!");
                }
            } else if (enemyField[y][x] == '~') {
                playerEnemyField[y][x] = 'M';
                enemyField[y][x] = 'M';
                System.out.println("You missed!");
            } else {
                System.out.println("You missed!");
            }
        }
    }

    private static boolean hitShip(int x, int y, Player enemy) {
        for (Ship ship : ships) {
            if (ship.isItsCell(x, y, enemy == first ? "first" : "second")) {
                ship.getDamage();
                if (ship.getDurability() == 0) {
                    enemy.loseShip();
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    private static void printField(char[][] field) {
        char letter = 'A';
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (char[] chars : field) {
            System.out.print(letter);
            for (char aChar : chars) {
                System.out.print(" " + aChar);
            }
            System.out.println();
            letter++;
        }
    }

    private static void addShips() {
        char[][] field = first.getField();
        printField(field);
        for (Ship ship : ships) {
            if (ship == Ship.AIRCRAFT_CARRIER_SECOND_PLAYER) {
                System.out.println("Press Enter and pass the move to another player");
                sc.nextLine();
                System.out.println("Player 2, place your ships to the game field");
                field = second.getField();
                printField(field);
            }
            System.out.println("Enter the coordinates of the " + ship.getName() + " (" + ship.getSize() + " cells):");
            while (true) {
                try {
                    addShip(ship, field);
                    break;
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            }
            printField(field);
        }
    }

    private static void addShip(Ship ship, char[][] field) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();

        int y1 = coordMap.get(a.charAt(0));
        int y2 = coordMap.get(b.charAt(0));
        int x1 = Integer.parseInt(a.substring(1)) - 1;
        int x2 = Integer.parseInt(b.substring(1)) - 1;
        boolean isVertical = x1 == x2;
        boolean isHorizontal = y1 == y2;
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);
        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int yy1 = minY - 1;
        int yy2 = maxY + 1;
        int xx1 = minX - 1;
        int xx2 = maxX + 1;

        if (minX == 0) {
            xx1++;
        }
        if (minY == 0) {
            yy1++;
        }
        if (maxX == 9) {
            xx2--;
        }
        if (maxY == 9) {
            yy2--;
        }
        if (isVertical) {
            checkLength(y1, y2, ship);
        } else if (isHorizontal) {
            checkLength(x1, x2, ship);
        } else {
            throw new WrongLocationException("Error! Wrong ship location! Try again:");
        }
        checkSpace(xx1, yy1, xx2, yy2, field);
        addShip(minX, minY, maxX, maxY, ship, field);
    }

    private static void addShip(int x1, int y1, int x2, int y2, Ship ship, char[][] field) {
        for (int i = y1; i <= y2; i++) {
            for (int j = x1; j <= x2; j++) {
                field[i][j] = 'O';
            }
        }
        ship.addCells(x1, y1, x2, y2);
    }

    private static Map<Character, Integer> getMap() {
        Map<Character, Integer> map = new HashMap<>();
        char c = 'A';
        for (int i = 0; i < 10; i++, c++) {
            map.put(c, i);
        }
        return map;
    }

    private static void checkLength(int a, int b, Ship ship) {
        if (Math.abs(a - b) + 1 != ship.getSize()) {
            throw new WrongLengthException("Error! Wrong length of the " + ship.getName() + "! Try again:");
        }
    }

    private static void checkSpace(int x1, int y1, int x2, int y2, char[][] field) {
        for (int i = y1; i <= y2; i++) {
            for (int j = x1; j <= x2; j++) {
                if (field[i][j] != '~') {
                    throw new WrongLocationException("Error! You placed it too close to another one. Try again:");
                }
            }
        }
    }
}