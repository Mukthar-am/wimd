package org.muks.wimd.dao.request;

import com.fasterxml.jackson.databind.JsonNode;

import java.math.BigDecimal;

/**
 * Created by 300000511 on 26/04/17.
 */
public class RequestJson {
    //"latitude": 12.97161923, "longitude": 77.59463452, "accuracy": 0.7

    BigDecimal rangeMin = new BigDecimal(-90);
    BigDecimal rangeMax = new BigDecimal(90);

    BigDecimal latitude = new BigDecimal(0);
    BigDecimal longitude = new BigDecimal(0);
    double accuracy = 0.7;

    boolean isLatitudeInRange = false;
    boolean isLongitudeInRange = false;

    public RequestJson(JsonNode requestJsonNode) {
        this.latitude = requestJsonNode.get("latitude").decimalValue();
        this.longitude = requestJsonNode.get("longitude").decimalValue();
        this.accuracy = requestJsonNode.get("accuracy").asDouble();

        if (this.latitude.compareTo(this.rangeMin) == 1
                && this.latitude.compareTo(this.rangeMax) == -1) {
            this.isLatitudeInRange = true;
        }

        if (this.longitude.compareTo(this.rangeMin) == 1
                && this.longitude.compareTo(this.rangeMax) == -1) {
            this.isLongitudeInRange = true;
        }
    }


    public boolean isLatitudeInRange() { return this.isLatitudeInRange; }

    public boolean isLongitudeInRange() { return this.isLongitudeInRange; }

    public String toString() {
        return "\"latitude\":" + this.latitude + ", \"" + this.longitude + "\", \"accuracy\": " + this.accuracy;
    }
}
