package org.muks.wimd.dao.request;

import com.fasterxml.jackson.databind.JsonNode;

import java.math.BigDecimal;

/**
 * Created by 300000511 on 26/04/17
 *
 *  - Request pojo with some of the validations done
 */
public class RequestJson {
    private BigDecimal rangeMin = new BigDecimal(-90);
    private BigDecimal rangeMax = new BigDecimal(90);

    private BigDecimal latitude = new BigDecimal(0);
    private BigDecimal longitude = new BigDecimal(0);
    private double accuracy = 0.7;

    private boolean isLatitudeInRange = false;
    private boolean isLongitudeInRange = false;

    private boolean isValidRequest = false;

    /**
     * Having parse method instead of overloading class constructor and is a bad practise to implement too much of logic within constructor
     * @param requestJsonNode
     * @return
     */
    public RequestJson parse(JsonNode requestJsonNode) {
        RequestJson requestJson = new RequestJson();

        requestJson.latitude = requestJsonNode.get("latitude").decimalValue();
        requestJson.longitude = requestJsonNode.get("longitude").decimalValue();
        requestJson.accuracy = requestJsonNode.get("accuracy").asDouble();


        /** Check on the range being within +/- 90 (inclusive) */
        if (requestJson.latitude.compareTo(requestJson.rangeMin) == 1
                && requestJson.latitude.compareTo(requestJson.rangeMax) == -1) {
            requestJson.isLatitudeInRange = true;
        }

        /** Check on the range being within +/- 90 (inclusive) */
        if (requestJson.longitude.compareTo(requestJson.rangeMin) == 1
                && requestJson.longitude.compareTo(requestJson.rangeMax) == -1) {
            requestJson.isLongitudeInRange = true;
        }


        if ( (requestJson.isLatitudeInRange) && (requestJson.isLongitudeInRange) ) {
            requestJson.isValidRequest = true;
        }

        return requestJson;
    }


    public boolean getIsLatitudeInRange() { return this.isLatitudeInRange; }

    public boolean getIsLongitudeInRange() { return this.isLongitudeInRange; }

    public boolean isRequestValid() { return this.isValidRequest; }

    public BigDecimal getLatitude() { return this.latitude; }

    public BigDecimal getLongitude() { return this.longitude; }

    public double getAccuracy() { return this.accuracy; }

    public String toString() {
        return "\"latitude\":" + this.latitude + ", \"" + this.longitude + "\", \"accuracy\": " + this.accuracy;
    }
}
