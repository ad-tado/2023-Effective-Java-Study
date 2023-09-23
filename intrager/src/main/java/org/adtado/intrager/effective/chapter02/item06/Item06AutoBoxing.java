package org.adtado.intrager.effective.chapter02.item06;

public class Item06AutoBoxing {

    public static long sumWithWrapperType() {
        Long sum = 0L;
        for(long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }

    public static long sumWithPrimitiveType() {
        long sum = 0;
        for(long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }
}
