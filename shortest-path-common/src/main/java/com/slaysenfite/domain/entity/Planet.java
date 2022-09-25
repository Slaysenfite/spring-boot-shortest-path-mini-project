package com.slaysenfite.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Persistable entity for a Planet
 */
@Entity
@Table(name = "PLANET", schema = "INTERSTELLAR_TRANSPORT")
public class Planet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long entityId;

    @Column(name = "NODE")
    private String node;

    @Column(name = "NAME")
    private String name;

    public Planet() {
    }

    public Planet(String node, String name) {
        this.node = node;
        this.name = name;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
