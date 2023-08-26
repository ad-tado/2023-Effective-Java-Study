package org.adtado.intrager.effective.chapter02.item06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class Item06AutoBoxingTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @DisplayName("Wrapper 타입으로 합을 구한 게 더 느리다")
    @Test
    void wrapperSlowerThanPrimitiveInSum() {
        long startWrapper = System.currentTimeMillis();
        Item06AutoBoxing.sumWithWrapperType();
        long endWrapper = System.currentTimeMillis();
        logger.info("resultWrapper time : {}", (endWrapper - startWrapper));

        long startPrimitive = System.currentTimeMillis();
        Item06AutoBoxing.sumWithPrimitiveType();
        long endPrimitive = System.currentTimeMillis();
        logger.info("resultPrimitive time : {}", (endPrimitive - startPrimitive));

        assertTrue((endWrapper - startWrapper) > (endPrimitive - startPrimitive));
    }
}