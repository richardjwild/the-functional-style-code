package v4;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

public class Sieve {

    private Set<Integer> sieve;

    private int limit;

    public Sieve(int limit) {
        sieve = new HashSet<>();
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
    }

    public Stream<Integer> primes() {
        return IntStream
                .range(1, limit)
                .boxed()
                .filter(not(sieve::contains))
                .distinct();
    }

    public static void main(String[] args) {
        System.out.println(new Sieve(10000)
                .primes()
                .map(Object::toString)
                .collect(joining(" ")));
    }
}