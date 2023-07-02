package pl.wieczorekp.mim.oop.lab9.gates;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.wieczorekp.mim.oop.lab9.Circuit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AndGateTest {

    @ParameterizedTest
    @MethodSource()
    void givenNInputsShouldAndCorrectly(boolean[] inputs) {
        // given
        Boolean[] nonPrimitiveInputs = new Boolean[inputs.length];
        IntStream.range(0, inputs.length).forEach(i -> nonPrimitiveInputs[i] = inputs[i]);
        boolean expected = Arrays.stream(nonPrimitiveInputs).allMatch(in -> in);

        Circuit c = new Circuit();
        AndGate gate = new AndGate();
        TerminatingGate sink = new TerminatingGate();
        List<SignalEmittingGate> taps = IntStream.range(0, inputs.length)
                .mapToObj(i -> new SignalEmittingGate(inputs[i]))
                .collect(Collectors.toList());

        taps.forEach(input -> gate.connectTo(new Wire(input, gate)));
        taps.forEach(input -> input.connectTo(new Wire(input, gate)));
        gate.connectTo(new Wire(gate, sink));

        c.addInputGates(taps);
        c.setOutputGate(sink);

        // when
        boolean actual = c.evaluate(inputs);

        // then
        assertEquals(expected, actual);
    }

    public static Stream<Arguments> givenNInputsShouldAndCorrectly() {
        return Stream.of(
                Arguments.of(new boolean[]{true})
//                Arguments.of(new boolean[]{false}),
//                Arguments.of(new boolean[]{true, true}),
//                Arguments.of(new boolean[]{false, false}),
//                Arguments.of(new boolean[]{true, false}),
//                Arguments.of(new boolean[]{false, true}),
//                Arguments.of(new boolean[]{true, true, true}),
//                Arguments.of(new boolean[]{true, true, false}),
//                Arguments.of(new boolean[]{true, false, true}),
//                Arguments.of(new boolean[]{true, false, false}),
//                Arguments.of(new boolean[]{false, true, true}),
//                Arguments.of(new boolean[]{false, true, false}),
//                Arguments.of(new boolean[]{false, false, true}),
//                Arguments.of(new boolean[]{false, false, false})
        );
    }
}