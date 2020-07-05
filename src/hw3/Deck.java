package hw3;

import java.util.NoSuchElementException;

public class Deck {
    private int capacity;
    private int[] deck;
    private int head;
    private int tail;
    private int items;

    public Deck(int capacity) {
        this.capacity = capacity;
        deck = new int[capacity];
        head = -1;
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

    private void checkIsFull() {
        if (isFull()) {
            capacity *= 2;
            int[] newQ = new int[capacity];
            if (tail >= head) {
                System.arraycopy(deck, 0, newQ, 0, deck.length);
            } else {
                System.arraycopy(deck, 0, newQ, 0, tail + 1);
                System.arraycopy(deck, head,
                        newQ, capacity - (deck.length - head),
                        deck.length - head - 1);
            }
            deck = newQ;
        }
    }

    public void insertHead(int value) {
        checkIsFull();
        if (head == 0)
            head = deck.length;
        deck[--head] = value;
        if (isEmpty())
            tail = head;
        items++;
    }
    public void insertTail(int value) {
        checkIsFull();
        if (tail == deck.length - 1)
            tail = -1;
        deck[++tail] = value;
        if (isEmpty())
            head = tail;
        items++;
    }

    public int removeHead() {
        if (isEmpty())
            throw new NoSuchElementException("Deck is empty");
        int temp = deck[head++];
        items--;
        if (isEmpty())
            head--;
        head %= capacity;
        return temp;
    }

    public int removeTail() {
        if (isEmpty())
            throw new NoSuchElementException("Deck is empty");
        int temp = deck[tail--];
        items--;
        if (isEmpty())
            tail++;
        if (tail == -1)
            tail = deck.length - 1;
        return temp;
    }

    @Override
    public String toString() {
        String s = "";
        if (!isEmpty()) {
            for (int i = 0; i < deck.length; i++) {
                if (((i < head || i > tail) && head <= tail) ||
                        ((i < head && i > tail) && head > tail))
                    s += ". ";
                else
                    s += deck[i] + " ";
            }
        }
        return "Deck {" + s + "} head = " + head + " tail " + tail + " items " + items;
    }

    public static void main(String[] args) {
        Deck deck = new Deck(10);
        for (int i = 0; i < 9; i++) {
            deck.insertTail(i);
        }
        System.out.println(deck + ". insertTail 9");
        for (int i = 0; i < 4; i++) {
            deck.removeTail();
        }
        System.out.println(deck + ". removeTail 4");
        for (int i = 0; i < 3; i++) {
            deck.removeHead();
        }
        System.out.println(deck + ". removeHead 3");
        for (int i = 0; i < 7; i++) {
            deck.insertTail(i);
        }
        System.out.println(deck + ". insertTail 7");
        for (int i = 0; i < 4; i++) {
            deck.removeTail();
        }
        System.out.println(deck + ". removeTail 4");
        for (int i = 0; i < 4; i++) {
            deck.insertHead(i);
        }
        System.out.println(deck + ". insertHead 4");
        for (int i = 0; i < 9; i++) {
            deck.removeTail();
        }
        System.out.println(deck + ". removeTail 9");
        for (int i = 0; i < 3; i++) {
            deck.insertTail(i);
        }
        System.out.println(deck + ". insertTail 3");
        for (int i = 0; i < 2; i++) {
            deck.insertHead(i);
        }
        System.out.println(deck + ". insertHead 2");
        for (int i = 0; i < 5; i++) {
            deck.removeHead();
        }
        System.out.println(deck + ". removeHead 5");
    }
}
