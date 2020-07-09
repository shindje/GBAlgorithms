package hw5;

public class Hanoi {
    static int cnt;

    //����� �������
    //a, b - ������, ���� ������������ ������ (�������) ������
    private static int getC(int a, int b) {
        for (int i = 1; i < 4; i++) {
            if (i != a && i != b)
                return i;
        }
        return 0;
    }

    //����������� ������
    //n - ������ ������
    //a, b - ������, ���� �����������
    private static void step(int n, int a, int b) {
        System.out.print(a + "->" + b + " ");
        cnt++;
    }

    //���� ����������� �����, ������� � n-��� (��������)
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

    //n - ���-�� �����
    static void start(int n) {
        cnt = 0;
        move(n, 1, 3);
        System.out.println("\n���������� �����: " + n);
        System.out.println("�����: " + cnt);
    }

    public static void main(String[] args) {
        start(8);
    }
}