package com.github.airlines.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * @author alex.dobjanschi
 * @since 11/18/12 5:19 PM
 */
@Entity
@Table(name = "companies")
public class Company {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;

    @Column
    String name;

    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @Column
    DateTime foundedOn;

    public Company() {}
    public Company(String name, DateTime foundedOn) {
        this.name = name;
        this.foundedOn = foundedOn;
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

    public DateTime getFoundedOn() {
        return foundedOn;
    }

    public void setFoundedOn(DateTime foundedOn) {
        this.foundedOn = foundedOn;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", foundedOn=" + foundedOn +
                '}';
    }
}
