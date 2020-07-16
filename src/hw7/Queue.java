package hw7;

import java.util.NoSuchElementException;

public class Queue<T> {
    private int capacity;
    private Object[] queue;
    private int head;
    private int tail;
    private int items;

    public Queue(int capacity) {
        this.capacity = capacity;
        queue = new Object[capacity];
        head = 0;
        tail = -1;
        items = 0;
    }

    public boolean isEmpty() {
        return items == 0;
    }

    public boolean isFull() {
        return items == capacity;
    }

    public int size() {
        return items;
    }

    public void insert(T value) {
        if (isFull()) {
            capacity *= 2;
            Object[] newQ = new Object[capacity];
            if (tail >= head) {
                System.arraycopy(queue, 0, newQ, 0, queue.length);
            } else {
                System.arraycopy(queue, 0, newQ, 0, tail + 1);
                System.arraycopy(queue, head,
                        newQ, capacity - (queue.length - head),
                        queue.length - head - 1);
            }
            queue = newQ;
        }
        if (tail == capacity - 1)
            tail = -1;
        queue[++tail] = value;
        items++;
    }

    public T remove() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");
        T temp = (T)queue[head++];
        head %= capacity; // if (head == capacity) head = 0;
        items--;
        return temp;
    }

    public T peek() {
        return (T)queue[head];
    }
}
