package org.muks.wimd.dao.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by 300000511 on 25/04/17
 *
 *  - Empty json response for a driver call
 */

@JsonSerialize
public class DriverLocationResponse extends JsonResponse {
    JSONObject response = new JSONObject();

    public DriverLocationResponse() {
        this.response.put("message", new JSONObject());
    }

    public void pushResponseError(String error) {
        if (!this.response.containsKey("errors")) {
            JSONArray responseErrors = new JSONArray();
            responseErrors.add(error);

            JSONObject errorsJsonObject = new JSONObject();
            errorsJsonObject.put("errors", responseErrors);

            this.response.put("errors", errorsJsonObject);

        } else {
            JSONArray errorsArray = (JSONArray) this.response.get("errors");
            errorsArray.add(error);

        }
    }

    @Override
    public JSONObject getResponse() {
        return this.response;
    }

}