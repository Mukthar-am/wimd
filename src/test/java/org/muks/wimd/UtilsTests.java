package org.muks.wimd;

import org.muks.wimd.dao.request.RequestJson;
import org.muks.wimd.utils.DistanceCalculator;
import org.muks.wimd.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

/**
 * Created by 300000511 on 28/04/17.
 */
public class UtilsTests {

    @Test
    public void TestCalculateDiff() {
        String json1 = "{" +
                "\"latitude\": 121.97161923," +
                "\"longitude\": 67.59463452," +
                "\"accuracy\": 0.7" +
                "}";

        String json2 = "{" +
                "\"latitude\": 121.97161923," +
                "\"longitude\": 67.59163452," +
                "\"accuracy\": 0.7" +
                "}";

        try {
            RequestJson requestJson1 = new RequestJson().parse(Utils.convertToJsonNode(json1));
            RequestJson requestJson2 = new RequestJson().parse(Utils.convertToJsonNode(json2));
            BigDecimal lat1 = requestJson1.getLatitude();
            BigDecimal longi1 = requestJson1.getLongitude();

            BigDecimal lat2 = requestJson2.getLatitude();
            BigDecimal longi2 = requestJson2.getLongitude();

            double distance = new DistanceCalculator().calculate(lat1, longi1, lat2, longi2, "meters");
            System.out.println("Distance: "+ distance);
            Assert.assertEquals(17.662433243961374, distance);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
