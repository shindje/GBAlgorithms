package hw4;

public interface ListIterator {
    void reset();
    Cat next();
    Cat getCurrent();
    boolean hasNext();
    boolean atEnd();
    boolean insertAfter(Cat c);
    boolean insertBefore(Cat c);
    Cat deleteCurrent();
}
