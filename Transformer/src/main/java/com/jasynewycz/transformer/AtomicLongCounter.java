package com.jasynewycz.transformer;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongCounter implements Counter {

    final private AtomicLong counter;

    public AtomicLongCounter(long start) {
        if(start < 0 || start == Long.MAX_VALUE) {
            throw new IllegalArgumentException("start value must be between 0 and " + Long.MAX_VALUE);
        }
        this.counter = new AtomicLong(start);
    }

    public long getNext() {

        if(counter.get() == Long.MAX_VALUE) {
            throw new IllegalStateException("counter has hit max of: " + Long.MAX_VALUE);
        }

        return counter.incrementAndGet();
    }

}