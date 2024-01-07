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

/**
 * Service responsible for all transactional interactions with the data store. This implementation is simply backed
 * by a JPA/hibernate entityManager with a datasource defined in wildfly with an expected JNDI lookup name:
 *  java:/PostgresDS
 *
 */
@ApplicationScoped
public class URLMappingService {

    @PersistenceContext
    EntityManager em;

    @Inject
    URLGenerator generator;

    /**
     * Initializes the start value of the counter used to generate hashes via a call to max() which returns the max
     * numeric value stored in the database or 0 if nothing stored yet.
     */
    @PostConstruct
    protected void init() {
        Long startValue = max();
        generator.setStartValue(startValue == null ? 0L : startValue);
    }

    /**
     * Performs a lookup for a URLMapping entity matching the supplied urlHash
     * @param urlHash the urlHash to search for
     * @return The URLMapping associated with the provided urlHash
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public URLMapping find(String urlHash) {

        return em.find(URLMapping.class, urlHash);
    }

    /**
     * Creates a new entry in the data store based on the passed in URLMapping object which must have the urlHash set.
     * @param mapping object that must have the longUrl set.
     * @return the mutated mapping with the urlHash and numericId set.
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public URLMapping create(URLMapping mapping) {
        URLMappingDto dto = generator.encodeNext();
        mapping.setUrlHash(dto.uniqueId());
        mapping.setNumericId(dto.counterValue());
        em.persist(mapping);
        return mapping;
    }

    /**
     *
     * @return the max numericId we have stored in the data store
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    private Long max() {
        Query query = em.createQuery("SELECT MAX(m.numericId) FROM URLMapping m");
        return (Long)query.getSingleResult();
    }

}