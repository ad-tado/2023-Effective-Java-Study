package org.adtado.sinclairr.effective.item03;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class RootUserEnumTest {
    @DisplayName("Enum을 이용하면 싱글턴이 보장됨")
    @Test
    public void singleton_save_test() {
        RootUserEnum e = RootUserEnum.INSTANCE;

        assertThat(e.getName()).isEqualTo("admin");
    }

}
