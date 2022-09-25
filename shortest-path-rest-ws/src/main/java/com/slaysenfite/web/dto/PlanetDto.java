package com.slaysenfite.web.dto;


/**
 * Domain transfer object for a Planet
 */
public class PlanetDto {

    private Long entityId;

    private String node;

    private String name;

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
