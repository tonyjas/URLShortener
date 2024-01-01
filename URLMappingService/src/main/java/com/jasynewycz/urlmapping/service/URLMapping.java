package com.jasynewycz.urlmapping.service;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "MAPPINGS")
public class URLMapping {


    @Id
    @NotNull
    @Size(min = 1, max = 40)
    @Column(length = 40)
    private String urlHash;

    // set length to 2048 although this may need extending
    // max URL length is potentially unbounded based on the specifications so
    // we may need to resort to a CLOB type in reality here but for initial MVP we
    // stick to a 2048 limit
    @NotNull
    @Size(min = 1, max = 2048)
    @Column(length = 2048)
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