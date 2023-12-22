package com.jasynewycz.transformer;



import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class URLGenerator {


    private Counter counter;

    public URLGenerator(Counter counter) {
        this.counter = counter;
    }

    /**
     * can create via empty constructor but MUST init the counter via setStartValue prior to calling encodeNext()
     */
    public URLGenerator() {

    }

    public URLMappingDto encodeNext() {
        if(counter == null) {
            throw new CounterNotInitializedException();
        }
        long count = counter.getNext();
        return new URLMappingDto(Base62.encode(count), count);
    }

    public void setStartValue(Long startValue) {
        counter = new AtomicLongCounter(startValue);
    }
}