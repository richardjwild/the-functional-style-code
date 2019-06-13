package v3;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.function.Predicate.not;
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

    public Set<Integer> primes() {
        return IntStream
                .range(1, limit)
                .boxed()
                .filter(not(sieve::contains))
                .collect(toSet());
    }

    public static void main(String[] args) {
        var sieve = new Sieve(10000);
        sieve.primes().stream()
                .sorted()
                .forEach(prime -> {
                    System.out.print(prime);
                    System.out.print(", ");
                });
        System.out.println();
    }
}