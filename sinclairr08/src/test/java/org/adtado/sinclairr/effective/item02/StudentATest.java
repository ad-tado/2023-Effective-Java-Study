package org.adtado.sinclairr.effective.item02;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class StudentATest {
    @Test
    void telescoping_test() {
        StudentA s1 = new StudentA("ej", 1, "", 100, 0, 5000);

        s1.addMoney(500);
        s1.addMoney(500);
        s1.addMoney(500);
        s1.addMoney(500);

        Assertions.assertThat(s1.getBalance()).isEqualTo(7000);

    }
}