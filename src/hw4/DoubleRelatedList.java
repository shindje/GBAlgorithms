package hw4;

public class DoubleRelatedList extends RelatedList {
    Node tail;

    DoubleRelatedList() {
        super();
        tail = null;
    }

    public void push(Cat c) {
        super.push(c);
        if (tail == null)
            tail = head;
        if (size > 1)
            head.next.prev = head;
    }

    public void pushTail(Cat c) {
        Node n = new Node(c);
        n.prev = tail;
        if (tail != null)
            tail.next = n;
        tail = n;
        if (head == null)
            head = tail;
        size++;
    }

    public Cat pop() {
        Cat temp = super.pop();
        if (head != null)
            head.prev = null;
        else
            tail = null;
        return temp;
    }

    public Cat popTail() {
        if (isEmpty()) return null;
        Cat temp = tail.c;
        tail = tail.prev;
        if (tail != null)
            tail.next = null;
        else
            head = null;
        size--;
        return temp;
    }


    public boolean delete(Cat c) {
        Node temp = find(c);
        if (temp != null) {
            if (temp == head)
                head = head.next;
            else
                temp.prev.next = temp.next;
            if (temp == tail)
                tail = tail.prev;
            else
                temp.next.prev = temp.prev;
            size--;
            return true;
        } else
            return false;
    }
}
