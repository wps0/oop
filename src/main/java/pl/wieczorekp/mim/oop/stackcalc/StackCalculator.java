package pl.wieczorekp.mim.oop.stackcalc;

import pl.wieczorekp.mim.oop.stackcalc.exception.IntegerOverflowException;
import pl.wieczorekp.mim.oop.stackcalc.exception.StackTooSmallException;

import java.util.*;

class StackCalculator {
    public static final Instruction[] SUPPORTED_INSTRUCTIONS = {
            new ParametrizedInstruction<>("PUSH"),
            new Instruction("POP"),
            new Instruction("ADD"),
            new Instruction("SUB"),
            new Instruction("MUL"),
            new Instruction("DIV"),
            new Instruction("NEG"),
    };
    private final Deque<Integer> stack;
    private final List<Instruction> instructions;

    public StackCalculator(List<Instruction> instructions) {
        this.stack = new ArrayDeque<>();
        this.instructions = instructions;
    }

    public void push(int val) {
        System.out.printf("PUSH %d\n", val);
        stack.push(val);
    }

    public int pop() throws StackTooSmallException {
        ensureStackContains(1);
        int popped = stack.pop();
        System.out.printf("POP %d\n", popped);
        return popped;
    }

    public void add() throws StackTooSmallException, IntegerOverflowException {
        ensureStackContains(2);
        int a = pop(), b = pop();
        long result = (long) a + b;

        ensureDoesNotOverflow(result);

        System.out.printf("ADD %d + %d\n", a, b);
        push(a + b);
    }

    public void sub() throws StackTooSmallException, IntegerOverflowException {
        ensureStackContains(2);
        int a = pop(), b = pop();
        long result = (long) a - b;

        ensureDoesNotOverflow(result);

        System.out.printf("SUB %d - %d\n", a, b);
        push((int) result);
    }

    public void mul() throws ArithmeticException, StackTooSmallException, IntegerOverflowException {
        ensureStackContains(2);
        int a = pop(), b = pop();
        long result = (long) a * b;

        ensureDoesNotOverflow(result);

        System.out.printf("MUL %d * %d%n", a, b);
        push((int) result);
    }

    public void div() throws ArithmeticException, StackTooSmallException, IntegerOverflowException {
        ensureStackContains(2);
        int a = pop();
        int b = pop();
        long result = (long) a / b;

        ensureDoesNotOverflow(result);

        System.out.printf("DIV %d / %d\n", a, b);
        push((int) result);
    }

    public void neg() throws StackTooSmallException, IntegerOverflowException {
        ensureStackContains(1);
        long top = pop();

        ensureDoesNotOverflow(-top);

        System.out.printf("NEG %d\n", top);
        push((int) -top);
    }

    public void execute() throws StackTooSmallException, IntegerOverflowException {
        for (Instruction inst : instructions) {
            switch (inst.name().toUpperCase()) {
                case "PUSH" -> push(((ParametrizedInstruction<Integer>) inst).param());
                case "POP" -> pop();
                case "ADD" -> add();
                case "SUB" -> sub();
                case "MUL" -> mul();
                case "DIV" -> div();
                case "NEG" -> neg();
                default -> throw new IllegalArgumentException("Unsupported instruction " + inst.name());
            }
        }
    }

    public int peek() {
        if (stack.isEmpty())
            throw new EmptyStackException();
        return stack.peek();
    }

    public void printStack() {
        List<Integer> listFromStack = new ArrayList<>(stack);
        Collections.reverse(listFromStack);

        Iterator<Integer> it = listFromStack.iterator();
        int i = 0;
        while (it.hasNext()) {
            System.out.printf("%d: %d\n", i++, it.next());
        }
    }

    private void ensureStackContains(int n) throws StackTooSmallException {
        if (stack.size() < n) {
            throw new StackTooSmallException("Not enough elements on the stack");
        }
    }

    private void ensureDoesNotOverflow(long value) throws IntegerOverflowException {
        if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) {
            throw new IntegerOverflowException("Integer overflow");
        }
    }
}
