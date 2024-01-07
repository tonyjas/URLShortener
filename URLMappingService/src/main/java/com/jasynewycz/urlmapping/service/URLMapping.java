package com.jasynewycz.urlmapping.service;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * Entity bean for the "MAPPINGS" table. Initial implementation uses a regular datasource provided via a wildfly
 * datasource (POSTGRESQL) in my test environments case. urlHash should be the Primary Key of the table however
 * numericId should also have an index as we use this to calculate at startup what the last used value was to
 * initialize our counter.
 */
@Entity
@Table(name = "MAPPINGS")
public class URLMapping {


    public static final int MIN_HASH_LENGTH = 6;
    public static final int MIN_LONG_URL_LENGTH = 8;
    public static final int MAX_HASH_LENGTH = 40;

    /**
     * Sets the max long URL length to 2048 although this may need extending.
     * Max URL length is potentially unbounded based on the specifications so
     * we may need to resort to a CLOB type in reality here but for initial MVP we
     * stick to a 2048 limit.
     */
    public static final int MAX_LONG_URL_LENGTH = 2048;


    @Id
    @NotNull
    @Size(min = MIN_HASH_LENGTH, max = MAX_HASH_LENGTH)
    @Column(length = MAX_HASH_LENGTH)
    private String urlHash;

    @NotNull
    @Size(min = MIN_LONG_URL_LENGTH, max = MAX_LONG_URL_LENGTH)
    @Column(length = MAX_LONG_URL_LENGTH)
    private String longUrl;

    @NotNull
    @Min(0)
    private long numericId;

    public String getUrlHash() {
        return urlHash;
    }

    public void setUrlHash(String urlHash) {
        this.urlHash = urlHash;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public long getNumericId() {
        return numericId;
    }

    public void setNumericId(long numericId) {
        this.numericId = numericId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        URLMapping that = (URLMapping) o;
        return numericId == that.numericId && Objects.equals(urlHash, that.urlHash) && Objects.equals(longUrl, that.longUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(urlHash, longUrl, numericId);
    }
}