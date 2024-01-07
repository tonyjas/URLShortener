package com.jasynewycz.transformer;

import java.util.concurrent.atomic.AtomicLong;


/**
 * Simple wrapper for an atomic long based counter
 */
public class AtomicLongCounter implements Counter {

    final private AtomicLong counter;

    /**
     *
     * @param start start value for this counter. Must be greater than 0 and less than Long.MAX_VALUE
     */
    public AtomicLongCounter(long start) {
        if(start < 0 || start == Long.MAX_VALUE) {
            throw new IllegalArgumentException("start value must be between 0 and " + Long.MAX_VALUE);
        }
        this.counter = new AtomicLong(start);
    }

    /**
     *
     * @return the next available value from this counter
     */
    public long getNext() {

        synchronized (AtomicLongCounter.class) {
            if (counter.get() == Long.MAX_VALUE) {
                throw new IllegalStateException("counter has hit max of: " + Long.MAX_VALUE);
            }

            return counter.incrementAndGet();
        }
    }
}