package org.spring.data.aerospike.repository;

import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.math.BigDecimal;
import java.util.Objects;

@Document(collection = "docs")
public class AerospikeDocument {

    @Id
    private String id;

    private BigDecimal value;

    @Version
    private long version;


    public AerospikeDocument() {
    }

    public AerospikeDocument(String id, BigDecimal value, long version) {
        this.id = id;
        this.value = value;
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AerospikeDocument document = (AerospikeDocument) o;
        return version == document.version && Objects.equals(id, document.id) && Objects.equals(value, document.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, version);
    }
}
