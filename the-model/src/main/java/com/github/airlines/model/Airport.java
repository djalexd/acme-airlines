package com.github.airlines.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Declaration of an airport.
 *
 * @author alex dobjanschi
 * @since 11/11/12 8:35 PM
 */
@Entity
@Table(name = "airports")
public class Airport {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;

    @Basic
    String name;

    @Embedded
    @Column(name = "airport_position")
    DetailedPosition position;

    public Airport() {}
    public Airport(String name, DetailedPosition position) {
        this.name = name;
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DetailedPosition getPosition() {
        return position;
    }

    public void setPosition(DetailedPosition position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position=" + position +
                '}';
    }
}
