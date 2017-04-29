package org.muks.wimd.dao.transportation;

import org.muks.wimd.dao.geo.Location;

/**
 * Created by 300000511 on 25/04/17
 *
 *  - Driver object
 */

public class Driver {
    private int id;
    private String name;
    private Vehicle vehicle;
    private Location location;

    public Driver(int driverId, String driverName, Vehicle vehicle, Location location) {
        this.id = driverId;
        this.name = driverName;
        this.vehicle = vehicle;
        this.location = location;
    }

    public int getId() { return this.id; }

    public String getName() { return this.name; }

    public Vehicle getVehicle() { return this.vehicle; }

    public Location getLocation() { return this.location; }

    public void setLocation(Location loctn) { this.location = loctn; }

    public String toString() {
        StringBuilder output = new StringBuilder("[");
        output.append("Id: " + this.id + ", ");
        output.append("Name: " + this.name + ", ");
        output.append("Vehicle: " + this.vehicle.toString() + ", ");
        output.append("Location: " + this.location.toString() + "]");
        return output.toString();
    }
}
