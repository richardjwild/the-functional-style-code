package v2;

import java.util.function.Function;

public interface MaybeValid<T> {

    <U> MaybeValid<U> map(Function<T, U> m);

    <U> MaybeValid<U> flatMap(Function<T, MaybeValid<U>> m);

    T orElseGet(Function<RequestError, T> defaultValueProvider);
}
