package com.jasynewycz.urlmapping.service;


import com.jasynewycz.transformer.AtomicLongCounter;
import com.jasynewycz.transformer.URLGenerator;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

/**
 * Basic happy path tests
 * TODO - add failure cases
 */
@ExtendWith(MockitoExtension.class)
public class URLMappingServiceTest {

    @Mock
    EntityManager em;

    @Test
    public void testCreate() {
        URLMappingService service = new URLMappingService();
        service.em = em;

        URLGenerator gen = new URLGenerator(new AtomicLongCounter(0L));
        service.generator = gen;

        URLMapping mapping = new URLMapping();
        mapping.setLongUrl("https://www.bbc.co.uk");

        URLMapping newMapping = service.create(mapping);

        verify(em).persist(mapping);

        assertEquals("https://www.bbc.co.uk", newMapping.getLongUrl());
        assertEquals("000001", newMapping.getUrlHash());
        assertEquals(1L, newMapping.getNumericId());
    }

    @Test
    public void testFind() {
        URLMappingService service = new URLMappingService();
        service.em = em;

        URLGenerator gen = new URLGenerator(new AtomicLongCounter(0L));
        service.generator = gen;

        URLMapping newMapping = service.find("000001");

        assertNull(newMapping);
        verify(em).find(URLMapping.class, "000001");


        URLMapping mapping = new URLMapping();
        mapping.setLongUrl("https://www.bbc.co.uk");
        service.create(mapping);

        newMapping = service.find("000001");

        verify(em, times(2)).find(URLMapping.class, "000001");
    }

}
