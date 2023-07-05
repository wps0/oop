package pl.wieczorekp.mim.oop.stackcalc;


import pl.wieczorekp.mim.oop.stackcalc.exception.IntegerOverflowException;
import pl.wieczorekp.mim.oop.stackcalc.exception.InvalidParameterException;
import pl.wieczorekp.mim.oop.stackcalc.exception.StackTooSmallException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StackMachine {

    public static void main(String[] args) {
        try {
            StackCalculator calc = new StackCalculator(parseInstructionString("PUSH 101 PUSH -3\nPUSH 10 PUSH 21\ndiv mUl PoP PUSH\n100\nSUB PUSH 10 PUSH 20"));
            calc.execute();
            calc.printStack();
        } catch (StackTooSmallException | IntegerOverflowException | InvalidParameterException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Illegal instruction:");
            e.printStackTrace();
        }
    }

    public static List<Instruction> parseInstructionString(String instructions) throws InvalidParameterException {
        String[] items = instructions.split("\\s");
        List<Instruction> instructionList = new ArrayList<>();

        int i = 0;
        while (i < items.length) {
            final int i2 = i;
            Optional<Instruction> inst = Arrays.stream(StackCalculator.SUPPORTED_INSTRUCTIONS)
                    .filter(s -> s.name().equalsIgnoreCase(items[i2]))
                    .findFirst();

            if (inst.isEmpty()) {
                throw new IllegalArgumentException("Invalid instruction " + items[i]);
            }

            if (inst.get() instanceof ParametrizedInstruction<?>) {
                // dla uproszczenia: only int
                Integer param = null;
                try {
                    param = Integer.valueOf(items[i+1]);
                } catch (NumberFormatException e) {
                    throw new InvalidParameterException("The parameter must be an integer");
                }

                instructionList.add(new ParametrizedInstruction<>(inst.get().name().toUpperCase(), param));

                i++;
            } else {
                instructionList.add(new Instruction(items[i].toUpperCase()));
            }

            i++;
        }

        return instructionList;
    }

}
