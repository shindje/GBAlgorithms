package hw2;

import java.util.Arrays;

public class Array {
    private int arr[];
    private int size;
    private boolean isSorted;

    private Array() {
        this.isSorted = false;
    }

    public Array(int capacity) {
        this();
        arr = new int[capacity];
        this.size = 0;
    }

    public Array(int... args) {
        this();
        this.size = args.length;
        this.arr = args;
    }

    public int get(int index) {
        if (index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException(index);
        return arr[index];
    }

    public void set (int index, int value) {
        if (index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException(index);
        arr[index] = value;
    }

    public int length() {
        return size;
    }

    private void increaseCapacity() {
        int[] temp = arr;
        arr = new int[size * 2];
        System.arraycopy(temp, 0, arr, 0, size);
    }

    public void append(int value) {
        if (size >= arr.length) {
            increaseCapacity();
        }
        arr[size++] = value;
        isSorted = false;
    }

    public int deleteLast() {
        if (size == 0)
            throw new ArrayIndexOutOfBoundsException(-1);

        return arr[--size];
    }

    public void insert(int index, int value) {
        if (index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException(index);
        if (size >= arr.length) {
            increaseCapacity();
        }
        for (int i = size; i >= index; i--) {
            arr[i+1] = arr [i];
        }
        size++;
        arr[index] = value;
        isSorted = false;
    }

    public boolean deleteValue(int value) {
        int index = find(value);
        if (index == -1)
            return false;
        for (int i = index; i < size-2; i++) {
            arr[i] = arr [i+1];
        }
        size--;
        return true;
    }

    public void delete(int index) {
        if (index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException(index);
        for (int i = index; i < size-2; i++) {
            arr[i] = arr [i+1];
        }
        size--;
    }

    public void deleteAll() {
        size = 0;
    }

    @Override
    public String toString() {
        if (arr == null) return "null";
        int iMax = size - 1;
        if (iMax == -1) return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        int i = 0;
        while (true) {
            b.append(arr[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
            i++;
        }
    }

    public int find(int value) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == value)
                return i;
        }
        return -1;
    }

    public boolean hasValue(int value) {
        if (!isSorted)
            throw new RuntimeException("try the 'find' method");

        int l = 0;
        int r = size;
        int m;
        while (l < r) {
            m = (l + r) >> 1; // (l + r) / 2
            if (value == arr[m])
                return true;
            else if (value < arr[m])
                r = m;
            else
                l = m + 1;
        }
        return false;
    }

    private void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    //Оптимизация 1 - внутренний цикл проходит не весь массив, а на i элементов меньше,
    // т.к. на каждой итерации внешнего цикла текущий максимальный элемент будет передвинут на правильную позицию
    //Оптимизация 2 - если ни одного перемещений за цикл не сделали, значит массив уже отсортирован
    public int sortBubbleBetter() {
        int actions = 0;
        for (int i = 0; i < size; i++) {
            actions++;
            boolean swapped = false;
            for (int j = 0; j < size - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1);
                    swapped = true;
                }
                actions++;
            }
            if (!swapped)
                break;
        }
        isSorted = true;
        return actions;
    }

    public int sortBubble() {
        int actions = 0;
        for (int i = 0; i < size; i++) {
            actions++;
            for (int j = 0; j < size - 1; j++) {
                if (arr[j] > arr[j + 1])
                    swap(j, j + 1);
                actions++;
            }
        }
        isSorted = true;
        return actions;
    }


    public int sortSelect() {
        int actions = 0;
        for (int flag = 0; flag < size; flag++) {
            actions++;
            int cMin = flag;
            for (int rem = flag + 1; rem < size; rem++) {
                if (arr[rem] < arr[cMin])
                    cMin = rem;
                actions++;
            }
            swap(flag, cMin);
        }
        isSorted = true;
        return actions;
    }

    public int sortInsert() {
        int actions = 0;
        for (int out = 0; out < size; out++) {
            actions++;
            int temp = arr[out];
            int in = out;
            while (in > 0 && arr[in - 1] >= temp) {
                actions++;
                arr[in] = arr[in - 1];
                in--;
            }
            arr[in] = temp;
        }
        isSorted = true;
        return actions;
    }

    //Сортировка подсчётом
    //Сложность - O(2n) -> O(n)
    public int sortCount() {
        int MAX_VALUE = 1000;
        int actions = 0;
        int [] positive = new int[MAX_VALUE];
        int [] negative = new int[MAX_VALUE];
        for (int i = 0; i < size; i++) {
            actions++;
            if (arr[i] < 0)
                negative[arr[i]*-1]++;
            else
                positive[arr[i]]++;
        }
        int[] temp = new int[size];
        int idx = 0;
        for (int i = negative.length - 1; i > 0; i--) {
            for (int j = 0; j < negative[i]; j++) {
                temp[idx] = i*-1;
                idx++;
                actions++;
            }
        }
        for (int i = 0; i< positive.length; i++) {
            for (int j = 0; j < positive[i]; j++) {
                temp[idx] = i;
                idx++;
                actions++;
            }
        }
        arr = temp;
        return actions;
    }

    public Array copyOf() {
        Array newArray = new Array();
        newArray.arr = Arrays.copyOf(arr, arr.length);
        newArray.size = size;
        newArray.isSorted = isSorted;
        return newArray;
    }
}
