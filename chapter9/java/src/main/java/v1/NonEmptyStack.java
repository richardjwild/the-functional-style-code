package v1;

import java.util.Optional;

public class NonEmptyStack<T> implements Stack<T> {

    private final Stack<T> parent;
    private final T value;

    public NonEmptyStack(Stack<T> parent, T value) {
        this.parent = parent;
        this.value = value;
    }

    @Override
    public int depth() {
        return parent.depth() + 1;
    }

    @Override
    public Stack<T> pop() {
        return parent;
    }

    @Override
    public Optional<T> top() {
        return Optional.of(value);
    }

    @Override
    public Stack<T> push(T value) {
        return new NonEmptyStack<>(this, value);
    }
}
