package org.muks.wimd.repository;

import org.muks.wimd.dao.transportation.Car;
import org.muks.wimd.dao.transportation.Driver;
import org.muks.wimd.dao.transportation.Segments;
import org.muks.wimd.dao.transportation.Vehicle;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 300000511 on 25/04/17
 * <p>
 * - A repository of drivers, created on-fly during the server startup.
 * <p>
 * - In production scenario, will be a singleton, factory class initiated and loaded for once after querying
 * a metadata table which holds entire driver information
 */

public class GoJekDrivers {
    private int CAPACITY = 50000;
    private Map<Integer, Driver> DRIVER_LISTING = new HashMap<>();

    private static GoJekDrivers instance = null;


    public static GoJekDrivers getInstance() {
        if (instance == null) {
            instance = new GoJekDrivers();
            instance.init();
        }

        return instance;
    }


    public void init() {
        if (DRIVER_LISTING.size() == 0) {

            for (int i = 1; i <= CAPACITY; i++) {

                Vehicle vehicle = null;
                if (i <= 10000)
                    vehicle = new Car("Toyota", "Etios", "White", ("KA-01 HA" + i), Segments.SEDAN);

                else if (i > 10000 && i <= 20000)
                    vehicle = new Car("Tata", "Indica", "White", ("KA-01 HA" + i), Segments.MINI);

                else if (i > 20000 && i <= 30000)
                    vehicle = new Car("Tata", "Nano", "White", ("KA-01 HA" + i), Segments.MICRO);

                else if (i > 30000 && i <= 50000)
                    vehicle = new Car("Mercedes", "C-Class", "White", ("KA-01 HA" + i), Segments.LUXURY);

                this.DRIVER_LISTING.put(i, new Driver(i, "Driver # " + i, vehicle));
            }

        }
    }


    /**
     * @param driverId
     * @return
     */
    public Driver getDriverById(int driverId) {
        if (driverId <= this.CAPACITY) {
            return this.DRIVER_LISTING.get(driverId);
        }

        return null;
    }

    public Driver updateDriverLocation(int driverId) {

//        this.DRIVER_LISTING.put(driverId, driverUpdate);

        return null;
    }

    public Map<Integer, Driver> getDriverListing() { return this.DRIVER_LISTING; }

    public int getCapacity() { return this.CAPACITY; }
}
