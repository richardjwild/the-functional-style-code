package v1;

import java.util.Optional;

public class EmptyStack<T> implements Stack<T> {

    @Override
    public int depth() {
        return 0;
    }

    @Override
    public Stack<T> pop() {
        return this;
    }

    @Override
    public Optional<T> top() {
        return Optional.empty();
    }

    @Override
    public Stack<T> push(T value) {
        return new NonEmptyStack<>(this, value);
    }
}
