package org.adtado.sinclairr.effective.item01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CalculatorTest {
    @DisplayName("덧셈 계산 검증")
    @Test
    void add_test() {
        Calculator calculator = Calculator.generateFromString("1 2 3 4");

        int result = calculator.calculate();

        assertThat(result).isEqualTo(10);
    }

    @DisplayName("곱셈 계산 검증")
    @Test
    void multiply_test() {
        Calculator calculator = Calculator.generateFromString("/mul 2 8 7");

        int result = calculator.calculate();

        assertThat(result).isEqualTo(112);
    }

    @DisplayName("계산 실패")
    @Test
    void fail_test() {
        assertThatThrownBy(() -> Calculator.generateFromString("s 3 4 1"))
                .isInstanceOf(NumberFormatException.class);
    }

    @DisplayName("덧셈 계산 검증 - String 이용")
    @Test
    void add_test_String() {
        Calculator calculator = new Calculator("1 2 3 4");

        int result = calculator.calculate();

        assertThat(result).isEqualTo(10);
    }

}
