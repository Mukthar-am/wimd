package org.muks.wimd.repository;


import org.muks.wimd.dao.transportation.Car;
import org.muks.wimd.dao.transportation.Segments;
import org.muks.wimd.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by 15692 on 23/06/16.
 */


public class VehicleTests {

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

}