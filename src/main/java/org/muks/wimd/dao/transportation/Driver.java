package org.muks.wimd.dao.transportation;

/**
 * Created by 300000511 on 25/04/17
 *
 *  - Driver object
 */

public class Driver {
    private int id;
    private String name;
    private Vehicle vehicle;


    public Driver(int driverId, String driverName, Vehicle vehicle) {
        this.id = driverId;
        this.name = driverName;
        this.vehicle = vehicle;
    }

    public int getId() { return this.id; }

    public String getName() { return this.name; }

    public Vehicle getVehicle() { return this.vehicle; }

    public String toString() {
        StringBuilder output = new StringBuilder("[");
        output.append("Id: " + id + ", ");
        output.append("Name: " + name + ", ");
        output.append("Vehicle: " + vehicle.toString() + "]");
        return output.toString();
    }
}
