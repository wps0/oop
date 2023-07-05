package pl.wieczorekp.mim.oop.circuit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CircuitTest {
    @ParameterizedTest
    @MethodSource("pl.wieczorekp.mim.oop.lab9.gates.GateTestUtils#primitive3BooleanArgumentSource")
    void givenSampleCircuitShouldEvaluateToCorrectValues(boolean[] inputs) {
        // given
        Circuit sampleCircuit = CircuitBuilder.buildSampleCircuit();
        boolean expected = !((inputs[0] | inputs[1]) & inputs[2]);

        // when
        boolean actualValue = sampleCircuit.evaluate(inputs);
        int actualDepth = sampleCircuit.depth();
        int actualLength = sampleCircuit.inputLength();
        int actualSize = sampleCircuit.size();

        // then
        assertEquals(expected, actualValue);
        assertEquals(3, actualDepth);
        assertEquals(3, actualLength);
        assertEquals(7, actualSize);
    }
}