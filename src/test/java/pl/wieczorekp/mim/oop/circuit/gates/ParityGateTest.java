package pl.wieczorekp.mim.oop.circuit.gates;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pl.wieczorekp.mim.oop.circuit.Circuit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParityGateTest {
    @ParameterizedTest
    @MethodSource("pl.wieczorekp.mim.oop.lab9.gates.GateTestUtils#primitiveBooleanArgumentSource")
    void givenNInputsShouldXorCorrectly(boolean[] inputs) {
        // given
        Boolean[] nonPrimitiveInputs = new Boolean[inputs.length];
        IntStream.range(0, inputs.length).forEach(i -> nonPrimitiveInputs[i] = inputs[i]);
        boolean expected = Arrays.stream(nonPrimitiveInputs).filter(val -> val).count() % 2 == 1;

        Circuit c = new Circuit();
        Gate gate = new ParityGate();
        TerminatingGate sink = new TerminatingGate();
        List<SignalEmittingGate> taps = IntStream.range(0, inputs.length)
                .mapToObj(i -> new SignalEmittingGate(inputs[i]))
                .collect(Collectors.toList());

        for (Gate tap : taps) {
            Wire tapGateWire = new Wire(tap, gate);
            tap.connectTo(tapGateWire);
            gate.connectTo(tapGateWire);
        }
        Wire gateSinkWire = new Wire(gate, sink);
        gate.connectTo(gateSinkWire);
        sink.connectTo(gateSinkWire);

        c.addInputGates(taps);
        c.setOutputGate(sink);

        // when
        boolean actual = c.evaluate(inputs);

        // then
        assertEquals(expected, actual);
    }

}