package org.muks.wimd.repository;

import org.muks.wimd.dao.transportation.Driver;
import org.testng.annotations.Test;
import org.w3c.dom.ranges.DocumentRange;

import java.util.Map;

/**
 * Created by 300000511 on 25/04/17.
 */
public class RepositoryTests {
    @Test
    public void testDriversRepository() {
        GoJekDrivers goJekDrivers = new GoJekDrivers().getInstance();
        if (goJekDrivers != null) {
            System.out.println("instance not null");
        }
        Map<Integer, Driver> drivers = goJekDrivers.getDriverListing();
        System.out.println("Size=" + drivers.size());

        //goJekDrivers.getCapacity()
        for (int i = 1; i <= 5; i++) {
            System.out.println(drivers.get(i));
        }
    }
}
