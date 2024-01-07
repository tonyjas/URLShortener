package com.jasynewycz.transformer;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TODO need to add some parallel execution tests to verify we can correctly get
 * unique sequence numbers from parallel threads
 */
public class URLGeneratorTest {

    @Test
    public void testBaseCase() {

        URLGenerator generator = new URLGenerator(new AtomicLongCounter(0L));
        assertEquals("000001", generator.encodeNext().uniqueId());
    }

    @Test
    public void testRun0To999() {
        URLGenerator generator = new URLGenerator(new AtomicLongCounter(0L));

        Set<String> set = new HashSet<>();
        for(long x = 0L; x < 1000L; x++) {
            assertTrue(set.add(generator.encodeNext().uniqueId()));
        }
    }

    @Test
    public void testRun0To999_999() {
        URLGenerator generator = new URLGenerator(new AtomicLongCounter(0L));

        Set<String> set = new HashSet<>();
        for(long x = 0L; x < 1_000_000L; x++) {
            assertTrue(set.add(generator.encodeNext().uniqueId()));
        }
    }



}
