package org.muks.wimd.transportation;

import org.muks.wimd.dao.geo.Location;
import org.muks.wimd.dao.request.RequestJson;
import org.muks.wimd.dao.transportation.Car;
import org.muks.wimd.dao.transportation.Driver;
import org.muks.wimd.dao.transportation.Segments;
import org.muks.wimd.dao.transportation.Vehicle;
import org.muks.wimd.repository.GoJekDrivers;
import org.muks.wimd.utils.DistanceCalculator;
import org.muks.wimd.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by 300000511 on 26/04/17.
 */
public class DriverAndVehicleTests {

    @Test()
    void testCarRegistractionProps() {
        String expectedRegistration1 = "KA-05 ML-3908";
        String expectedRegistration2 = "KA-55 ML-3908";
        Car car1 = new Car("VW", "Vento", "Black", expectedRegistration1, Segments.LUXURY);
        Car car2 = new Car("Toyota", "Etios Viva", "White", expectedRegistration2, Segments.SEDAN);

        Assert.assertEquals(expectedRegistration1, car1.getRegistration());
        Assert.assertEquals(expectedRegistration2, car2.getRegistration());
    }

    @Test
    void testVehicleCreation() {
        Assert.assertEquals("LUXURY", Utils.getSegment("luxury").toString());
        Assert.assertEquals("SEDAN", Utils.getSegment("sedan").toString());
        Assert.assertEquals("MINI", Utils.getSegment("mini").toString());
        Assert.assertEquals("MICRO", Utils.getSegment("micro").toString());
        Assert.assertEquals("AUTO", Utils.getSegment("auto").toString());

    }


    // ToDo: Needs equals and hashcode implementation for object to object comparison
//    @Test
//    public void updateDriverTests() {
//        GoJekDrivers goJekDrivers = new GoJekDrivers();
//        goJekDrivers.setCapacity(5);
//        goJekDrivers.init();
//
//        String expectedDriverString = "[Id: 1, Name: Driver # 1, Vehicle: (Make: Toyota, Model: Etios, Color: Whiteeeee, Registration: KA-01 HA1, Segment: SEDAN)]";
//
//        Vehicle vehicle = new Car("Toyota", "Etios", "Whiteeeee", ("KA-01 HA1"), Segments.SEDAN);
//        Driver driverToUpdate = new Driver(1, "Driver # 1", vehicle);
//        goJekDrivers.updateDriverLocation(driverToUpdate);
//
//        Assert.assertEquals(goJekDrivers.getDriverById(1), expectedDriverString);
//    }

    @Test
    public void getDriverMaxCapacityTests() {
        GoJekDrivers goJekDrivers = new GoJekDrivers();
        goJekDrivers.setCapacity(5);
        Assert.assertEquals(goJekDrivers.getCapacity(), 5);
    }


    @Test
    public void getDriverByIdTest() {
        GoJekDrivers goJekDrivers = new GoJekDrivers();
        goJekDrivers.setCapacity(5);
        goJekDrivers.init();

        String expectedDriverString = "[Id: 1, Name: Driver # 1, Vehicle: (Make: Toyota, Model: Etios, Color: White, Registration: KA-01 HA1, Segment: SEDAN, )]";
        Assert.assertEquals(goJekDrivers.getDriverById(1), expectedDriverString);

    }

    @Test
    public void TestDriverDistance() {
        GoJekDrivers goJekDrivers = new GoJekDrivers();
        goJekDrivers.setCapacity(5);
        goJekDrivers.init();

        Vehicle vehicle = new Car("Toyota", "Etios", "Whiteeeee", ("KA-01 HA1"), Segments.SEDAN);
        Location changedLocation = new Location(new BigDecimal("12.97161923"), new BigDecimal("77.59463452"), 0.1);
        Driver driverToUpdate = new Driver(1, "Driver # 1", vehicle, changedLocation);

        goJekDrivers.updateDriverLocation(driverToUpdate);


        Driver driver1 = goJekDrivers.getDriverById(1);
        Driver driver2 = goJekDrivers.getDriverById(2);

        try {
            BigDecimal lat1 = driver1.getLocation().getLatitude();
            BigDecimal longi1 = driver1.getLocation().getLongitude();

            BigDecimal lat2 = driver2.getLocation().getLatitude();
            BigDecimal longi2 = driver2.getLocation().getLongitude();

            double distance = new DistanceCalculator().calculate(lat1, longi1, lat2, longi2, "meters");
            System.out.println("Distance: "+ distance);
            Assert.assertEquals(distance, 866345.231661696);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
