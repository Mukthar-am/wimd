package org.muks.wimd.kdTree;

import org.muks.wimd.dao.geo.Location;
import org.muks.wimd.dao.request.RequestJson;
import org.muks.wimd.dao.transportation.Driver;
import org.muks.wimd.repository.GoJekDrivers;
import org.muks.wimd.utils.Utils;
import org.muks.wimd.utils.kdTree.KD2DNode;
import org.muks.wimd.utils.kdTree.KD2DTree;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

/**
 * Created by 300000511 on 29/04/17.
 */
public class KDTreeTests {

    @Test
    public void TestNearestNode() {
        GoJekDrivers goJekDrivers = GoJekDrivers.getInstance();
        goJekDrivers.init();

        /** */
        /** update locations */
        int i = 10;
        Location location1 = new Location(new BigDecimal(11.97161923), new BigDecimal(71.59463452), 0.1);
        Location location2 = new Location(new BigDecimal(1.97161923), new BigDecimal(55.59463452), 0.1);
        Location location3 = new Location(new BigDecimal(8.97161923), new BigDecimal(77.59463452), 0.1);

        Driver driver = goJekDrivers.getDriverById(i);
        goJekDrivers.updateDriverLocation(driver, location1);

        i = 8;
        driver = goJekDrivers.getDriverById(i);
        goJekDrivers.updateDriverLocation(driver, location2);

        i = 2;
        driver = goJekDrivers.getDriverById(i);
        goJekDrivers.updateDriverLocation(driver, location3);


        String json = "{" +
                "\"latitude\": 12.97161923," +
                "\"longitude\": 77.59463452," +
                "\"accuracy\": 0.7," +
                "\"radius\": 250," +
                "\"limit\": 3" +
                "}";

        RequestJson requestJson = new RequestJson();
        try {
            requestJson = new RequestJson().parse(Utils.convertToJsonNode(json));
        } catch (Exception e) {
            e.printStackTrace();
        }


        goJekDrivers.getDriversCloseby(requestJson);

        double inLat = requestJson.getLocation().getLatitude().doubleValue();
        double inLon = requestJson.getLocation().getLongitude().doubleValue();



    }
}
