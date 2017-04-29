package org.muks.wimd.repository;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.muks.wimd.dao.geo.Location;
import org.muks.wimd.dao.request.RequestJson;
import org.muks.wimd.dao.response.DriverLocationResponse;
import org.muks.wimd.dao.transportation.Car;
import org.muks.wimd.dao.transportation.Driver;
import org.muks.wimd.dao.transportation.Segments;
import org.muks.wimd.dao.transportation.Vehicle;
import org.muks.wimd.utils.DistanceCalculator;
import org.muks.wimd.utils.kdTree.KD2DNode;
import org.muks.wimd.utils.kdTree.KD2DTree;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

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
    private KD2DTree kdTree = new KD2DTree(CAPACITY);
    private TreeMap<Integer, Driver> DRIVER_LISTING = new TreeMap<>();

    private static GoJekDrivers instance = null;


    public static GoJekDrivers getInstance() {
        if (instance == null) {
            instance = new GoJekDrivers();
            instance.init();
        }

        return instance;
    }

    /**
     * - initialize
     */
    public void init() {
        if (DRIVER_LISTING.size() == 0) {


            for (int i = 1; i <= CAPACITY; i++) {

                Location location = new Location();
                Vehicle vehicle = null;
                if (i <= 10000)
                    vehicle = new Car("Toyota", "Etios", "White", ("KA-01 HA" + i), Segments.SEDAN);

                else if (i > 10000 && i <= 20000)
                    vehicle = new Car("Tata", "Indica", "White", ("KA-01 HA" + i), Segments.MINI);

                else if (i > 20000 && i <= 30000)
                    vehicle = new Car("Tata", "Nano", "White", ("KA-01 HA" + i), Segments.MICRO);

                else if (i > 30000 && i <= 50000)
                    vehicle = new Car("Mercedes", "C-Class", "White", ("KA-01 HA" + i), Segments.LUXURY);


                this.DRIVER_LISTING.put(i, new Driver(i, "Driver # " + i, vehicle, new Location()));
                double[] cordinates = new double[2];
                cordinates[0] = location.getLatitude().doubleValue();
                cordinates[1] = location.getLongitude().doubleValue();
                kdTree.add(cordinates, i);
            }

        }
    }


    /**
     * @param driverId - int
     * @return - driver pojo
     */
    public Driver getDriverById(int driverId) {
        if (driverId <= this.CAPACITY) {
            return this.DRIVER_LISTING.get(driverId);
        }

        return null;
    }

    /**
     * @param driver - Driver pojo
     */
    public void updateDriverLocation(Driver driver) {
        this.DRIVER_LISTING.put(driver.getId(), driver);
        System.out.println("uUpdating driver infomration: " + this.DRIVER_LISTING.get(driver.getId()));
    }

    public void updateDriverLocation(Driver driver, Location location) {
        this.DRIVER_LISTING.put(driver.getId(), driver);
        System.out.println("uUpdating driver infomration: " + this.DRIVER_LISTING.get(driver.getId()));
        driver.setLocation(location);
    }

    /**
     * @return
     */
    public Map<Integer, Driver> getDriverListing() {
        return this.DRIVER_LISTING;
    }

    /**
     * - get capacity
     *
     * @return int
     */
    public int getCapacity() {
        return this.CAPACITY;
    }

    public void setCapacity(int capacity) {
        this.CAPACITY = capacity;
    }


    public JSONArray getDriversCloseby(RequestJson inputRequestJson) {

        BigDecimal inLat = inputRequestJson.getLatitude();
        BigDecimal inLon = inputRequestJson.getLongitude();

        KD2DTree kdTree = getKdTree();
        System.out.println("Inorder of 2D Kd tree: ");
        double a[] = new double[2];
        a[0] = inLat.doubleValue();
        a[1] = inLon.doubleValue();

        KD2DNode node = kdTree.find_nearest(a);
        System.out.println("+++ " + node.toString() + ", Driver: " + node.driverId);


        Driver driver = this.DRIVER_LISTING.get(node.driverId);
        DriverLocationResponse driverLocationResponse = new DriverLocationResponse();
        driverLocationResponse.pushResponseError("Latitude should be between +/- 90");

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id", driver.getId());
        jsonObj.put("latitude", driver.getLocation().getLatitude());
        jsonObj.put("longitude", driver.getLocation().getLongitude());

        double distance = new DistanceCalculator().distance(inLat.doubleValue(), driver.getLocation().getLatitude().doubleValue(),
                inLon.doubleValue(), driver.getLocation().getLongitude().doubleValue(), "meters");

        jsonObj.put("distance", distance);


        JSONArray jsonArray = new JSONArray();
        jsonArray.add(0, jsonObj);

        driverLocationResponse.addDriversListing(jsonArray);

        return driverLocationResponse.getDriversListing();
        

            /*
                ToDo: Iterate by the drives and check on which driver is close by the main digit of lat-lon and run distance calculator
             */
            /*
             *  - Iterate through the first 10 (value of limit), who do match the whole number of the lat-lon
              * - Based on the matched 10, find the closest one.
              * - If someone falls within the radius, return else run the same above iteration for next 10 matching drivers
             */


    }


    public KD2DTree getKdTree() {
        for (int i = 1; i < 10; i++) {
            Driver driver = this.DRIVER_LISTING.get(i);
            Location location = driver.getLocation();

            double x[] = new double[2];
            x[0] = location.getLatitude().doubleValue();
            x[1] = location.getLongitude().doubleValue();
            kdTree.add(x, i);
        }

        return kdTree;
    }

}
