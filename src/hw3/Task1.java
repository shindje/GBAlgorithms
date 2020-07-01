package hw3;

import java.io.IOException;
import java.io.InputStream;

public class Task1 {
    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        Stack stack = new Stack(100);
        while (true) {
            int s = in.read();
            if (s == '\n') {
                while (!stack.isEmpty())
                    System.out.print((char)stack.pop());
                System.out.println();
            } else {
                stack.push(s);
            }
        }

    }
}
