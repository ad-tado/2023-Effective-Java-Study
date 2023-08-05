package org.adtado.sinclairr.effective.item01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator {
    private char operator;
    private List<Integer> operands;

    private static List<Integer> parse(String s) {
        return Arrays.stream(s.split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }
    public static Calculator from(String s) {
        if (s.startsWith("/mul")) {
            return new Calculator(parse(s.substring(5)), '*');
        }

        return new Calculator(parse(s), '+');
    }

    private Calculator(List<Integer> operands, char operator) {
        this.operands = operands;
        this.operator = operator;
    }

    public Calculator(String s) {
        if (s.startsWith("/mul")) {
            this.operands = parse(s.substring(5));
            this.operator = '*';
        }
        else {
            this.operands = parse(s);
            this.operator = '+';
        }
    }

    public int calculate() {
        if (operator == '+') {
            return this.add();
        }
        if (operator == '*') {
            return this.multiply();
        }

        throw new IllegalArgumentException();
    }

    private int add() {
        return operands.stream().reduce(0, (x, y) -> x + y);
    }


    private int multiply() {
        return operands.stream().reduce(1,  (x, y) -> x * y);
    }
}
