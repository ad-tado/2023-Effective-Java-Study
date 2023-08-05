package org.adtado.sinclairr.effective.item02;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentBTest {
    @Test
    void javabeans_test() {
        StudentB s2 = new StudentB();
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                new Thread() {
                    public void run() {
                        s2.setName("ej");
                        s2.setGrade(3);
                        s2.setLevel(100);
                        s2.setBalance(5000);
                    }
                }.start();
            }

            else {
                new Thread() {
                    public void run() {
                        s2.addMoney(500);
                    }
                }.start();
            }
        }

        // Following result should be the one of (5000, 5500, 6000, 6500, 7000)
        System.out.println(s2.getBalance());
    }
}
