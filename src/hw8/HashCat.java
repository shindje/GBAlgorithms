package hw8;

import java.util.ArrayList;
import java.util.List;

public class HashCat {
    private List<Integer>[] hashArray;
    private int arrSize;

    public HashCat(int capacity) {
        this.hashArray = new List[capacity];
        this.arrSize = capacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrSize; i++) {
            sb.append((hashArray[i] != null) ? hashArray[i] : "*");
            if (i < arrSize - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    private int hashFunc(int key) {
        return key % arrSize;
    }

    public Integer find(int key) {
        int hashVal = hashFunc(key);
        List<Integer> arr = hashArray[hashVal];
        if (arr == null)
            return null;
        else
            for (Integer i: arr) {
                if (i==key)
                    return i;
            }
        return null;
    }

    public void insert(int key) {
        int hashVal = hashFunc(key);
        if (isFull()) increaseCapacity();
        List<Integer> arr = hashArray[hashVal];
        if (arr == null) {
            arr = new ArrayList<>();
            arr.add(key);
            hashArray[hashVal] = arr;
        } else {
            boolean find = false;
            for (Integer i: arr) {
                if (i==key)
                    find = true;
            }
            if (!find)
                arr.add(key);
        }
    }

    public Integer delete(int key) {
        int hashVal = hashFunc(key);
        List<Integer> arr = hashArray[hashVal];
        if (arr == null)
            return null;
        else
            for (Integer i: arr) {
                if (i == key) {
                    arr.remove(i);
                    if (arr.size() == 0)
                        hashArray[hashVal] = null;
                    return i;
                }
            }
        return null;
    }

    private boolean isFull() {
        for (int i = 0; i < hashArray.length; i++) {
            if (hashArray[i] == null)
                return false;
        }
        return true;
    }

    private void increaseCapacity() {
        arrSize *= 2;
        List[] oldArr = hashArray;
        hashArray = new List[arrSize];
        for (int i = 0; i < oldArr.length; i++) {
            List<Integer> arr = oldArr[i];
            if (arr != null)
                for (Integer key: arr)
                    insert(key);
        }
    }

}