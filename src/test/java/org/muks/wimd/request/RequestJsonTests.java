package org.muks.wimd.request;

import com.fasterxml.jackson.databind.JsonNode;
import org.muks.wimd.dao.request.RequestJson;
import org.muks.wimd.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

/**
 * Created by 300000511 on 26/04/17.
 */
public class RequestJsonTests {

    @Test
    public void TestRequestJsonBasic() {
        String json = "{" +
                "\"latitude\": 29.97161923," +
                "\"longitude\": 79.59463452," +
                "\"accuracy\": 0.7" +
                "}";

        String expectedString = "{\"latitude\":29.97161923,\"longitude\":79.59463452,\"accuracy\":0.7}";

        try {
            JsonNode requestJson = Utils.convertToJsonNode(json);
            String actualString = requestJson.toString();

            Assert.assertEquals(actualString, expectedString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestLatitudeAndLongitudeInRange() {
        String json = "{" +
                "\"latitude\": 12.97161923," +
                "\"longitude\": 77.59463452," +
                "\"accuracy\": 0.7" +
                "}";


        try {
            RequestJson requestJson = new RequestJson().parse(Utils.convertToJsonNode(json));
            boolean latitudeInRange = requestJson.getIsLatitudeInRange();
            boolean longitudeInRange = requestJson.getIsLongitudeInRange();

            Assert.assertEquals(longitudeInRange, true);
            Assert.assertEquals(latitudeInRange, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestLatitudeAndLongitudeOutOfRange() {
        String json = "{" +
                "\"latitude\": 121.97161923," +
                "\"longitude\": 97.59463452," +
                "\"accuracy\": 0.7" +
                "}";


        try {
            RequestJson requestJson = new RequestJson().parse(Utils.convertToJsonNode(json));
            boolean latitudeInRange = requestJson.getIsLatitudeInRange();
            boolean longitudeInRange = requestJson.getIsLongitudeInRange();

            Assert.assertEquals(false, longitudeInRange);
            Assert.assertEquals(false, latitudeInRange);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void IsRequestValid() {
        String json = "{" +
                "\"latitude\": 12.97161923," +
                "\"longitude\": 67.59463452," +
                "\"accuracy\": 0.7" +
                "}";


        try {
            RequestJson requestJson = new RequestJson().parse(Utils.convertToJsonNode(json));
            Assert.assertEquals(true, requestJson.isRequestValid());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void IsRequestInvalid() {
        String json = "{" +
                "\"latitude\": 121.97161923," +
                "\"longitude\": 67.59463452," +
                "\"accuracy\": 0.7" +
                "}";


        try {
            RequestJson requestJson = new RequestJson().parse(Utils.convertToJsonNode(json));
            Assert.assertEquals(false, requestJson.isRequestValid());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestGetLatitudeLongitude() {
        String json = "{" +
                "\"latitude\": 121.97161923," +
                "\"longitude\": 67.59463452," +
                "\"accuracy\": 0.7" +
                "}";


        try {
            RequestJson requestJson = new RequestJson().parse(Utils.convertToJsonNode(json));
            BigDecimal lat = requestJson.getLatitude();
            BigDecimal longi = requestJson.getLongitude();

            Assert.assertEquals(1, lat.compareTo(longi));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
