package pl.wieczorekp.mim.oop.lab9.gates;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.wieczorekp.mim.oop.lab9.Circuit;
import sun.misc.Signal;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

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