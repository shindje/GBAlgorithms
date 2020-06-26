package hw1;

public class Main {
    //Простое возведение в степень
    //Сложность - О(n), т.к. кол-во действий = показатель степени - 1, константные действия не учитываем
    static long simpleExtent(long value, int extent) {
        if (extent == 0) {
            return 1;
        } else {
            long res = value;
            for (int i = 2; i <= extent; i++) {
                res *= value;
            }
            return res;
        }
    }

    //Возведение в степень с использованием четности степени (если я правильно понял)
    //Сложность - О(log n), т.к. на каждой итерации сокращаем кол-во действий вдвое, константные множители не учитываем
    static long evenExtent(long value, int extent) {
        if (extent == 0) {
            return 1;
        } else {
            long res = value;
            if (extent > 3) {
                res = evenExtent(value, extent/2);
                res *= res;
            } else if (extent > 1) {
                res *= res;
            }
            if (extent % 2 == 1 && extent > 1) {
                res *= value;
            }

            return res;
        }
    }

    //Поиск минималького элемента
    //Сложность - O(n), т.к. для каждого элемента выполняем 1 действие, константные действия не учитываем
    static long min(long [] arr) {
        long min = Long.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    //Поиск среднего арифметического
    //Сложность - O(n), т.к. для каждого элемента выполняем 1 действие, константные действия не учитываем
    static double avg(long[] arr) {
        double summ = 0;
        for (int i = 0; i < arr.length; i++) {
            summ += arr[i];
        }
        return summ/arr.length;
    }

    public static void main(String[] args) {
        System.out.println(simpleExtent(3, 5));
        System.out.println(evenExtent(3,5));
        System.out.println(min(new long[]{56, 1, 5, 2, 7}));
        System.out.println(avg(new long[]{56, 1, 5, 2, 7}));
    }
}
