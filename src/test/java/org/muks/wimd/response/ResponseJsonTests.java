package org.muks.wimd.response;

import org.muks.wimd.dao.response.DriverLocationResponse;
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
}
