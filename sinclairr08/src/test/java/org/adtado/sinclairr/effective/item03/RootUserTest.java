package org.adtado.sinclairr.effective.item03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.assertj.core.api.Assertions.*;

class RootUserTest {
    @DisplayName("싱글턴 생성 깨기")
    @Test
    void singleton_break_test() throws Exception{
        Constructor<RootUser> constructor = RootUser.class.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);

        RootUser rootUser = RootUser.getInstance();
        RootUser anotherUser = constructor.newInstance("attacker");

        assertThat(rootUser.getName()).isNotEqualTo(anotherUser.getName());
    }

}
