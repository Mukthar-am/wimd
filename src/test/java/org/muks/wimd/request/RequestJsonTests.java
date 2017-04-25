package org.muks.wimd.request;

import org.muks.wimd.dao.request.RequestJson;
import org.muks.wimd.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by 300000511 on 26/04/17.
 */
public class RequestJsonTests {
    @Test
    public void TestRequestJson() {
        String json = "{" +
                "\"latitude\": 12.97161923," +
                "\"longitude\": 77.59463452," +
                "\"accuracy\": 0.7" +
                "}";

        RequestJson requestJson = null;
        try {
            requestJson = new RequestJson( Utils.convertToJsonNode(json) );

            Assert.assertEquals(true, requestJson.isLatitudeInRange());
            Assert.assertEquals(true, requestJson.isLongitudeInRange());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
