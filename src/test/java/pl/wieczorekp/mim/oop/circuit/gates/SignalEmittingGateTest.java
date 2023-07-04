package pl.wieczorekp.mim.oop.circuit.gates;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.wieczorekp.mim.oop.circuit.Circuit;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SignalEmittingGateTest {
    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void givenSingleInputsShouldEmitCorrectValue(boolean input) {
        // given
        boolean expected = input;

        Circuit c = new Circuit();
        SignalEmittingGate gate = new SignalEmittingGate(input);
        TerminatingGate sink = new TerminatingGate();

        Wire gateSinkWire = new Wire(gate, sink);
        gate.connectTo(gateSinkWire);
        sink.connectTo(gateSinkWire);

        c.addInputGates(List.of(gate));
        c.setOutputGate(sink);

        // when
        boolean actual = c.evaluate(new boolean[]{input});

        // then
        assertEquals(expected, actual);
    }
}