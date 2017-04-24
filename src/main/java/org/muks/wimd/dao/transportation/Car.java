package org.muks.wimd.dao.transportation;

/**
 * Created by 300000511 on 25/04/17.
 */
public class Car extends Vehicle {
    public Car(String make, String model, String color, String registration, Segments segment) {
        super.make = make;
        super.model = model;
        super.color = color;
        super.registration = registration;
        super.segment = segment;
    }

    @Override
    String getMake() {
        return make;
    }

    @Override
    String getModel() {
        return model;
    }

    @Override
    String getColor() {
        return color;
    }

    @Override
    public String getRegistration() {
        return registration;
    }

    @Override
    Segments getSegment() {
        return segment;
    }


    public String toString() {
        StringBuilder printCar = new StringBuilder("(");
        printCar.append("Make: " + make + ", ");
        printCar.append("Model: " + model + ", ");
        printCar.append("Color: " + color + ", ");
        printCar.append("Registration: " + registration + ", ");
        printCar.append("Segment: " + segment + ", ");
        printCar.append(")");

        return printCar.toString();
    }
}
