package org.muks.wimd.dao.transportation;

/**
 * Created by 300000511 on 25/04/17.
 */
public abstract class Vehicle {
    String make = null;
    String model = null;
    String color = null;
    String registration = null;
    Segments segment = null; // Enum Values = luxury, sedan, mini, micro

    abstract String getMake();
    abstract String getModel();
    abstract String getColor();
    abstract String getRegistration();
    abstract Segments getSegment();
}
