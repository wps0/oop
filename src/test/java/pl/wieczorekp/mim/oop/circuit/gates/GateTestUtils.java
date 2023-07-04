package pl.wieczorekp.mim.oop.circuit.gates;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public final class GateTestUtils {
    private GateTestUtils() {}

    public static Stream<Arguments> primitiveBooleanArgumentSource() {
        return Stream.of(
                Arguments.of(new boolean[]{true}),
                Arguments.of(new boolean[]{false}),
                Arguments.of(new boolean[]{true, true}),
                Arguments.of(new boolean[]{false, false}),
                Arguments.of(new boolean[]{true, false}),
                Arguments.of(new boolean[]{false, true}),
                Arguments.of(new boolean[]{true, true, true}),
                Arguments.of(new boolean[]{true, true, false}),
                Arguments.of(new boolean[]{true, false, true}),
                Arguments.of(new boolean[]{true, false, false}),
                Arguments.of(new boolean[]{false, true, true}),
                Arguments.of(new boolean[]{false, true, false}),
                Arguments.of(new boolean[]{false, false, true}),
                Arguments.of(new boolean[]{false, false, false})
        );
    }

    public static Stream<Arguments> primitive3BooleanArgumentSource() {
        return Stream.of(
                Arguments.of(new boolean[]{true, true, true}),
                Arguments.of(new boolean[]{true, true, false}),
                Arguments.of(new boolean[]{true, false, true}),
                Arguments.of(new boolean[]{true, false, false}),
                Arguments.of(new boolean[]{false, true, true}),
                Arguments.of(new boolean[]{false, true, false}),
                Arguments.of(new boolean[]{false, false, true}),
                Arguments.of(new boolean[]{false, false, false})
        );
    }


}
