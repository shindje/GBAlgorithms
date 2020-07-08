package hw4;

public class Main {
    public static void main(String[] args) {
        DoubleRelatedList rl = new DoubleRelatedList();
        rl.push(new Cat(2, "cat1"));
        rl.push(new Cat(3, "cat2"));
        rl.push(new Cat(4, "cat3"));
        rl.push(new Cat(5, "cat4"));
        rl.push(new Cat(6, "cat5"));
        System.out.println(rl);
        for (int i = 0; i < 5; i++) {
            if (i%2 == 0)
                System.out.println(rl.pop());
            else
                System.out.println(rl.popTail());
            System.out.println(rl);
        }
        rl.pushTail(new Cat(12, "cat12"));
        rl.pushTail(new Cat(13, "cat13"));
        rl.pushTail(new Cat(14, "cat14"));
        System.out.println(rl);
        rl.delete(new Cat(13, "cat13"));
        System.out.println(rl);
        rl.delete(new Cat(12, "cat12"));
        System.out.println(rl);
        rl.delete(new Cat(14, "cat14"));
        System.out.println(rl);
        rl.push(new Cat(24, "cat23"));
        rl.push(new Cat(25, "cat24"));
        System.out.println(rl);

        ListIterator iterator = rl.getListIterator();
        System.out.println(iterator.getCurrent());
        System.out.println(iterator.atEnd());
        System.out.println(iterator.next());
        System.out.println(iterator.atEnd());
        iterator.reset();
        System.out.println(iterator.getCurrent());
        iterator.insertAfter(new Cat(31,"cat 31"));
        System.out.println(rl);
        System.out.println(iterator.next());
        iterator.insertBefore(new Cat(30,"cat 30"));
        System.out.println(rl);
        iterator.deleteCurrent();
        System.out.println(rl);
        iterator.reset();
        while (iterator.getCurrent() != null)
            iterator.deleteCurrent();
        System.out.println(rl);
    }
}
