package v2;

import java.util.function.Function;

public class Valid<T> implements MaybeValid<T> {

    private final T value;

    public Valid(T value) {
        this.value = value;
    }

    @Override
    public <U> MaybeValid<U> map(Function<T, U> m) {
        return new Valid<>(m.apply(value));
    }

    @Override
    public <U> MaybeValid<U> flatMap(Function<T, MaybeValid<U>> m) {
        return m.apply(value);
    }

    @Override
    public T orElseGet(Function<RequestError, T> unused) {
        return value;
    }
}
