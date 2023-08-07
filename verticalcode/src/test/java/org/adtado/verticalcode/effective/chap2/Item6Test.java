package org.adtado.verticalcode.effective.chap2;

import org.adtado.verticalcode.effective.chap2.item6.BigList;
import org.junit.jupiter.api.Test;

public class Item6Test {
    @Test
    void 불필요한_객체생성_테스트() {

        BigList bigList = new BigList();
        long begin = System.currentTimeMillis();
        bigList.init();

        long end = System.currentTimeMillis();
        System.out.println(end - begin);

        begin = System.currentTimeMillis();
        bigList.init2();

        end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
}
