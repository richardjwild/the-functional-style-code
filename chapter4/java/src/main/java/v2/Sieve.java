package v2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;

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

    public List<Integer> primes() {
        return IntStream
                .range(1, limit)
                .boxed()
                .filter(not(sieve::contains))
                .collect(toList());
    }

    public static void main(String[] args) {
        var sieve = new Sieve(10000);
        for (var prime : sieve.primes()) {
            System.out.print(prime);
            System.out.print(", ");
        }
        System.out.println();
    }
}