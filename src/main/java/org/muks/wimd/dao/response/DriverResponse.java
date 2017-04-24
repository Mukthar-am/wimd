package org.muks.wimd.dao.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.json.simple.JSONObject;

/**
 * Created by 300000511 on 25/04/17
 *
 *  - Empty json response for a driver call
 */

@JsonSerialize
public class DriverResponse extends JsonResponse {
    JSONObject response = new JSONObject();

    @Override
    public String getResponse() {
        return response.toJSONString();
    }


}