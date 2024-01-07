package com.jasynewycz.transformer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class AtomicLongCounterTest {


    @Test
    public void testSimpleIncrement() {

        AtomicLongCounter c = new AtomicLongCounter(0);
        assertEquals(1, c.getNext());
    }

    @Test
    public void testLowerBoundary() {

        final long startValue = -1;
        assertThrowsExactly(IllegalArgumentException.class, () -> new AtomicLongCounter(startValue));
    }

    @Test
    public void testHigherBoundary() {

        final long startValue = Long.MAX_VALUE;
        assertThrowsExactly(IllegalArgumentException.class, () -> new AtomicLongCounter(startValue));
    }

    @Test
    public void testIncrementToLimit() {

        AtomicLongCounter c = new AtomicLongCounter(Long.MAX_VALUE-1);
        assertEquals(Long.MAX_VALUE, c.getNext());
        assertThrowsExactly(IllegalStateException.class, c::getNext);
    }
}
