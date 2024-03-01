package ladder.domain.attribute;

import java.util.List;
import java.util.function.IntConsumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public record Width<L>(int value) {

    private static final int MIN_WIDTH = 1;

    public Width {
        if (value < MIN_WIDTH) {
            throw new IllegalStateException("자연수가 아닙니다: %d".formatted(value));
        }
    }

    public List<L> repeat(final Supplier<L> supplier) {
        return IntStream.range(0, value)
                .mapToObj(__ -> supplier.get())
                .toList();
    }

    public void repeat(final IntConsumer consumer) {
        IntStream.range(0, value).forEach(consumer);
    }
}
