package hw5;

public class Queens {
    private static int SIZE;
    private static int[][] desk;

    static void printDesk() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (desk[i][j] > 0)
                    System.out.print(desk[i][j] + "  ");
                else
                    System.out.print(".  ");
            }
            System.out.println();
        }
    }

    static boolean isCellValid(int x, int y) {
        if (x == SIZE || y == SIZE)
            return false;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (desk[i][j] > 0 && (i == x || j == y || i + y == j + x || i + j == x + y))
                    return false;
            }
        }
        return true;
    }


    static private boolean put(int n) {
        if (n == SIZE + 1)
            return true;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i, j)) {
                    desk[i][j] = n;
                    boolean res = put(n + 1);
                    if (res)
                        return true;
                    else
                        desk[i][j] = 0;
                }
            }
        }
        return false;
    };

    //n - кол-во ферзей (размер доски). Больше 13 лучше не ставить
    static void start(int n) {
        SIZE = n;
        desk = new int[SIZE][SIZE];
        if (put(1))
            printDesk();
        else
            System.out.println("Не удалось найти подходящий вариант для " + SIZE + " ферзей");
    }


    public static void main(String[] args) {
        start(6);
    }

}