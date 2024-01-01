package com.jasynewycz.urlmapping.service;


import com.jasynewycz.transformer.URLGenerator;
import com.jasynewycz.transformer.URLMappingDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class URLMappingService {

    @PersistenceContext
    EntityManager em;

    @Inject
    URLGenerator generator;

    @PostConstruct
    protected void init() {
        Long startValue = max();
        generator.setStartValue(startValue == null ? 0L : startValue);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public URLMapping find(String shortUrl) {

        return em.find(URLMapping.class, shortUrl);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public URLMapping create(URLMapping mapping) {
        URLMappingDto dto = generator.encodeNext();
        mapping.setUrlHash(dto.uniqueId());
        mapping.setNumericId(dto.counterValue());
        em.persist(mapping);
        return mapping;
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    private Long max() {
        Query query = em.createQuery("SELECT MAX(m.numericId) FROM URLMapping m");
        return (Long)query.getSingleResult();
    }

}