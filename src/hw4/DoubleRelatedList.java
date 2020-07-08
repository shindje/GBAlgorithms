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

    class DoubleRelatedListIterator implements ListIterator {
        Node current;

        DoubleRelatedListIterator() {
            reset();
        }

        @Override
        public void reset() {
            current = head;
        }

        @Override
        public Cat next() {
            if (current == null || current.next == null)
                return null;
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
            n.prev = current;
            if (current == tail)
                tail = n;
            else
                current.next.prev = n;
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
            n.prev = current.prev;
            if (current == head)
                head = n;
            else
                current.prev.next = n;
            current.prev = n;
            size++;
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
                current.prev.next = current.next;
            if (current == tail)
                tail = tail.prev;
            else
                current.next.prev = current.prev;
            current = current.next;
            return temp;
        }
    }

    public ListIterator getListIterator() {
        return new DoubleRelatedListIterator();
    }
}
