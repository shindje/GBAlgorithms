package hw5;

public class Hanoi {
    static int cnt;

    //Выбор стержня
    //a, b - откуда, куда перемещается нижнее (большее) кольцо
    private static int getC(int a, int b) {
        for (int i = 1; i < 4; i++) {
            if (i != a && i != b)
                return i;
        }
        return 0;
    }

    //Перемещение кольца
    //n - размер кольца
    //a, b - откуда, куда переместить
    private static void step(int n, int a, int b) {
        System.out.print(a + "->" + b + " ");
        cnt++;
    }

    //Цикл перемещения колец, начиная с n-ого (большего)
    private static void move(int n, int a, int b) {
        if (n == 1)
            step(n, a, b);
        else {
            int c = getC(a, b);
            move(n - 1, a, c);
            step(n, a, b);
            move(n - 1, c, b);
        }
    }

    //n - кол-во колец
    static void start(int n) {
        cnt = 0;
        move(n, 1, 3);
        System.out.println("\nПеремещено колец: " + n);
        System.out.println("Шагов: " + cnt);
    }

    public static void main(String[] args) {
        start(8);
    }
}