package pl.wieczorekp.mim.oop.stackcalc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.wieczorekp.mim.oop.stackcalc.exception.IntegerOverflowException;
import pl.wieczorekp.mim.oop.stackcalc.exception.StackTooSmallException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StackCalculatorTest {
    StackCalculator stackCalculator;
    @BeforeEach
    void init() {
        stackCalculator = new StackCalculator(List.of());
    }

    @Test
    void push_shouldPushTheValueToTheStack() {
        // given
        int x = 10;

        // when
        stackCalculator.push(x);

        // then
        assertEquals(x, stackCalculator.peek());
    }

    @Test
    void pop_shouldPopAValueFromTheStack() {
        // given
        int x = 10;
        stackCalculator.push(x);
        stackCalculator.push(x+1);

        // when
        assertDoesNotThrow(() -> stackCalculator.pop());

        // then
        assertEquals(x, stackCalculator.peek());
    }

    @Test
    void pop_shouldThrowExceptionIfTheStackIsEmpty() {
        assertThrowsExactly(StackTooSmallException.class, () -> stackCalculator.pop());
    }

    @Test
    void add_givenLargeEnoughStackShouldAddTwoValues() {
        // given
        stackCalculator.push(11);
        stackCalculator.push(12);

        // when
        assertDoesNotThrow(() -> stackCalculator.add());

        // then
        assertEquals(23, stackCalculator.peek());
    }

    @Test
    void add_givenOneValueShouldThrowAnException() {
        // given
        stackCalculator.push(11);

        // when
        assertThrowsExactly(StackTooSmallException.class, () -> stackCalculator.add());

        // then
        assertEquals(11, stackCalculator.peek());
    }

    @Test
    void add_givenAnEmptyStackShouldThrowAnException() {
        assertThrowsExactly(StackTooSmallException.class, () -> stackCalculator.add());
    }

    @Test
    void sub_givenLargeEnoughStackShouldSubTwoValues() {
        // given
        stackCalculator.push(11);
        stackCalculator.push(12);

        // when
        assertDoesNotThrow(() -> stackCalculator.sub());

        // then
        assertEquals(1, stackCalculator.peek());
    }

    @Test
    void sub_givenOneValueShouldThrowAnException() {
        // given
        stackCalculator.push(11);

        // when
        assertThrowsExactly(StackTooSmallException.class, () -> stackCalculator.sub());

        // then
        assertEquals(11, stackCalculator.peek());
    }

    @Test
    void sub_givenAnEmptyStackShouldThrowAnException() {
        assertThrowsExactly(StackTooSmallException.class, () -> stackCalculator.sub());
    }

    @Test
    void mul_givenLargeEnoughStackShouldMulTwoValues() {
        // given
        stackCalculator.push(11);
        stackCalculator.push(12);

        // when
        assertDoesNotThrow(() -> stackCalculator.mul());

        // then
        assertEquals(11*12, stackCalculator.peek());
    }

    @Test
    void mul_givenOneValueShouldThrowAnException() {
        // given
        stackCalculator.push(11);

        // when
        assertThrowsExactly(StackTooSmallException.class, () -> stackCalculator.mul());

        // then
        assertEquals(11, stackCalculator.peek());
    }

    @Test
    void mul_givenAnEmptyStackShouldThrowAnException() {
        assertThrowsExactly(StackTooSmallException.class, () -> stackCalculator.mul());
    }

    @Test
    void div_givenLargeEnoughStackShouldDivTwoValues() {
        // given
        stackCalculator.push(12);
        stackCalculator.push(110);

        // when
        assertDoesNotThrow(() -> stackCalculator.div());

        // then
        assertEquals(9, stackCalculator.peek());
    }

    @Test
    void div_givenOneValueShouldThrowAnException() {
        // given
        stackCalculator.push(11);

        // when
        assertThrowsExactly(StackTooSmallException.class, () -> stackCalculator.div());

        // then
        assertEquals(11, stackCalculator.peek());
    }

    @Test
    void div_givenAnEmptyStackShouldThrowAnException() {
        assertThrowsExactly(StackTooSmallException.class, () -> stackCalculator.div());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 10})
    void neg_shouldNegateTheIntegerCorrectly(int x) {
        // given
        stackCalculator.push(x);

        // when
        assertDoesNotThrow(() -> stackCalculator.neg());

        // then
        assertEquals(-x, stackCalculator.peek());
    }

    @Test
    void neg_givenTooSmallIntegerShouldThrowAnException() {
        // given
        int x = Integer.MIN_VALUE;
        stackCalculator.push(x);

        // when
        assertThrowsExactly(IntegerOverflowException.class, () -> stackCalculator.neg());
    }

    @Test
    void neg_givenMaxIntShouldWork() {
        // given
        int x = Integer.MAX_VALUE;
        stackCalculator.push(x);

        // when
        assertDoesNotThrow(() -> stackCalculator.neg());

        // then
        assertEquals(-x, stackCalculator.peek());
    }

    @Test
    void execute_shouldExecuteSampleInstructionSequence1() {
        // given
        Instruction[] instSeq = {
                new ParametrizedInstruction<>("PUSH", 3),
                new Instruction("POP"),
        };
        List<Instruction> instructions = new ArrayList<>(Arrays.asList(instSeq));
        stackCalculator = new StackCalculator(instructions);

        // when
        assertDoesNotThrow(stackCalculator::execute);

        // then
        assertThrowsExactly(EmptyStackException.class, stackCalculator::peek);
    }

    @Test
    void execute_shouldExecuteSampleInstructionSequence2() {
        // given
        Instruction[] instSeq = {
                new ParametrizedInstruction<>("PUSH", 10),
                new ParametrizedInstruction<>("PUSH", -3),
                new Instruction("ADD"),
        };
        List<Instruction> instructions = new ArrayList<>(Arrays.asList(instSeq));
        stackCalculator = new StackCalculator(instructions);

        // when
        assertDoesNotThrow(stackCalculator::execute);

        // then
        assertEquals(7, stackCalculator.peek());
    }

    @Test
    void execute_shouldExecuteSampleInstructionSequence3() {
        // given
        Instruction[] instSeq = {
                new ParametrizedInstruction<>("PUSH", 1),
                new ParametrizedInstruction<>("PUSH", 10),
                new ParametrizedInstruction<>("PUSH", -3),
                new Instruction("ADD"),
                new Instruction("NEG"),
                new ParametrizedInstruction<>("PUSH", 1330),
                new Instruction("SUB"),
                new Instruction("SUB"),
        };
        List<Instruction> instructions = new ArrayList<>(Arrays.asList(instSeq));
        stackCalculator = new StackCalculator(instructions);

        // when
        assertDoesNotThrow(stackCalculator::execute);

        // then
        assertEquals(1336, stackCalculator.peek());
    }

    @Test
    void execute_shouldExecuteSampleInstructionSequence4() {
        // given
        Instruction[] instSeq = {
                new ParametrizedInstruction<>("PUSH", 101),
                new ParametrizedInstruction<>("PUSH", -3),
                new ParametrizedInstruction<>("PUSH", 10),
                new ParametrizedInstruction<>("PUSH", 21),
                new Instruction("DIV"), // top: 2
                new Instruction("MUL"), // top: -6
                new Instruction("POP"), // top: 100
                new ParametrizedInstruction<>("PUSH", 100),
                new Instruction("SUB"),
        };
        List<Instruction> instructions = new ArrayList<>(Arrays.asList(instSeq));
        stackCalculator = new StackCalculator(instructions);

        // when
        assertDoesNotThrow(stackCalculator::execute);

        // then
        assertEquals(-1, stackCalculator.peek());
    }
}