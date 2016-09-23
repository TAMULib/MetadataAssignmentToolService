/* 
 * MetadataFieldValue.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package edu.tamu.app.model;

import java.io.UnsupportedEncodingException;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import edu.tamu.framework.model.BaseEntity;

/**
 * 
 * 
 * @author
 *
 */
@Entity
public class MetadataFieldValue extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = MetadataFieldGroup.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private MetadataFieldGroup field;

    @ManyToOne(optional = true, cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH }, fetch = FetchType.EAGER)
    private ControlledVocabulary cv;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String value;

    @PrePersist
    @PreUpdate
    protected void sanitize() throws UnsupportedEncodingException {
        if (value != null) {
            value = value.replaceAll("[\u0000-\u001f]", "");
            value = new String(value.getBytes(), "UTF-8");
        }
    }

    public MetadataFieldValue() {
    }

    public MetadataFieldValue(MetadataFieldGroup field) {
        this();
        this.field = field;
    }

    public MetadataFieldValue(ControlledVocabulary cv, MetadataFieldGroup field) {
        this(field);
        this.cv = cv;
    }

    public MetadataFieldValue(String value, MetadataFieldGroup field) {
        this(field);
        this.value = value;
    }

    public MetadataFieldGroup getField() {
        return field;
    }

    public void setField(MetadataFieldGroup field) {
        this.field = field;
    }

    public ControlledVocabulary getCv() {
        return cv;
    }

    public void setCv(ControlledVocabulary cv) {
        this.cv = cv;
    }

    public String getValue() {
        if (value == null) {
            return cv.getValue();
        }
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
