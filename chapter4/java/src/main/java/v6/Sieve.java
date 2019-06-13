package v6;

import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

public class Sieve {

    private int limit;

    public Sieve(int limit) {
        this.limit = limit;
    }

    private Predicate<Integer> isPrimeTo(int limit) {
        var sieve = IntStream
                .range(2, (limit / 2))
                .boxed()
                .flatMap(n -> Stream
                        .iterate(n * 2, np -> np += n)
                        .takeWhile(np -> np <= limit))
                .collect(toSet());
        return not(sieve::contains);
    }

    public Stream<Integer> primes() {
        return IntStream
                .range(1, limit)
                .boxed()
                .filter(isPrimeTo(limit))
                .distinct();
    }

    public static void main(String[] args) {
        System.out.println(new Sieve(10000)
                .primes()
                .map(Object::toString)
                .collect(joining(" ")));
    }
}