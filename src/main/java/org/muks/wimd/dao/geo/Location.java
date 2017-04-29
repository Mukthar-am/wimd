package org.muks.wimd.dao.geo;

import java.math.BigDecimal;

/**
 * Created by 300000511 on 28/04/17
 *  Location object
 */
public class Location {
    private BigDecimal latitude;
    private BigDecimal longitude;
    private double accuracy;

    public Location() {
        this.latitude = new BigDecimal(0);
        this.longitude = new BigDecimal(0);
        this.accuracy = 1.0;
    }


    public Location(BigDecimal lat, BigDecimal longi, double acc) {
        this.latitude = lat;
        this.longitude = longi;
        this.accuracy = acc;
    }

    public BigDecimal getLatitude() { return this.latitude; }
    public BigDecimal getLongitude() { return this.longitude; }
    public double getAccuracy() { return this.accuracy; }

    public String toString() {
        return "[Latitude: " + this.latitude + ", Longitude: " + this.longitude + ", Accuracy: " + this.accuracy + "]";
    }
}
