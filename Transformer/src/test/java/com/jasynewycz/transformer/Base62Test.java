package com.jasynewycz.transformer;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class Base62Test {


    @Test
    public void testBaseCase() {
        assertEquals("000000", Base62.encode(0L));
        assertEquals("000001", Base62.encode(1L));
    }

    @Test
    public void testLowLimit() {
        assertThrowsExactly(IllegalArgumentException.class, () -> Base62.encode(-1L));
    }

    @Test
    public void testHighLimit() {

        assertThrowsExactly(IllegalArgumentException.class, () -> Base62.encode(Long.MAX_VALUE));
    }

    @Test
    public void testAlphaNumericOutput() {

        assertEquals("0000g8", Base62.encode(1_000L));
        assertEquals("0002Bi", Base62.encode(10_000L));
        assertEquals("000q0U", Base62.encode(100_000L));
        assertEquals("004c92", Base62.encode(1_000_000L));
        assertEquals("aZl8N0y58M6", Base62.encode(Long.MAX_VALUE-1));
    }

    @Test
    public void testDecode() {

        assertEquals(1_000L, Base62.decodeToLong("0000g8"));
        assertEquals(10_000L, Base62.decodeToLong("0002Bi"));
        assertEquals(100_000L, Base62.decodeToLong("000q0U"));
        assertEquals(1_000_000L, Base62.decodeToLong("004c92"));
        assertEquals(Long.MAX_VALUE-1, Base62.decodeToLong("aZl8N0y58M6"));
    }

    @Test
    public void testRun0To999() {

        Set<String> set = new HashSet<>();
        for(long x = 0L; x < 1000L; x++) {
            assertTrue(set.add(Base62.encode(x)));
        }
    }

    @Test
    public void testRun0To999_999() {

        Set<String> set = new HashSet<>();
        for(long x = 0L; x < 1_000_000L; x++) {
            assertTrue(set.add(Base62.encode(x)));
        }
    }

}
