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
    }
}
