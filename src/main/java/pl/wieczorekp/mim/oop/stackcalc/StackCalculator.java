package pl.wieczorekp.mim.oop.stackcalc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Stack;

class StackCalculator {
    private static final Instruction[] AVAILABLE_INSTRUCTIONS = {
            new ParametrizedInstruction<>("PUSH"),
            new Instruction("POP"),
            new Instruction("ADD"),
            new Instruction("SUB"),
            new Instruction("MUL"),
            new Instruction("DIV"),
            new Instruction("NEG"),
    };
    private Stack<Integer> stack;

    public StackCalculator() {
        this.stack = new Stack<>();
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

        System.out.printf("ADD a=%d b=%d\n", a, b);
        push(a + b);
    }

    public void sub() throws StackTooSmallException, IntegerOverflowException {
        ensureStackContains(2);
        int a = pop(), b = pop();
        long result = Long.max(a, b) - Integer.min(a, b);

        ensureDoesNotOverflow(result);

        System.out.printf("SUB a=%d b=%d\n", Integer.max(a, b), Integer.min(a, b));
        push((int) result);
    }

    public void mul() throws ArithmeticException, StackTooSmallException, IntegerOverflowException {
        ensureStackContains(2);
        int a = pop(), b = pop();
        long result = (long) a * b;

        ensureDoesNotOverflow(result);

        System.out.printf("MUL a=%d b=%d\n", a, b);
        push((int) result);
    }

    public void div() throws ArithmeticException, StackTooSmallException, IntegerOverflowException {
        ensureStackContains(2);
        int a = pop(), b = pop();
        long result = Long.max(a, b) / Integer.min(a, b);

        ensureDoesNotOverflow(result);

        System.out.printf("DIV %d / %d\n", Integer.max(a, b), Integer.min(a, b));
        push((int) result);
    }

    public void neg() throws StackTooSmallException, IntegerOverflowException {
        ensureStackContains(1);
        long top = pop();

        ensureDoesNotOverflow(-top);

        System.out.printf("NEG %d\n", top);
        push((int) -top);
    }

    public void executeInstructions(ArrayList<Instruction> instructions) throws StackTooSmallException, IntegerOverflowException {
        for (Instruction inst : instructions) {
            switch (inst.getName().toUpperCase()) {
                case "PUSH" -> push((Integer) ((ParametrizedInstruction<?>) inst).getParam());
                case "POP" -> pop();
                case "ADD" -> add();
                case "SUB" -> sub();
                case "MUL" -> mul();
                case "DIV" -> div();
                case "NEG" -> neg();
                default -> throw new IllegalArgumentException("Unsupported instruction " + inst.getName());
            }
        }
    }

    public static ArrayList<Instruction> parseInstructionString(String instructions) {
        String[] items = instructions.split(" ");
        ArrayList<Instruction> instructionList = new ArrayList<>();

        for (int i = 0; i < items.length; i++) {
            final int i2 = i;
            Optional<Instruction> inst = Arrays.stream(AVAILABLE_INSTRUCTIONS)
                    .filter(s -> s.getName().equalsIgnoreCase(items[i2]))
                    .findFirst();

            if (inst.isEmpty()) {
                throw new IllegalArgumentException("Invalid instruction " + items[i]);
            }

            if (inst.get() instanceof ParametrizedInstruction<?>) {
                // dla uproszczenia: only int
                Integer param = Integer.getInteger(items[i+1]);
                instructionList.add(new ParametrizedInstruction<>(inst.get().getName(), param));

                i++;
            } else {
                instructionList.add(new Instruction(items[i]));
            }
        }

        return instructionList;
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
