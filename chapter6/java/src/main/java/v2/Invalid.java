package v2;

import java.util.function.Function;

public class Invalid<T> implements MaybeValid<T> {

    private final RequestError error;

    public Invalid(RequestError e) {
        error = e;
    }

    @Override
    public <U> MaybeValid<U> map(Function<T, U> unused) {
        return new Invalid<>(error);
    }

    @Override
    public <U> MaybeValid<U> flatMap(Function<T, MaybeValid<U>> unused) {
        return new Invalid<>(error);
    }

    @Override
    public T orElseGet(Function<RequestError, T> dvp) {
        return dvp.apply(error);
    }
}
