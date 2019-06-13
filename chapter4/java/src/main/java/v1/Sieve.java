package v1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        List<Integer> primes;
        primes = new ArrayList<>();
        var n = 1;
        while (n <= limit) {
            if (!sieve.contains(n))
                primes.add(n);
            n++;
        }
        return primes;
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