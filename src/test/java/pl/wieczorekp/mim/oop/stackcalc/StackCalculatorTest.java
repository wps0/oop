package pl.wieczorekp.mim.oop.stackcalc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StackCalculatorTest {

    @Test
    void givenProperInstructionStringShouldCalculate() {
        // given
        StackCalculator calc = new StackCalculator();
        // instruction set 1
        Instruction[] instSeq1 = {
                new ParametrizedInstruction<>("PUSH", 3),
                new Instruction("POP"),
        };
        ArrayList<Instruction> instructionList1 = new ArrayList<>(Arrays.asList(instSeq1));

        // instruction set 2
        Instruction[] instSeq2 = {
                new ParametrizedInstruction<>("PUSH", 10),
                new ParametrizedInstruction<>("PUSH", -3),
                new Instruction("ADD"),
                new Instruction("POP"),
        };
        ArrayList<Instruction> instructionList2 = new ArrayList<>(Arrays.asList(instSeq2));

        // instruction set 3
        Instruction[] instSeq3 = {
                new ParametrizedInstruction<>("PUSH", 10),
                new Instruction("ADD"),
        };
        ArrayList<Instruction> instructionList3 = new ArrayList<>(Arrays.asList(instSeq3));

        // when
        // then
        assertDoesNotThrow(() -> calc.executeInstructions(instructionList1));
        assertDoesNotThrow(() -> calc.executeInstructions(instructionList2));
        assertThrows(StackTooSmallException.class, () -> calc.executeInstructions(instructionList3));
    }
}