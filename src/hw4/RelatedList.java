package hw4;

import java.util.Objects;

public class RelatedList {
    class Node {
        Cat c;
        Node next;
        Node prev;

        public Node(Cat c) {
            this.c = c;
        }

        @Override
        public String toString() {
            return c.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return c.equals(node.c);
        }

        @Override
        public int hashCode() {
            return Objects.hash(c);
        }
    }

    Node head;
    int size;

    public RelatedList() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void push(Cat c) {
        Node n = new Node(c);
        n.next = head;
        head = n;
        size++;
    }

    public Cat pop() {
        if (isEmpty()) return null;
        Cat temp = head.c;
        head = head.next;
        size--;
        return temp;
    }

    @Override
    public String toString() {
        Node current = head;
        StringBuilder sb = new StringBuilder("[");
        while (current != null) {
            sb.append(current);
            current = current.next;
            sb.append((current == null) ? "" : ", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean contains(Cat c) {
        return find(c) == null;
    }

    Node find(Cat c) {
        if (isEmpty()) return null;
        Node current = head;
        while (!current.c.equals(c)) {
            if (current.next == null)
                return null;
            else
                current = current.next;
        }
        return current;
    }

    public boolean delete(Cat c) {
        if (isEmpty()) return false;
        Node current = head;
        Node previous = head;
        while (!current.c.equals(c)) {
            if (current.next == null) {
                return false;
            } else {
                previous = current;
                current = current.next;
            }
        }
        size--;
        if (current == head) {
            head = head.next;
        } else {
            previous.next = current.next;
        }
        return true;
    }

    class RelatedListIterator implements ListIterator {
        Node current;
        Node previous;

        RelatedListIterator() {
            reset();
        }

        @Override
        public void reset() {
            current = head;
            previous = null;
        }

        @Override
        public Cat next() {
            if (current == null || current.next == null)
                return null;
            previous = current;
            current = current.next;
            return current.c;
        }

        @Override
        public Cat getCurrent() {
            if (current == null)
                return null;
            else
                return current.c;
        }

        @Override
        public boolean hasNext() {
            if (current == null)
                return false;
            else
                return current.next != null;
        }

        @Override
        public boolean atEnd() {
            return !hasNext();
        }

        @Override
        public boolean insertAfter(Cat c) {
            if (current == null)
                return false;
            Node n = new Node(c);
            n.next = current.next;
            current.next = n;
            size++;
            return true;
        }

        @Override
        public boolean insertBefore(Cat c) {
            if (current == null)
                return false;
            Node n = new Node(c);
            n.next = current;
            if (current == head)
                head = n;
            else
                previous.next = n;
            previous = n;
            return true;
        }

        @Override
        public Cat deleteCurrent() {
            if (current == null)
                return null;
            size--;
            Cat temp = current.c;
            if (current == head)
                head = head.next;
            else
                previous.next = current.next;
            current = current.next;
            return temp;
        }
    }

    public ListIterator getListIterator() {
        return new RelatedListIterator();
    }
}
