package v1;

import java.util.Optional;

public interface Stack<T> {

    int depth();

    Stack<T> pop();

    Optional<T> top();

    Stack<T> push(T value);

    static <T> Stack<T> create(Class<T> of) {
        return new EmptyStack<>();
    }
}
