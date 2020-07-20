package hw8;

public class Main {
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static void qSort(int[] arr, int min, int max) {
        int left = min;
        int right = max;
        int x = arr[(left + right) / 2];
        do {
            while (arr[left] < x) left++;
            while (arr[right] > x) right--;
            if (left <= right) {
                if (arr[left] > arr[right]) {
                    swap(arr, left, right);
                }
                left++;
                right--;
            }
        } while (left <= right);

        if (left < max) {
            qSort(arr, left, max);
        }
        if (right > min) {
            qSort(arr, min, right);
        }
    }

    public static void main(String[] args) {
        HashCat hashTable = new HashCat(25);
        hashTable.insert(10);
        hashTable.insert(20);
        hashTable.insert(30);
        hashTable.insert(75);
        hashTable.insert(40);
        hashTable.insert(50);
        hashTable.insert(60);
        hashTable.insert(70);
        System.out.println(hashTable.toString());
        hashTable.delete(75);
        System.out.println(hashTable.toString());
    }
}