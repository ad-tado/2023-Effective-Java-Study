package org.adtado.verticalcode.effective.chap2;

import org.adtado.verticalcode.effective.chap2.item7.MyCache;
import org.junit.jupiter.api.Test;

public class Item7Test {

    @Test
    public void weakHashMapTest() {
        MyCache myCache = new MyCache();
        for(int i = 0; i < 100000; i++) {
            myCache.put(String.valueOf(i), i);
        }

        for(int i = 0; i < 100000; i++) {
            Object tmp = myCache.get(String.valueOf(i));
            if(tmp == null) {
                System.out.println("NOT FOUND - " + i );
            }
        }
    }
}
