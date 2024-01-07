package com.jasynewycz.transformer;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * generates hashes for use in short URL's.
 */
@ApplicationScoped
public class URLGenerator {


    private Counter counter;

    public URLGenerator(Counter counter) {
        this.counter = counter;
    }

    /**
     * Can be created via empty constructor but MUST init the counter via setStartValue prior to calling encodeNext()
     */
    public URLGenerator() {
    }

    /**
     *
     * @return URLMappingDto wrapper for a counter value and it's base62 hash
     */
    public URLMappingDto encodeNext() {
        if(counter == null) {
            throw new CounterNotInitializedException();
        }
        long count = counter.getNext();
        return new URLMappingDto(Base62.encode(count), count);
    }

    /**
     * Sets the start value of the counter to be used to generate hashes
     * @param startValue the value to use as the start of the counter
     */
    public void setStartValue(Long startValue) {
        counter = new AtomicLongCounter(startValue);
    }
}