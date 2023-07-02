package pl.wieczorekp.mim.oop.lab9.gates;

import lombok.Value;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.wieczorekp.mim.oop.lab9.Circuit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class NotGateTest {
    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void givenOneInputShouldNotCorrectly(boolean input) {
        // given
        boolean expected = !input;

        Circuit c = new Circuit();
        NotGate gate = new NotGate();
        TerminatingGate sink = new TerminatingGate();
        List<SignalEmittingGate> taps = List.of(new SignalEmittingGate(input));

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
        boolean actual = c.evaluate(new boolean[]{input});

        // then
        assertEquals(expected, actual);
    }

}