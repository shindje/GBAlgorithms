package hw3;

import java.util.NoSuchElementException;

public class PriorityQueue {
    private class El {
        int priority;
        int value;

        El(int priority, int value) {
            this.priority = priority;
            this.value = value;
        }
    }

    private int capacity;
    private El[] queue;
    private int tail;

    public PriorityQueue(int capacity) {
        this.capacity = capacity;
        queue = new El[capacity];
        tail = 0;
    }

    public boolean isEmpty() {
        return tail == 0;
    }

    public boolean isFull() {
        return tail == capacity;
    }

    public int size() {
        return tail;
    }

    public void insert(int priority, int value) {
        if (isFull()) {
            capacity *= 2;
            El[] newQ = new El[capacity];
            System.arraycopy(queue, 0, newQ, 0, queue.length);
            queue = newQ;
        }
        queue[tail++] = new El(priority, value);
    }

    public int remove() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");
        int idx = getMin();
        int temp = queue[idx].value;
        System.arraycopy(queue, idx+1, queue, idx, tail-idx);
        tail--;
        return temp;
    }

    private int getMin() {
        int minIdx = 0;
        for (int i = 1; i < tail; i++) {
            if (queue[i].priority < queue[minIdx].priority)
                minIdx = i;
        }
        return minIdx;
    }

    public int peek() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");
        int idx = getMin();
        return queue[idx].value;
    }


    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < tail; i++) {
            s += "(p: " + queue[i].priority + " v: " + queue[i].value + ")";
        }
        return "PriorityQueue{" + s + "} tail=" + tail;
    }

    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue(5);
        for (int i = 0; i < 7; i++) {
            queue.insert((int)(Math.random()*10), i);
        }
        System.out.println(queue);
        try {
            for (int i = 0; i < 8; i++) {
                int k = queue.remove();
                System.out.println(queue + " remove: " + k);
            }
        } catch (NoSuchElementException e) {
            System.out.println(queue + " NoSuchElementException");
        }
    }
}
