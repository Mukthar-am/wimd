package org.muks.wimd.dao;

import org.muks.wimd.dao.request.RequestJson;
import org.muks.wimd.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by 300000511 on 26/04/17
 *  tests for request json
 */
public class RequestJsonTests {

    @Test
    public void TestRequestJson() {
        String json = "{" +
                "\"latitude\": 12.97161923," +
                "\"longitude\": 77.59463452," +
                "\"accuracy\": 0.7" +
                "}";

        String expectedString = "\"latitude\":12.97161923, \"77.59463452\", \"accuracy\": 0.7";

        try {
            RequestJson requestJson = new RequestJson(Utils.convertToJsonNode(json));
            String actualString = requestJson.toString();
            Assert.assertEquals(expectedString, actualString);

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
            RequestJson requestJson = new RequestJson(Utils.convertToJsonNode(json));
            boolean latitudeInRange = requestJson.isLatitudeInRange();
            boolean longitudeInRange = requestJson.isLongitudeInRange();

            Assert.assertEquals(true, longitudeInRange);
            Assert.assertEquals(true, latitudeInRange);

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
            RequestJson requestJson = new RequestJson(Utils.convertToJsonNode(json));
            boolean latitudeInRange = requestJson.isLatitudeInRange();
            boolean longitudeInRange = requestJson.isLongitudeInRange();

            Assert.assertEquals(false, longitudeInRange);
            Assert.assertEquals(false, latitudeInRange);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
