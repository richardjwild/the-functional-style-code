package v1;

import java.util.stream.IntStream;

public class Factorial {

    public static void main(String[] args) {
        IntStream.range(1, 11).forEach(n ->
                System.out.println(n + "! = " + factorial(n)));
    }

    static int factorial(int n) {
        if (n == 1)
            return n;
        else
            return n * factorial(n - 1);
    }
}
