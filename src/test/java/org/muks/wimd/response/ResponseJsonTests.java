package org.muks.wimd.response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.muks.wimd.dao.response.DriverLocationResponse;
import org.muks.wimd.utils.DistanceCalculator;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by 300000511 on 28/04/17
 *  - Response object tests
 */
public class ResponseJsonTests {

    @Test
    public void TestEmptyResponseJson() {
        DriverLocationResponse driverLocationResponse = new DriverLocationResponse();
        String response = driverLocationResponse.getResponse().get("message").toString();
        System.out.println("Response: " + response);
        Assert.assertEquals(response, "{}");
    }

    @Test
    public void TestResponseErrors() {
        DriverLocationResponse driverLocationResponse = new DriverLocationResponse();
        driverLocationResponse.pushResponseError("my error");

        String expectedError = "{\"errors\":[\"my error\"]}";
        String actualError = driverLocationResponse.getResponse().get("errors").toString();
        Assert.assertEquals(actualError, expectedError);
    }

    @Test
    public void TestActualResponseErrors() {
        DriverLocationResponse driverLocationResponse = new DriverLocationResponse();
        driverLocationResponse.pushResponseError("Latitude should be between +/- 90");

        String expectedError = "{\"errors\":[\"Latitude should be between +\\/- 90\"]}";
        String actualError = driverLocationResponse.getResponse().get("errors").toString();
        Assert.assertEquals(actualError, expectedError);
    }

    @Test
    public void TestFindDriverResponseErrors() {
        DriverLocationResponse driverLocationResponse = new DriverLocationResponse();
        driverLocationResponse.pushResponseError("Latitude should be between +/- 90");

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id", 1);
        jsonObj.put("latitude", 12.97161923);
        jsonObj.put("longitude", 77.59463452);
        jsonObj.put("distance", 123);


        JSONArray jsonArray = new JSONArray();
        jsonArray.add(0, jsonObj);

        driverLocationResponse.setAvailableDrivers(jsonArray);

        System.out.println(driverLocationResponse.getAvailableDrivers().toString());
        new DistanceCalculator().calculate();

        String expected = "[{\"distance\":123,\"latitude\":12.97161923,\"id\":1,\"longitude\":77.59463452}]";
        String actual = jsonArray.toJSONString();


        Assert.assertEquals(actual, expected);

    }
}
