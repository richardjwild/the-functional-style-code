package v2;

import java.util.stream.IntStream;

public class Factorial {

    public static void main(String[] args) {
        IntStream.range(1, 11).forEach(n ->
                System.out.println(n + "! = " + factorial(n)));
    }

    static int factorial(int number) {
        return IntStream
                .range(1, number + 1)
                .reduce(1, (a, n) -> a * n);
    }
}
