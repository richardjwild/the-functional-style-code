package v1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("A stack")
class StackSpec {

    @Nested
    class when_new {

        Stack<String> newStack = Stack.create(String.class);

        @Test
        void is_empty() {
            assertThat(newStack.depth(), is(0));
        }
    }

    @Nested
    class when_empty {

        Stack<String> emptyStack = Stack.create(String.class);

        @Test
        void cannot_be_popped() {
            assertThat(emptyStack.pop().depth(), is(0));
        }

        @Test
        void has_nothing_on_top() {
            assertThat(emptyStack.top(), is(Optional.empty()));
        }

        @Test
        void becomes_non_empty_on_pushing() {
            var pushed = emptyStack.push("a string");
            assertThat(pushed.depth(), is(1));
        }
    }

    @Nested
    class when_non_empty {

        Stack<String> nonEmptyStack = Stack.create(String.class).push("a value");

        @Test
        void becomes_deeper_on_pushing() {
            var pushed = nonEmptyStack.push("another value");
            assertThat(pushed.depth(), is(nonEmptyStack.depth() + 1));
        }

        @Test
        void becomes_less_deep_on_popping() {
            var popped = nonEmptyStack.pop();
            assertThat(popped.depth(), is(nonEmptyStack.depth() - 1));
        }

        @Test
        void has_value_last_pushed_on_top() {
            assertThat(nonEmptyStack.top(), is(Optional.of("a value")));
            assertThat(nonEmptyStack.push("new top").top(), is(Optional.of("new top")));
        }
    }
}
