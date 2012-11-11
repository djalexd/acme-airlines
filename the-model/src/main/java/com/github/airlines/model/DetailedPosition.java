package com.github.airlines.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * An embeddable position. Cannot be stored by itself.
 * @author alex.dobjanschi
 * @since 11/11/12 8:48 PM
 */
@Embeddable
public class DetailedPosition implements Serializable {

    @Column(name = "latitude", nullable = false)
    BigDecimal latitude;
    @Column(name = "longitude", nullable = false)
    BigDecimal longitude;

    public DetailedPosition() {}

    public DetailedPosition(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
