package hw2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr; // int arr[]
        arr = new int[5];
        int[] arr2 = {1, 2, 3, 4};

        Array a = new Array(1, -2, 3, 5, -4, 7, -2, 67, -3, -76);
        System.out.println("O(n^2): " + a.length() * a.length());
        System.out.println("sortBubble. Операций: " + a.copyOf().sortBubble());
        System.out.println("sortBubbleBetter. Операций: " + a.copyOf().sortBubbleBetter());
        System.out.println("sortInsert. Операций: " + a.copyOf().sortInsert());
        System.out.println("sortSelect. Операций: " + a.copyOf().sortSelect());
        Array a1 = a.copyOf();
        System.out.println("sortCount. Операций: " + a1.sortCount());
        System.out.println(a1);
        a.insert(3, 999);
        System.out.println(a);
        a.deleteValue(-4);
        System.out.println(a);
        a.delete(2);
        System.out.println(a);
        a.deleteAll();
        System.out.println(a);
    }
}
