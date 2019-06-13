package v5;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.joining;

public class Sieve {

    private int limit;

    public Sieve(int limit) {
        this.limit = limit;
    }

    private Predicate<Integer> isPrimeTo(int limit) {
        var sieve = new HashSet<Integer>();
        this.limit = limit;
        var n = 2;
        while (n <= (limit / 2)) {
            var nonPrime = (n * 2);
            while (nonPrime <= limit) {
                sieve.add(nonPrime);
                nonPrime += n;
            }
            n++;
        }
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