package com.slaysenfite.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Persistable entity for a Planet
 */
@Entity
@Table(name = "ROUTE", schema = "INTERSTELLAR_TRANSPORT")
@SecondaryTable(
    name = "ROUTE_TRAFFIC", schema = "INTERSTELLAR_TRANSPORT",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "ROUTE_ID")
)
public class Route implements Serializable {

    @Id
    private Integer id;

    @Column(name = "PLANET_ORIGIN")
    private String origin;

    @Column(name = "PLANET_DESTINATION")
    private String destination;

    @Column(name = "DISTANCE")
    private Double distance;

    @Column(table = "ROUTE_TRAFFIC", name = "DELAY")
    private Double delay;

    public Route(int id, String origin, String destination, Double distance, Double delay) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.delay = delay;
    }

    public Route() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getDelay() {
        return delay;
    }

    public void setDelay(Double delay) {
        this.delay = delay;
    }
}
