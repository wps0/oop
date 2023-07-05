package pl.wieczorekp.mim.oop.stackcalc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StackMachineTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "PUSH 3 PUSH 10\nADD",
            "pUsH 3 PuSh 10\nadd",
            "push\n3 PUSH 10\nadd"
    })
    void parseInstructionString_givenSampleInstructionStringShouldParseItCorrectly(String seq) {
        // when
        List<Instruction> instructions = StackMachine.parseInstructionString(seq);

        // then
        assertEquals(3, instructions.size());
        assertEquals("PUSH", instructions.get(0).name());
        assertTrue(instructions.get(0).isParameterised());
        assertEquals("PUSH", instructions.get(1).name());
        assertTrue(instructions.get(1).isParameterised());
        assertEquals("ADD", instructions.get(2).name());
        assertFalse(instructions.get(2).isParameterised());
    }

}