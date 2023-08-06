package org.adtado.sinclairr.effective.item02;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StudentCTest {
    @Test
    void builder_test() {
        StudentC s3 = new StudentC.StudentCBuilder("ej", 5)
                .level(100)
                .balance(5000)
                .build();

        s3.addMoney(500);
        s3.addMoney(500);
        s3.addMoney(500);
        s3.addMoney(500);

        Assertions.assertThat(s3.getBalance()).isEqualTo(7000);
    }
}